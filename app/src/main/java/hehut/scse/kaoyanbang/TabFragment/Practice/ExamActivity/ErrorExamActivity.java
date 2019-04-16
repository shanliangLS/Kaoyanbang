package hehut.scse.kaoyanbang.TabFragment.Practice.ExamActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.TabFragment.Practice.MyAdapter.ExamAdapter;
import hehut.scse.kaoyanbang.TabFragment.Practice.Util.Subject;


public class ErrorExamActivity extends Activity {
    private final int UNREAD_COLOR = Color.GRAY;
    private final int READ_COLOR = R.color.sky_l;
    private final int READING_COLOR = Color.GREEN;
    private final int SINGLE_CHOICE = 0;
    private final int MULTIPLE_CHOICE = 1;
    private final int JUDGE_CHOICE = 2;
    private final int GROUP_POSITION = 0;
    private final int CHILD_POSITION = 1;
    // 判断点击状态
    private final int CHECKED = 1;
    private final int UNCHECKED = 0;
    Context mContext = ErrorExamActivity.this;
    ExamAdapter testListAdapter;
    ExpandableListView testListExpandableListView;// 左侧题目目录
    TextView testProblemTextView;// 题目文本框
    TextView answerTextView;
    Button nextButton;
    Button preButton;
    TextView showAnswerButton;
    CheckBox testSelectCheckBoxA;
    CheckBox testSelectCheckBoxB;
    CheckBox testSelectCheckBoxC;
    CheckBox testSelectCheckBoxD;
    CheckBox testSkipNextAuto;
    TextView titleTv;
    TextView clearTv;
    RelativeLayout hasProLayout;
    RelativeLayout noProLayout;
    private String SHARE_UNIT;
    private String SHARE_GROUP_POSITION;
    private String SHARE_CHILD_POSITION;
    private boolean lockState = false;
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;
    private boolean showAnswer = false;
    private int[][][] answerSave;
    private boolean[][] answerTrue;
    private boolean[][] tempAnswerTrue;
    private Subject[] angleSelectSubject;
    private Subject[] multipleSelectSubject;
    private Subject[] judgeSelectSubject;
    private int singleCount = 0;
    private int multipleCount = 0;
    private int judgeCount = 0;
    private int[] position;
    private String singleFileName;
    private String multipleFileName;
    private String judgeFileName;
    private ExamDetailsActivity praticeTestActivity;
    private int currentUnit = 0;
    private boolean firstChangeSpinner = true;
    private boolean hasFile = true;
    private int[] testSelectCheckBoxId =
            {R.id.test_choice_A_checkbox, R.id.test_choice_B_checkbox,
                    R.id.test_choice_C_checkbox, R.id.test_choice_D_checkbox};
    private String fileDirName;

    public static void activityStart(Context mContext, String singleFileName,
                                     String multipleFileName, String judgeFileName, String fileDirName) {
        Intent intent = new Intent(mContext, ErrorExamActivity.class);
        intent.putExtra("singleFileName", singleFileName + "error.txt");
        intent.putExtra("multipleFileName", multipleFileName + "error.txt");
        intent.putExtra("judgeFileName", judgeFileName + "error.txt");
        intent.putExtra("fileDirName", fileDirName);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        singleFileName = intent.getStringExtra("singleFileName");
        multipleFileName = intent.getStringExtra("multipleFileName");
        judgeFileName = intent.getStringExtra("judgeFileName");
        fileDirName = intent.getStringExtra("fileDirName");
        setContentView(R.layout.exam_error);
        getData();
        findView();
        initData();
        setButtonListener();
    }

    private void findView() {
        testListExpandableListView = (ExpandableListView) findViewById(R.id.test_expandableListView);
        testProblemTextView = (TextView) findViewById(R.id.test_problem_textview);
        testSelectCheckBoxA = (CheckBox) findViewById(R.id.test_choice_A_checkbox);
        testSelectCheckBoxB = (CheckBox) findViewById(R.id.test_choice_B_checkbox);
        testSelectCheckBoxC = (CheckBox) findViewById(R.id.test_choice_C_checkbox);
        testSelectCheckBoxD = (CheckBox) findViewById(R.id.test_choice_D_checkbox);
        nextButton = (Button) findViewById(R.id.bt_next_title);
        preButton = (Button) findViewById(R.id.bt_pre_title);
        showAnswerButton = (TextView) findViewById(R.id.tv_show_answer);
        answerTextView = (TextView) findViewById(R.id.tv_answer);
        testSkipNextAuto = (CheckBox) findViewById(R.id.skip_next_auto);
        titleTv = (TextView) findViewById(R.id.title_text);
        clearTv = (TextView) findViewById(R.id.title_right_text);
        hasProLayout = (RelativeLayout) findViewById(R.id.pratice_layout);
        noProLayout = (RelativeLayout) findViewById(R.id.null_layout);
    }

    private void getData() {
        giveDataToGroup(SINGLE_CHOICE);
        giveDataToGroup(MULTIPLE_CHOICE);
        giveDataToGroup(JUDGE_CHOICE);

    }

    private void initData() {
        titleTv.setText("错题集");
        clearTv.setText("清空错题集");
        initArraysAndAdapter();
        testListExpandableListView
                .setOnChildClickListener(new TestExpandableChildClickListener());
        testListExpandableListView.setGroupIndicator(null);
        new TestExpandableChildClickListener().onChildClick(null, null,
                SINGLE_CHOICE, 0, 0);
//        initUnitSpinner();
        if (!hasFile) {
            noProLayout.setVisibility(View.VISIBLE);
            hasProLayout.setVisibility(View.GONE);
            clearTv.setVisibility(View.GONE);
        } else {
            noProLayout.setVisibility(View.GONE);
            clearTv.setVisibility(View.VISIBLE);
            hasProLayout.setVisibility(View.VISIBLE);
        }

    }

    private void setButtonListener() {
        testListExpandableListView
                .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                                              @Override
                                              public void onGroupExpand(int groupPosition) {
                                                  int num = testListAdapter.getGroupCount();
                                                  for (int i = 0; i < num; i++) {
                                                      if (i != groupPosition) {
                                                          testListExpandableListView.collapseGroup(i);
                                                      }
                                                  }
                                              }
                                          }

                );
        nextButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              int endLength;
                                              int groupLength;
                                              if (answerSave[2].length != 0) {
                                                  endLength = answerSave[2].length;
                                                  groupLength = 3;
                                              } else if (answerSave[1].length != 0) {
                                                  endLength = answerSave[1].length;
                                                  groupLength = 2;
                                              } else {
                                                  endLength = answerSave[0].length;
                                                  groupLength = 1;
                                              }
                                              if (position[GROUP_POSITION] >= groupLength - 1
                                                      && position[CHILD_POSITION] >= endLength - 1) {// 最后一题时
                                                  Log.d("下一题", "提示");
                                                  Toast.makeText(mContext, "没有下一题辣,点击左上角可以选择单元呦",
                                                          Toast.LENGTH_SHORT).show();

                                              } else if (position[GROUP_POSITION] < groupLength - 1
                                                      && position[CHILD_POSITION] >= answerSave[position[GROUP_POSITION]].length - 1) {// 每一组的最后一题
                                                  Log.d("下一题", "下一题");
                                                  new TestExpandableChildClickListener().onChildClick(null,
                                                          null, position[GROUP_POSITION] + 1, 0, 0);
                                              } else {
                                                  new TestExpandableChildClickListener().onChildClick(null,
                                                          null, position[GROUP_POSITION],
                                                          position[CHILD_POSITION] + 1, 0);
                                              }
                                              //展开当前位置的列表
//                                              expandCurrentGroup();
                                          }
                                      }

        );
        preButton.setOnClickListener(new View.OnClickListener()

                                     {
                                         @Override
                                         public void onClick(View v) {
                                             if (position[GROUP_POSITION] <= 0
                                                     && position[CHILD_POSITION] == 0) {// 选择题第一题时
                                                 Toast.makeText(mContext, "没有上一题了呦", Toast.LENGTH_SHORT)
                                                         .show();
                                             } else if (position[GROUP_POSITION] > 0
                                                     && position[CHILD_POSITION] == 0) {// 一组中的第一题
                                                 new TestExpandableChildClickListener()
                                                         .onChildClick(
                                                                 null,
                                                                 null,
                                                                 position[GROUP_POSITION] - 1,
                                                                 answerSave[position[GROUP_POSITION] - 1].length - 1,
                                                                 0);
                                             } else {// 非一组中的第一题,选择上一题位置
                                                 new TestExpandableChildClickListener().onChildClick(null,
                                                         null, position[GROUP_POSITION],
                                                         position[CHILD_POSITION] - 1, 0);
                                             }
                                             expandCurrentGroup();
                                         }
                                     }

        );
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!showAnswer) {// 如果目前不显示答案
                    showAnswer = true;
                    showAnswerButton.setText("隐藏答案");
                    answerTextView.setVisibility(View.VISIBLE);
                } else {
                    showAnswer = false;
                    showAnswerButton.setText("显示答案");
                    answerTextView.setVisibility(View.GONE);
                }
            }
        });
        /**
         * 清空错题集
         */
        clearTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("清空错题");
                builder.setMessage("您确定要清空错题集吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String path = mContext.getFilesDir() + "/Dxsbang/"
                                + fileDirName + "/";
                        File targetFile1 = new File(path, singleFileName);
                        File targetFile2 = new File(path, multipleFileName);
                        File targetFile3 = new File(path, judgeFileName);
                        if (targetFile1.exists()) {
                            targetFile1.delete();
                        }
                        if (targetFile2.exists()) {
                            targetFile2.delete();
                        }
                        if (targetFile3.exists()) {
                            targetFile3.delete();
                        }
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }

    /**
     * 作用:
     * 1.初始化单选题,加载题目文字
     * 2.检查该题存储答案来调整点击状态
     *
     * @param selectType
     * @param childPosition 题目ID
     */

    private void initSingleSelection(int selectType, int childPosition) {
        initProblem(selectType, childPosition);
        lockState = false;// 上锁,不使用监听器
        for (int i = 0; i < testSelectCheckBoxId.length; i++) {
            CheckBox tempCheckBox = (CheckBox) findViewById(testSelectCheckBoxId[i]);
            if (answerSave[SINGLE_CHOICE][childPosition][i + 1] == CHECKED)
                tempCheckBox.setChecked(true);
            else
                tempCheckBox.setChecked(false);
        }
        lockState = true;// 开锁,可以使用监听器
    }

    private void initMultipleSelection(int selectType, int childPosition) {
        initProblem(selectType, childPosition);
        lockState = false;
        for (int i = 0; i < testSelectCheckBoxId.length; i++) {
            CheckBox tempCheckBox = (CheckBox) findViewById(testSelectCheckBoxId[i]);
            if (answerSave[MULTIPLE_CHOICE][childPosition][i + 1] == CHECKED)
                tempCheckBox.setChecked(true);
            else
                tempCheckBox.setChecked(false);
        }
        lockState = true;
    }

    private void initJudgeSelection(int selectType, int childPosition) {
        initProblem(selectType, childPosition);
        lockState = false;
        for (int i = 0; i < 2; i++) {
            CheckBox tempCheckBox = (CheckBox) findViewById(testSelectCheckBoxId[i]);
            if (answerSave[JUDGE_CHOICE][childPosition][i + 1] == CHECKED)
                tempCheckBox.setChecked(true);
            else
                tempCheckBox.setChecked(false);
        }
        lockState = true;

    }

    private void initProblem(int selectType, int childPosition) {
        switch (selectType) {
            case SINGLE_CHOICE:
                testProblemTextView.setText(Integer
                        .toString(position[CHILD_POSITION] + 1)
                        + "."
                        + angleSelectSubject[childPosition].getTitle());
                testSelectCheckBoxA.setText(angleSelectSubject[childPosition]
                        .getA());
                testSelectCheckBoxB.setText(angleSelectSubject[childPosition]
                        .getB());
                testSelectCheckBoxC.setText(angleSelectSubject[childPosition]
                        .getC());
                testSelectCheckBoxD.setText(angleSelectSubject[childPosition]
                        .getD());
                answerTextView.setText("答案:"
                        + angleSelectSubject[childPosition].getAnswer());
                testSelectCheckBoxC.setVisibility(View.VISIBLE);
                testSelectCheckBoxD.setVisibility(View.VISIBLE);
                break;
            case MULTIPLE_CHOICE:
                testProblemTextView.setText(Integer
                        .toString(position[CHILD_POSITION] + 1)
                        + "."
                        + multipleSelectSubject[childPosition].getTitle());
                testSelectCheckBoxA.setText(multipleSelectSubject[childPosition]
                        .getA());
                testSelectCheckBoxB.setText(multipleSelectSubject[childPosition]
                        .getB());
                testSelectCheckBoxC.setText(multipleSelectSubject[childPosition]
                        .getC());
                testSelectCheckBoxD.setText(multipleSelectSubject[childPosition]
                        .getD());
                answerTextView.setText("答案:"
                        + multipleSelectSubject[childPosition].getAnswer());
                testSelectCheckBoxC.setVisibility(View.VISIBLE);
                testSelectCheckBoxD.setVisibility(View.VISIBLE);
                testProblemTextView.setTag(multipleSelectSubject[childPosition]
                        .getAnswer());
                break;
            case JUDGE_CHOICE:
                testProblemTextView.setText(Integer
                        .toString(position[CHILD_POSITION] + 1)
                        + "."
                        + judgeSelectSubject[childPosition].getTitle());
                testSelectCheckBoxA.setText("A.正确");
                testSelectCheckBoxB.setText("B.错误");
                answerTextView.setText("答案:"
                        + judgeSelectSubject[childPosition].getAnswer());
                testSelectCheckBoxC.setVisibility(View.GONE);
                testSelectCheckBoxD.setVisibility(View.GONE);
        }
    }

    /**
     * @param groupPosition
     * @param childPosition
     * @return 若答案正确返回true 否则返回false
     */
    private boolean isTrue(int groupPosition, int childPosition) {
        if (answerSave[groupPosition][childPosition][0] == 0) {// 没有填写答案时
            return false;
        } else {
            String tempAnswer[] = {"A", "B", "C", "D"};
            // 判断题
            if (groupPosition == JUDGE_CHOICE) {
                for (int i = 0; i < 2; i++) {
                    if (answerSave[JUDGE_CHOICE][childPosition][i + 1] == CHECKED) {// 如果找到答案
                        if (judgeSelectSubject[childPosition].getAnswer()
                                .contains(tempAnswer[i])) {
                            return true;
                        } else
                            return false;
                    }
                }
            }
            // 选择题
            if (groupPosition == SINGLE_CHOICE) {
                for (int i = 0; i < 4; i++) {
                    if (answerSave[SINGLE_CHOICE][childPosition][i + 1] == CHECKED) {
                        if (angleSelectSubject[childPosition].getAnswer()
                                .contains(tempAnswer[i])) {
                            return true;
                        } else
                            return false;
                    }
                }
            }
            if (groupPosition == MULTIPLE_CHOICE) {
                for (int i = 0; i < 4; i++) {
                    // 正确答案没有选中
                    if (multipleSelectSubject[childPosition].getAnswer()
                            .contains(tempAnswer[i])
                            && answerSave[MULTIPLE_CHOICE][childPosition][i + 1] == UNCHECKED) {
                        if (position[GROUP_POSITION] == 1
                                && position[CHILD_POSITION] == 1) {
                        }
                        return false;
                    }
                    // 选中答案没有选中的选项
                    if (answerSave[MULTIPLE_CHOICE][childPosition][i + 1] == CHECKED
                            && !multipleSelectSubject[childPosition]
                            .getAnswer().contains(tempAnswer[i])) {
                        if (position[GROUP_POSITION] == 1
                                && position[CHILD_POSITION] == 1) {
                        }
                        return false;
                    }
                    if (i == 3) {// 循环结束没有检验到错误答案,回答就正确
                        return true;
                    }
                }
            }
        }
        return false;// 理论上不会执行到此位置
    }

    private void adaptAnswerColor() {
        if (isTrue(position[GROUP_POSITION], position[CHILD_POSITION])) {// 判断答案是否正确调整答案颜色
            answerTextView.setTextColor(Color.BLACK);
        } else {
            answerTextView.setTextColor(Color.RED);
        }
    }

    /**
     * 输入相应题型对应的名称
     *
     * @param fileName
     * @return
     */
    private ArrayList<String> getSaveList(String fileName) {
        Log.d("测试", fileName);

        ArrayList<String> list = new ArrayList<String>();
        try {
            String path = mContext.getFilesDir() + "/Dxsbang/"
                    + fileDirName + "/";
       /*     File targetFile = new File(path, fileName);
            if (targetFile.exists()) {
                FileInputStream fis = new FileInputStream(targetFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ArrayList<String> tempList = new ArrayList();
                while ((tempList = (ArrayList<String>) ois.readObject()).size() != 0) {
                Log.d("文件", "读取一次");
                tempList = (ArrayList<String>) ois.readObject();
                list.addAll(tempList);
                for (int i = 0; i < tempList.size(); i++) {
                    Log.d("文件", list.get(i));
                }
                }
                ois.close();
                fis.close();
            } else {
                for (int i = 0; i < 6; i++) {
                    list.add("尚且没有此类错题");
                }
            }
            */

            FileInputStream fileInputStream = null;
            File targetFile = new File(path, fileName);
            if (targetFile.exists()) {
                fileInputStream = new FileInputStream(targetFile);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(
//                        fileInputStream, "GB2312"));
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                String line = "";
                Log.d("长度", "while");
                while ((line = reader.readLine()) != null) {
                    if (!"".equals(line.trim())) {
                        list.add(line);
                    }
                }
                fileInputStream.close();
            } else {
                if (list.size() < 6) {
                    for (int i = 0; i < 6; i++) {
                        list.add("没有错题记录");
                        hasFile = false;
                    }
                } else {
                    Log.d("长度", "长度>6" + "  " + list.size());
                }
                Log.d("长度", "执行");
            }
        } catch (Exception e) {
            Log.e("错题读取错误", e.toString());
            e.printStackTrace();
        }
        Log.d("长度", list.size() + "");
        return list;
    }
//        } catch (Exception e) {
//            Log.e("读取文件错误", e.toString());
//        }
//        return list;
//    }


    private void giveDataToGroup(int choiceType) {
        final int title = 0;
        final int A = 1;
        final int B = 2;
        final int C = 3;
        final int D = 4;
        final int answer = 5;
        switch (choiceType) {
            case SINGLE_CHOICE: {
                ArrayList<String> list = getSaveList(singleFileName);
                singleCount = list.size() / 6;// 获得长度
                angleSelectSubject = new Subject[singleCount];
                for (int i = 0; i < singleCount; i++) {
                    String[] strings = new String[6];
                    for (int j = 6; j > 0; j--) {
                        strings[6 - j] = list.get(6 * (i + 1) - j);// 按照题目,四个选项,答案的顺序赋值到Strings数组
                    }

                    angleSelectSubject[i] = new Subject(strings[A], strings[B],
                            strings[C], strings[D], strings[title], strings[answer]);
                }
            }
            break;
            case MULTIPLE_CHOICE: {
                ArrayList<String> list = getSaveList(multipleFileName);
                multipleCount = list.size() / 6;// 获得长度
                multipleSelectSubject = new Subject[multipleCount];
                for (int i = 0; i < multipleCount; i++) {
                    String[] strings = new String[6];
                    for (int j = 6; j > 0; j--) {
                        strings[6 - j] = list.get(6 * (i + 1) - j);// 按照题目,四个选项,答案的顺序赋值到Strings数组
                    }
                    multipleSelectSubject[i] = new Subject(strings[A], strings[B],
                            strings[C], strings[D], strings[title], strings[answer]);
                }
            }
            break;
            case JUDGE_CHOICE: {
                ArrayList<String> list = getSaveList(judgeFileName);
                judgeCount = list.size() / 2;// 获得长度
                judgeSelectSubject = new Subject[judgeCount];
                for (int i = 0; i < judgeCount; i++) {
                    String[] strings = new String[2];
                    for (int j = 2; j > 0; j--) {
                        strings[2 - j] = list.get(2 * (i + 1) - j);// 共两组值
                    }
                    judgeSelectSubject[i] = new Subject(strings[title], strings[1]);// 此处answer的索引为1
                }
            }
            break;
            default:
        }
    }

    private void expandCurrentGroup() {
        if (!testListExpandableListView
                .isGroupExpanded(position[GROUP_POSITION])) {
            testListExpandableListView.expandGroup(position[GROUP_POSITION]);
        }
    }

//    private void initUnitSpinner() {
//        int unitCount = praticeTestActivity.getUnitCount();
//        final ArrayList<String> list = new ArrayList<String>();
//        // 此处修改
//        if ("historySingleChoice".equals(singleFileName)) {// 如果是史纲
//            for (int i = 0; i < unitCount - 2; ++i) {
//                Log.d("名字", singleFileName);
//                list.add("第" + Integer.toString(i + 1) + "章");
//            }
//            list.add("中篇");
//            list.add("下篇");
//        } else if ("maSingleChoice".equals(singleFileName)) {
//            for (int i = 0; i < unitCount - 1; ++i) {
//                list.add("第" + Integer.toString(i + 1) + "章");
//            }
//            list.add("绪论");
//        } else if ("mindSingleChoice".equals(singleFileName)) {
//            for (int i = 0; i < unitCount - 1; ++i) {
//                list.add("第" + Integer.toString(i + 1) + "章");
//            }
//            list.add("绪论");
//        } else {
//
//            for (int i = 0; i < unitCount; ++i) {
//                Log.d("名字", "else");
//                list.add("第" + Integer.toString(i + 1) + "章");
//            }
//        }
//        ArrayAdapter adapter = new ArrayAdapter(mContext,
//                R.layout.exam_spinner, R.id.spinnerText, list);
//        unitSelectSpinner.setAdapter(adapter);
//        unitSelectSpinner
//                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent,
//                                               View view, int position, long id) {
//                        currentUnit = position;
//                        preferencesEditor.putInt(SHARE_UNIT, currentUnit);
//                        preferencesEditor.commit();
//                        giveDataToGroup(SINGLE_CHOICE);
//                        giveDataToGroup(MULTIPLE_CHOICE);
//                        giveDataToGroup(JUDGE_CHOICE);
//                        if (firstChangeSpinner == false) {
//                            Log.d("测试", "false" + position);
//                            initArraysAndAdapter();
//                        }
//                        if (firstChangeSpinner == true) {
//                            Log.d("测试", "true" + position);
//                            firstChangeSpinner = false;
//                        }
//
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//                    }
//                });
//    }

    private void initArraysAndAdapter() {
        position = new int[]{0, 0};
        answerSave = new int[3][][];
        answerSave[SINGLE_CHOICE] = new int[angleSelectSubject.length][5];
        answerSave[MULTIPLE_CHOICE] = new int[multipleSelectSubject.length][5];
        answerSave[JUDGE_CHOICE] = new int[judgeSelectSubject.length][3];
        answerTrue = new boolean[3][];
        answerTrue[SINGLE_CHOICE] = new boolean[angleSelectSubject.length];
        answerTrue[MULTIPLE_CHOICE] = new boolean[multipleSelectSubject.length];
        answerTrue[JUDGE_CHOICE] = new boolean[judgeSelectSubject.length];
        tempAnswerTrue = new boolean[3][];
        tempAnswerTrue[SINGLE_CHOICE] = new boolean[angleSelectSubject.length];
        tempAnswerTrue[MULTIPLE_CHOICE] = new boolean[multipleSelectSubject.length];
        tempAnswerTrue[JUDGE_CHOICE] = new boolean[judgeSelectSubject.length];
        for (int i = 0; i < tempAnswerTrue[SINGLE_CHOICE].length; i++) {
            tempAnswerTrue[SINGLE_CHOICE][i] = true;
        }
        for (int i = 0; i < tempAnswerTrue[MULTIPLE_CHOICE].length; i++) {
            tempAnswerTrue[MULTIPLE_CHOICE][i] = true;
        }
        for (int i = 0; i < tempAnswerTrue[JUDGE_CHOICE].length; i++) {
            tempAnswerTrue[JUDGE_CHOICE][i] = true;
        }// 为临时正确答案数组赋值为ture,防止提交之前颜色变化
        testListAdapter = new ExamAdapter(mContext,
                angleSelectSubject.length, multipleSelectSubject.length,
                judgeSelectSubject.length, UNREAD_COLOR, READ_COLOR,
                READING_COLOR, position, answerSave, tempAnswerTrue);
        Log.i("ceshi", "initArraysAndAdapter: "+angleSelectSubject.length);
//        testListExpandableListView.setAdapter(testListAdapter);
//        testListExpandableListView.expandGroup(0);
        initProblem(SINGLE_CHOICE, 0);// 刷新第一道题
        initSingleSelection(SINGLE_CHOICE, 0);//刷新显示的答案的check
        //调整答案显示状态
        showAnswer = false;
        showAnswerAuto(false);
        showAnswerButton.setText("显示答案");
    }

    private void showAnswerAuto(boolean showAnswer) {
        if (showAnswer) {
            answerTextView.setVisibility(View.VISIBLE);
        } else
            answerTextView.setVisibility(View.GONE);
    }

    /**
     * 切换题号时,更改显示答案状态
     */
    private void showAnswerChange() {
        if (!showAnswer
                && answerSave[position[GROUP_POSITION]][position[CHILD_POSITION]][0] == 0) {// 如果不是显示答案状态并且未答题,切换时更改为不显示答案
            if (showAnswerButton.getVisibility() == View.VISIBLE)
                showAnswerAuto(false);
        } else if (answerSave[position[GROUP_POSITION]][position[CHILD_POSITION]][0] != 0) {
            showAnswerAuto(true);
        }
    }


    private class testCheckBoxCheckedChangeListener implements
            CompoundButton.OnCheckedChangeListener {
        private int selectType;
        private int childPosition;

        public testCheckBoxCheckedChangeListener(int childPosition,
                                                 int selectType) {
            this.childPosition = childPosition;
            this.selectType = selectType;
        }

        /**
         * @param buttonView
         * @param isChecked
         */
        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            final int A_ANSWER = 1;
            final int B_ANSWER = 2;
            final int C_ANSWER = 3;
            final int D_ANSWER = 4;
            // 假如为锁定状态,不触发监听器
            if (!lockState) {
                return;
            }
            // 将做题情况设置为未选中,如果有选中选项则变为选中
            answerSave[selectType][childPosition][0] = 0;
            // 更改选项
            switch (selectType) {
                case SINGLE_CHOICE: {
                    switch (buttonView.getId()) {
                        case R.id.test_choice_A_checkbox: {
                            if (isChecked) {
                                showAnswerAuto(true);
                                testCheckedSingleChioce(R.id.test_choice_A_checkbox);
                            } else
                                answerSave[SINGLE_CHOICE][childPosition][A_ANSWER] = UNCHECKED;
                            break;
                        }
                        case R.id.test_choice_B_checkbox: {
                            if (isChecked) {
                                showAnswerAuto(true);
                                testCheckedSingleChioce(R.id.test_choice_B_checkbox);
                            } else
                                answerSave[SINGLE_CHOICE][childPosition][B_ANSWER] = UNCHECKED;
                            break;
                        }
                        case R.id.test_choice_C_checkbox: {
                            if (isChecked) {
                                showAnswerAuto(true);
                                testCheckedSingleChioce(R.id.test_choice_C_checkbox);
                            } else
                                answerSave[SINGLE_CHOICE][childPosition][C_ANSWER] = UNCHECKED;
                            break;
                        }
                        case R.id.test_choice_D_checkbox: {
                            if (isChecked) {
                                showAnswerAuto(true);
                                testCheckedSingleChioce(R.id.test_choice_D_checkbox);
                            } else
                                answerSave[SINGLE_CHOICE][childPosition][D_ANSWER] = UNCHECKED;
                            break;
                        }
                        default:
                            throw new ThreadDeath();
                    }
                }

                break;
                case MULTIPLE_CHOICE: {
                    switch (buttonView.getId()) {
                        case R.id.test_choice_A_checkbox: {
                            if (testSelectCheckBoxA.isChecked()) {
                                answerSave[MULTIPLE_CHOICE][childPosition][A_ANSWER] = CHECKED;
                            } else
                                answerSave[MULTIPLE_CHOICE][childPosition][A_ANSWER] = UNCHECKED;
                            break;
                        }
                        case R.id.test_choice_B_checkbox: {
                            if (testSelectCheckBoxB.isChecked())
                                answerSave[MULTIPLE_CHOICE][childPosition][B_ANSWER] = CHECKED;
                            else
                                answerSave[MULTIPLE_CHOICE][childPosition][B_ANSWER] = UNCHECKED;
                            break;
                        }
                        case R.id.test_choice_C_checkbox: {
                            if (testSelectCheckBoxC.isChecked())
                                answerSave[MULTIPLE_CHOICE][childPosition][C_ANSWER] = CHECKED;
                            else
                                answerSave[MULTIPLE_CHOICE][childPosition][C_ANSWER] = UNCHECKED;
                            break;
                        }
                        case R.id.test_choice_D_checkbox: {
                            if (testSelectCheckBoxD.isChecked())
                                answerSave[MULTIPLE_CHOICE][childPosition][D_ANSWER] = CHECKED;
                            else
                                answerSave[MULTIPLE_CHOICE][childPosition][D_ANSWER] = UNCHECKED;
                            break;
                        }
                        default:
                            throw new ThreadDeath();
                    }
                    break;
                }
                case JUDGE_CHOICE: {
                    switch (buttonView.getId()) {
                        case R.id.test_choice_A_checkbox: {
                            if (testSelectCheckBoxA.isChecked()) {
                                showAnswerAuto(true);
                                testSelectCheckBoxB.setChecked(false);
                                answerSave[JUDGE_CHOICE][childPosition][B_ANSWER] = UNCHECKED;
                                answerSave[JUDGE_CHOICE][childPosition][A_ANSWER] = CHECKED;
                            } else
                                answerSave[JUDGE_CHOICE][childPosition][A_ANSWER] = UNCHECKED;
                            break;
                        }
                        case R.id.test_choice_B_checkbox: {
                            if (testSelectCheckBoxB.isChecked()) {
                                showAnswerAuto(true);
                                testSelectCheckBoxA.setChecked(false);
                                answerSave[JUDGE_CHOICE][childPosition][A_ANSWER] = UNCHECKED;
                                answerSave[JUDGE_CHOICE][childPosition][B_ANSWER] = CHECKED;
                            } else
                                answerSave[JUDGE_CHOICE][childPosition][B_ANSWER] = UNCHECKED;
                            break;
                        }
                        default:
                            throw new ThreadDeath();
                    }
                    break;
                }
                default:
                    throw new ThreadDeath();
            }
            // 检测是否已做
            testChangeColor();
            adaptAnswerColor();// 调整当前位置颜色,换题调整颜色在列表监听器处
            //若答案正确则跳转到下一题
            if (isTrue(selectType, childPosition)) {
                answerTrue[selectType][childPosition] = true;
            }
            nextProblemAuto();// 自动跳转下一题

        }

        private void nextProblemAuto() {
            if (testSkipNextAuto.isChecked() && selectType != MULTIPLE_CHOICE) {// 如果被选中,跳转到下一题
                if (answerSave[selectType][childPosition][0] == 1 && isTrue(position[GROUP_POSITION], position[CHILD_POSITION])) {// 已经选中
                    nextButton.performClick();
                }
            }
        }

        /**
         * 1.更改本题存储的答案 2.把未选中选项设置为未选中保证单选
         *
         * @param checkedCheckBoxId 被选中的CheckBox的ID
         */
        private void testCheckedSingleChioce(int checkedCheckBoxId) {
            for (int i = 0; i < 4; i++) {
                CheckBox checkBox = (CheckBox) findViewById(testSelectCheckBoxId[i]);
                if (testSelectCheckBoxId[i] == checkedCheckBoxId) {
                    answerSave[SINGLE_CHOICE][childPosition][i + 1] = CHECKED;// A选项值为1,比i值永远大1
                } else {
                    answerSave[SINGLE_CHOICE][childPosition][i + 1] = UNCHECKED;
                    checkBox.setChecked(false);
                }
            }
        }

        /**
         * 检测checkBox中是否有被选中的,调整Listview颜色
         */
        private void testChangeColor() {
            if (selectType == SINGLE_CHOICE || selectType == MULTIPLE_CHOICE) {
                for (int i = 1; i < 5; i++) {// 索引1234分别代表ABCD 索引0为点击情况 总共五个数据
                    if (answerSave[selectType][childPosition][i] == CHECKED) {// 有被选择的选项
                        answerSave[selectType][childPosition][0] = 1;
                        break;
                    }
                }
            } else if (selectType == JUDGE_CHOICE) {
                for (int i = 1; i < 3; i++) {// 索引12为正误两选项,索引0位点击情况,共3个数据
                    if (answerSave[selectType][childPosition][i] == CHECKED) {
                        answerSave[selectType][childPosition][0] = 1;
                        break;
                    }
                }
            } else
                throw new ThreadDeath();// 若非这三类题 则抛出错误
            // 监听适配器的改变,改变颜色
            testListAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 列表监听器 为四个选项各自设置监听器,在此处设置监听器,是为了随时记录childPosition
     */
    private class TestExpandableChildClickListener implements
            ExpandableListView.OnChildClickListener {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {
            position[GROUP_POSITION] = groupPosition;
            position[CHILD_POSITION] = childPosition;
            adaptAnswerColor();// 改变题号时改变题颜色
            testListAdapter.notifyDataSetChanged();
            switch (groupPosition) {
                case SINGLE_CHOICE: {
                    initSingleSelection(SINGLE_CHOICE, childPosition);
                    for (int i = 0; i < testSelectCheckBoxId.length; i++) {
                        CheckBox checkBox = (CheckBox) findViewById(testSelectCheckBoxId[i]);
                        checkBox.setOnCheckedChangeListener(new testCheckBoxCheckedChangeListener(
                                childPosition, SINGLE_CHOICE));
                    }
                    showAnswerChange();
                    break;
                }
                case MULTIPLE_CHOICE: {
                    initMultipleSelection(MULTIPLE_CHOICE, childPosition);
                    for (int i = 0; i < testSelectCheckBoxId.length; i++) {
                        CheckBox checkBox = (CheckBox) findViewById(testSelectCheckBoxId[i]);
                        checkBox.setOnCheckedChangeListener(new testCheckBoxCheckedChangeListener(
                                childPosition, MULTIPLE_CHOICE));
                    }
                    showAnswerChange();
                    break;
                }
                case JUDGE_CHOICE: {
                    initJudgeSelection(JUDGE_CHOICE, childPosition);
                    for (int i = 0; i < 2; i++) {
                        CheckBox checkBox = (CheckBox) findViewById(testSelectCheckBoxId[i]);
                        checkBox.setOnCheckedChangeListener(new testCheckBoxCheckedChangeListener(
                                childPosition, JUDGE_CHOICE));
                    }
                    showAnswerChange();
                    break;
                }
                default:
                    throw new ThreadDeath();
            }
            return true;
        }
    }

}



