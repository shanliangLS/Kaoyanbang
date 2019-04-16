package hehut.scse.kaoyanbang.TabFragment.Practice.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.TabFragment.Practice.ExamActivity.ExamDetailsActivity;
import hehut.scse.kaoyanbang.TabFragment.Practice.MyAdapter.ExamAdapter;
import hehut.scse.kaoyanbang.TabFragment.Practice.Util.Subject;

public class TestFragment extends Fragment {

    private final int singleProblemCount = 20;
    private final int multipleProblemCount = 20;
    private final int judgeProblemCount = 10;
    private final int UNREAD_COLOR = Color.GRAY;
    private final int READ_COLOR = R.color.sky_l;
    private final int READING_COLOR = Color.GREEN;
    private final int SINGLE_CHOICE = 0;
    private final int MULTIPLE_CHOICE = 1;
    private final int JUDGE_CHOICE = 2;
    private final int GROUP_POSITION = 0;
    private final int CHILD_POSITION = 1;
    //判断点击状态
    private final int CHECKED = 1;
    private final int UNCHECKED = 0;
    boolean lockState = false;
    boolean showAnswer = false;
    ExamAdapter testListAdapter;
    ExpandableListView testListExpandableListView;//左侧题目目录
    TextView testProblemTextView;//题目文本框
    TextView answerTextView;
    TextView testEndTextView;//提交按钮
    Button nextButton;
    Button preButton;
    Button showAnswerButton;
    CheckBox testSelectCheckBoxA;
    CheckBox testSelectCheckBoxB;
    CheckBox testSelectCheckBoxC;
    CheckBox testSelectCheckBoxD;
    CheckBox testSkipNextAuto;
    AlertDialog.Builder submitAlertDialogBuilder;
    ExamDetailsActivity praticeTestActivity;
    ArrayList<String> singleProblem;
    ArrayList<String> multipleProblem;
    ArrayList<String> judgeProblem;
    private View view;
    private Context mContext;
    private int[][][] answerSave;//0的位置为是否做题的记录
    private boolean[][] answerTrue;
    private boolean[][] tempAnswerTrue;
    private Subject[] angleSelectSubject;
    private Subject[] multipleSelectSubject;
    private Subject[] judgeSelectSubject;
    private ArrayList<Subject> angleSelectSubjectList=new ArrayList<Subject>();
    private ArrayList<Subject> multipleSelectSubjectList=new ArrayList<Subject>();
    private ArrayList<Subject> judgeSelectSubjectList=new ArrayList<Subject>();
    private int singleCount = 0;
    private int multipleCount = 0;
    private int judgeCount = 0;
    private String singleFileNames;
    private String multipleFileNames;
    private String judgeFileNames;
    private int[] position;
    private int[] testSelectCheckBoxId =

            {
                    R.id.test_choice_A_checkbox, R.id.test_choice_B_checkbox, R.id.test_choice_C_checkbox, R.id.test_choice_D_checkbox
            };

    //    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO �Զ����ɵķ������
        if (view == null) {
            praticeTestActivity = (ExamDetailsActivity) getActivity();
            singleFileNames = praticeTestActivity.getSingleFileNames();
            multipleFileNames = praticeTestActivity.getMultipleFileNames();
            judgeFileNames = praticeTestActivity.getJudgeFileNames();
            view = inflater.inflate(R.layout.exam_test, null);
            mContext = getActivity();
            getData();
            findView();
            initData();
            setButtonListener();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO �Զ����ɵķ������
        super.onActivityCreated(savedInstanceState);
    }

    private void findView() {
        testListExpandableListView = (ExpandableListView) view.findViewById(R.id.test_expandableListView);
        testProblemTextView = (TextView) view.findViewById(R.id.test_problem_textview);
        testEndTextView = (TextView) view.findViewById(R.id.test_end_button);
        testSelectCheckBoxA = (CheckBox) view.findViewById(R.id.test_choice_A_checkbox);
        testSelectCheckBoxB = (CheckBox) view.findViewById(R.id.test_choice_B_checkbox);
        testSelectCheckBoxC = (CheckBox) view.findViewById(R.id.test_choice_C_checkbox);
        testSelectCheckBoxD = (CheckBox) view.findViewById(R.id.test_choice_D_checkbox);
        nextButton = (Button) view.findViewById(R.id.bt_next_title);
        preButton = (Button) view.findViewById(R.id.bt_pre_title);
        showAnswerButton = (Button) view.findViewById(R.id.tv_show_answer);
        answerTextView = (TextView) view.findViewById(R.id.tv_answer);
        testSkipNextAuto = (CheckBox) view.findViewById(R.id.skip_next_auto);
    }

    private void getData() {
        giveDataToGroup(SINGLE_CHOICE);//获得题目数据
        giveDataToGroup(MULTIPLE_CHOICE);
        giveDataToGroup(JUDGE_CHOICE);
        submitAlertDialogBuilder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_DARK);//THEME_HOLO_DARK


    }

    private void initData() {
        initArraysAndAdapter();
        testListExpandableListView.setOnChildClickListener(new TestExpandableChildClickListener());
        testListExpandableListView.setGroupIndicator(null);
        new TestExpandableChildClickListener().onChildClick(null, null, SINGLE_CHOICE, 0, 0);//angleSelectSubject
    }

    private void setButtonListener() {
        testListExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int num = testListExpandableListView.getExpandableListAdapter().getGroupCount();
                for (int i = 0; i < num; i++) {
                    if (i != groupPosition) {
                        testListExpandableListView.collapseGroup(i);
                    }
                }
            }
        });
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
                                                  Toast.makeText(getActivity(), "没有下一题辣,提交按钮在右下角呦",
                                                          Toast.LENGTH_SHORT).show();

                                              } else if (position[GROUP_POSITION] < groupLength - 1
                                                      && position[CHILD_POSITION] >= answerSave[position[GROUP_POSITION]].length - 1) {// 每一组的最后一题
                                                  Log.d("下一题", "下一题");
                                                  new TestExpandableChildClickListener().onChildClick(null,
                                                          null, position[GROUP_POSITION] + 1, 0, 0);
                                              } else {
                                                  Log.i("ceshi", "点击");
                                                  new TestExpandableChildClickListener().onChildClick(null,
                                                          null, position[GROUP_POSITION],
                                                          position[CHILD_POSITION] + 1, 0);
                                                  Log.i("ceshi", "点击1");
                                              }
                                              //展开当前位置的列表
                                              expandCurrentGroup();
                                              Log.i("ceshi", "点击2");
                                          }
                                      }

        );
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position[GROUP_POSITION] <= 0 && position[CHILD_POSITION] == 0) {//选择题第一题时
                    Toast.makeText(getActivity(), "没有上一题了呦", Toast.LENGTH_SHORT).show();
                } else if (position[GROUP_POSITION] > 0 && position[CHILD_POSITION] == 0) {//一组中的第一题
                    new TestExpandableChildClickListener().onChildClick(null, null, position[GROUP_POSITION] - 1, answerSave[position[GROUP_POSITION] - 1].length - 1, 0);
                } else {//非一组中的第一题,选择上一题位置
                    new TestExpandableChildClickListener().onChildClick(null, null, position[GROUP_POSITION], position[CHILD_POSITION] - 1, 0);
                }
                expandCurrentGroup();
            }
        });
        testEndTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasFinish = true;
                for (int i = 0; i < answerSave.length; i++) {
                    for (int j = 0; j < answerSave[i].length; j++) {
                        if (answerSave[i][j][0] == 0) {
                            hasFinish = false;
                            break;
                        }
                    }
                    if (hasFinish == false)
                        break;
                }
                initSubmitAlertDialogBuilder(hasFinish);//显示提示框
            }
        });
        showAnswerButton.setOnClickListener(new showAnswerOnClickListener());
    }

    private void initSubmitAlertDialogBuilder(boolean hasFinish) {
        submitAlertDialogBuilder.setTitle("交卷提醒");
        submitAlertDialogBuilder.setCancelable(true);
        if (!hasFinish) {
            submitAlertDialogBuilder.setMessage("考研帮提醒:\n您尚未解答所有题目,确定交卷吗?");
        } else {
            submitAlertDialogBuilder.setMessage("考研帮帮提醒:\n您确定要交卷吗?");
        }
        submitAlertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new showAnswerOnClickListener().onClick(showAnswerButton);//显示答案按钮的点击事件,来显示答案
                for (int i = 0; i < answerTrue.length; i++) {
                    for (int j = 0; j < answerTrue[i].length; j++) {
                        tempAnswerTrue[i][j] = answerTrue[i][j];
                    }
                }
                testListAdapter.notifyDataSetChanged();
                giveMark();
                testEndTextView.setClickable(false);
                saveAllErrorPro();
            }
        });
        submitAlertDialogBuilder.setNegativeButton("取消", null);
        submitAlertDialogBuilder.show();
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
        lockState = false;//上锁,不使用监听器
        for (int i = 0; i < testSelectCheckBoxId.length; i++) {
            CheckBox tempCheckBox = (CheckBox) view.findViewById(testSelectCheckBoxId[i]);
            if (answerSave[SINGLE_CHOICE][childPosition][i + 1] == CHECKED)
                tempCheckBox.setChecked(true);
            else
                tempCheckBox.setChecked(false);
        }
        lockState = true;//开锁,可以使用监听器
    }

    private void initMultipleSelection(int selectType, int childPosition) {
        initProblem(selectType, childPosition);
        lockState = false;
        for (int i = 0; i < testSelectCheckBoxId.length; i++) {
            CheckBox tempCheckBox = (CheckBox) view.findViewById(testSelectCheckBoxId[i]);
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
            CheckBox tempCheckBox = (CheckBox) view.findViewById(testSelectCheckBoxId[i]);
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
                testProblemTextView.setText(angleSelectSubject[childPosition].getTitle());
                testProblemTextView.setText(Integer.toString(position[CHILD_POSITION] + 1) + "." + angleSelectSubject[childPosition].getTitle());
                testSelectCheckBoxA.setText(angleSelectSubject[childPosition].getA());
                testSelectCheckBoxB.setText(angleSelectSubject[childPosition].getB());
                testSelectCheckBoxC.setText(angleSelectSubject[childPosition].getC());
                testSelectCheckBoxD.setText(angleSelectSubject[childPosition].getD());
                answerTextView.setText(angleSelectSubject[childPosition].getAnswer().trim());
                testSelectCheckBoxC.setVisibility(View.VISIBLE);
                testSelectCheckBoxD.setVisibility(View.VISIBLE);
                break;
            case MULTIPLE_CHOICE:
                testProblemTextView.setText(Integer.toString(position[CHILD_POSITION] + 1) + "." + multipleSelectSubject[childPosition].getTitle());

                testSelectCheckBoxA.setText(multipleSelectSubject[childPosition].getA());
                testSelectCheckBoxB.setText(multipleSelectSubject[childPosition].getB());
                testSelectCheckBoxC.setText(multipleSelectSubject[childPosition].getC());
                testSelectCheckBoxD.setText(multipleSelectSubject[childPosition].getD());
                answerTextView.setText(multipleSelectSubject[childPosition].getAnswer());
                testSelectCheckBoxC.setVisibility(View.VISIBLE);
                testSelectCheckBoxD.setVisibility(View.VISIBLE);
                testProblemTextView.setTag(multipleSelectSubject[childPosition].getAnswer().trim());
                break;
            case JUDGE_CHOICE:
                testProblemTextView.setText(Integer.toString(position[CHILD_POSITION] + 1) + "." + judgeSelectSubject[childPosition].getTitle());
                testSelectCheckBoxA.setText("A.正确");
                testSelectCheckBoxB.setText("B.错误");
                answerTextView.setText("答案:" + judgeSelectSubject[childPosition].getAnswer().trim());
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
        if (answerSave[groupPosition][childPosition][0] == 0) {//没有填写答案时
            return false;
        } else {
            String tempAnswer[] = {"A", "B", "C", "D"};
            //判断题
            if (groupPosition == JUDGE_CHOICE) {
                for (int i = 0; i < 2; i++) {
                    if (answerSave[JUDGE_CHOICE][childPosition][i + 1] == CHECKED) {//如果找到答案
                        if (judgeSelectSubject[childPosition].getAnswer().contains(tempAnswer[i])) {
                            return true;
                        } else return false;
                    }
                }
            }
            //选择题
            if (groupPosition == SINGLE_CHOICE) {
                for (int i = 0; i < 4; i++) {
                    if (answerSave[SINGLE_CHOICE][childPosition][i + 1] == CHECKED) {
                        if (angleSelectSubject[childPosition].getAnswer().contains(tempAnswer[i])) {
                            return true;
                        } else return false;
                    }
                }
            }
            if (groupPosition == MULTIPLE_CHOICE) {
                for (int i = 0; i < 4; i++) {
                    //正确答案没有选中
                    if (multipleSelectSubject[childPosition].getAnswer().contains(tempAnswer[i]) && answerSave[MULTIPLE_CHOICE][childPosition][i + 1] == UNCHECKED) {
                        if (position[GROUP_POSITION] == 1 && position[CHILD_POSITION] == 1) {
                        }
                        return false;
                    }
                    //选中答案没有选中的选项
                    if (answerSave[MULTIPLE_CHOICE][childPosition][i + 1] == CHECKED && !multipleSelectSubject[childPosition].getAnswer().contains(tempAnswer[i])) {
                        if (position[GROUP_POSITION] == 1 && position[CHILD_POSITION] == 1) {
                        }
                        return false;
                    }
                    if (i == 3) {//循环结束没有检验到错误答案,回答就正确
                        return true;
                    }
                }
            }
        }
        return false;//理论上不会执行到此位置
    }

    private void giveMark() {
        final float SCORE = (float) (100 / (answerSave[SINGLE_CHOICE].length + answerSave[MULTIPLE_CHOICE].length + answerSave[JUDGE_CHOICE].length));
        int count = 0;
        int singleCount = 0;
        int multipleCount = 0;
        int judgeCount = 0;
        int singleScore = 0;
        int multipleScore = 0;
        int judgeScore = 0;
        int totalScore = 0;
        for (int i = 0; i < answerSave.length; i++) {
            for (int j = 0; j < answerSave[i].length; j++) {
                if (isTrue(i, j)) {
                    count++;
                    if (i == SINGLE_CHOICE) {
                        singleCount++;
                    } else if (i == MULTIPLE_CHOICE) {
                        multipleCount++;
                    } else if (i == JUDGE_CHOICE) {
                        judgeCount++;
                    }
                }
            }
        }
        singleScore = (int) (singleCount * SCORE);
        multipleScore = (int) (multipleCount * SCORE);
        judgeScore = (int) (judgeCount * SCORE);
        totalScore = (int) (count * SCORE);
        AlertDialog.Builder scoreAlert = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_DARK);
        scoreAlert.setTitle("您的成绩:" + totalScore);
        scoreAlert.setMessage("您的单选题分数为:" + singleScore + "\n您的多选题分数为:" + multipleScore + "\n您的判断题分数为:" + judgeScore);
        scoreAlert.setCancelable(true);
        scoreAlert.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        scoreAlert.show();
    }

    private void adaptAnswerColor() {
        if (isTrue(position[GROUP_POSITION], position[CHILD_POSITION])) {//判断答案是否正确调整答案颜色
            answerTextView.setTextColor(Color.BLACK);
        } else {
            answerTextView.setTextColor(Color.RED);
        }
    }

    /**
     * 获取一个文件中的题库
     *
     * @param fileName
     * @return
     */
    private ArrayList<String> getSaveList(String fileName) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            String path = mContext.getFilesDir()  + "/";//+ "/Dxsbang/" + praticeTestActivity.getFileDirName()
            File targetFile = new File(path, fileName);
            FileInputStream fileInputStream = new FileInputStream(targetFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, "GB2312"));
            String line = null;
            boolean firstRead = true;
            try {
                line = "";
            } catch (Exception e) {
                e.printStackTrace();
            }
            while ((line = reader.readLine()) != null) {
                if (!"".equals(line.trim())) {
                    if (firstRead) {
                        firstRead = false;
                        continue;
                    }
                    list.add(line);
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取一类题所有文件中的题库
     *
     * @param fileNames
     * @param count
     * @return
     */
    private ArrayList<String> getSaveList(String fileNames, int count) {
        Log.i("qwe", "getSaveList: "+fileNames);
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> tempList;
        for (int i = 0; i < count; i++) {
            tempList = getSaveList(fileNames + Integer.toString(i) + ".txt");
            list.addAll(tempList);
//            for (String temp : tempList) {
//                Log.d("文件内容", temp);
//            }
        }
        return list;
    }

    private void giveDataToGroup(int choiceType) {
        final int title = 0;
        final int A = 1;
        final int B = 2;
        final int C = 3;
        final int D = 4;
        final int answer = 5;
        switch (choiceType) {
            case SINGLE_CHOICE: {
                singleProblem = getProblem(singleFileNames, singleProblemCount, 6);
                singleCount = singleProblem.size() / 6;//获得长度
                angleSelectSubject = new Subject[singleCount];
                for (int i = 0; i < singleCount; i++) {
                    String[] strings = new String[6];
                    for (int j = 6; j > 0; j--) {
                        strings[6 - j] = singleProblem.get(6 * (i + 1) - j);//按照题目,四个选项,答案的顺序赋值到Strings数组
                    }
                    angleSelectSubject[i] = new Subject(strings[A], strings[B], strings[C], strings[D], strings[title], strings[answer]);
                    angleSelectSubjectList.add(angleSelectSubject[i]);
                }
            }
            break;
            case MULTIPLE_CHOICE: {
//                ArrayList<String> list = getSaveList(praticeTestActivity.getMultipleFileName());
                multipleProblem = getProblem(multipleFileNames, multipleProblemCount, 6);
                multipleCount = multipleProblem.size() / 6;//获得长度
                multipleSelectSubject = new Subject[multipleCount];
                for (int i = 0; i < multipleCount; i++) {
                    String[] strings = new String[6];
                    for (int j = 6; j > 0; j--) {
                        strings[6 - j] = multipleProblem.get(6 * (i + 1) - j);//按照题目,四个选项,答案的顺序赋值到Strings数组
                    }
                    multipleSelectSubject[i] = new Subject(strings[A], strings[B], strings[C], strings[D], strings[title], strings[answer]);
                    multipleSelectSubjectList.add(multipleSelectSubject[i]);
                }
            }
            break;
            case JUDGE_CHOICE: {
                judgeProblem = getProblem(judgeFileNames, judgeProblemCount, 2);
                judgeCount = judgeProblem.size() / 2;//获得长度
                judgeSelectSubject = new Subject[judgeCount];
                for (int i = 0; i < judgeCount; i++) {
                    String[] strings = new String[2];
                    for (int j = 2; j > 0; j--) {
                        strings[2 - j] = judgeProblem.get(2 * (i + 1) - j);//共两组值
                    }
                    judgeSelectSubject[i] = new Subject(strings[title], strings[1]);//此处answer的索引为1
                    judgeSelectSubjectList.add(judgeSelectSubject[i]);
                }
            }
            break;
            default:

        }
    }

    private void expandCurrentGroup() {
        if (!testListExpandableListView.isGroupExpanded(position[GROUP_POSITION])) {
            testListExpandableListView.expandGroup(position[GROUP_POSITION]);
        }
    }

    /**
     * 获得所有单元的题目使用
     *
     * @param fileNames    文件名
     * @param problemCount 获得题目的个数
     */
    private ArrayList<String> getProblem(String fileNames, int problemCount, int linesOfProblem) {
        ArrayList<String> saveList = getSaveList(fileNames, praticeTestActivity.getUnitCount());
        int saveProblemCount = saveList.size() / linesOfProblem;//获得文件中总共题目个数
        ArrayList<String> returnList = new ArrayList<String>();//返回的题目集合
        int selectedProblem = 0;
        boolean hasSelected[] = new boolean[saveProblemCount];
        Random random = new Random();
        for (int i = 0; i < problemCount; i++) {
            if (hasSelected.length != 0) {
                do {
                    if (saveProblemCount != 0)
                        selectedProblem = random.nextInt(saveProblemCount);
                } while (hasSelected[selectedProblem]);
                hasSelected[selectedProblem] = true;
                for (int j = 0; j < linesOfProblem; ++j) {//将相应的linesOfProblem行的值添加到returnList
                    returnList.add(saveList.get(selectedProblem * linesOfProblem + j));
                }
            }
        }
        return returnList;
    }

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
        }//为临时正确答案数组赋值为ture,防止提交之前颜色变化

        testListAdapter = new ExamAdapter(getActivity(), angleSelectSubject.length, multipleSelectSubject.length,
                judgeSelectSubject.length, UNREAD_COLOR, READ_COLOR, READING_COLOR, position,
                answerSave, tempAnswerTrue,angleSelectSubjectList,multipleSelectSubjectList,judgeSelectSubjectList);
        testListExpandableListView.setAdapter(testListAdapter);
        testListExpandableListView.expandGroup(0);
    }

    public String getFileDirName() {
        return praticeTestActivity.getFileDirName();
    }

    /**
     * 把存储了错题的list写进文件
     *
     * @param errorList 错误题号的数组
     */
    private void saveErrorPro(ArrayList<String> errorList, String fileName) {
        String path = mContext.getFilesDir() + "/Dxsbang/" + getFileDirName() + "/";
        try {
            File file = new File(path, fileName);
            Log.d("名字", fileName);
            File floder = new File(path);
            if (!floder.exists()) {
                floder.mkdirs();
                Log.d("文件", "创建文件夹");
            }
            FileOutputStream fos = new FileOutputStream(file, true);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(errorList);
//            ArrayList<String> arrayList = new ArrayList<>();
//            for (int i = 0; i < 6; i++) {
//                arrayList.add("测试");
//            }
//            oos.writeObject(arrayList);
//            oos.close();
//            fos.close();
            for (int i = 0; i < errorList.size(); i++) {
                fos.write(errorList.get(i).getBytes());
                fos.write("\n".getBytes());
            }
            fos.close();
        } catch (Exception e) {
            Log.e("存储错误题", e.toString());
        }
    }

    /**
     * 存储所有错题
     */
    private void saveAllErrorPro() {
        ArrayList<String> errorListSingle = new ArrayList<String>();
        ArrayList<String> errorListMultiple = new ArrayList<String>();
        ArrayList<String> errorListJudge = new ArrayList<String>();

        for (int i = 0; i < singleCount; i++) {
            //如果错误存入文件
            if (!isTrue(SINGLE_CHOICE, i)) {
                for (int j = i * 6; j < (i + 1) * 6; j++) {
                    errorListSingle.add(singleProblem.get(j));
                }
            }
        }
        for (int i = 0; i < multipleCount; i++) {
            if (!isTrue(MULTIPLE_CHOICE, i)) {
                for (int j = i * 6; j < (i + 1) * 6; j++) {
                    errorListMultiple.add(multipleProblem.get(j));
                }
            }
        }
        for (int i = 0; i < judgeCount; i++) {
            if (!isTrue(JUDGE_CHOICE, i)) {
                for (int j = i * 2; j < (i + 1) * 2; j++) {
                    errorListJudge.add(judgeProblem.get(j));
                }

            }
        }

        saveErrorPro(errorListSingle, singleFileNames + "error.txt");
        saveErrorPro(errorListMultiple, multipleFileNames + "error.txt");
        saveErrorPro(errorListJudge, judgeFileNames + "error.txt");
    }

    private class testCheckBoxCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        private int selectType;
        private int childPosition;

        public testCheckBoxCheckedChangeListener(int childPosition, int selectType) {
            this.childPosition = childPosition;
            this.selectType = selectType;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            final int A_ANSWER = 1;
            final int B_ANSWER = 2;
            final int C_ANSWER = 3;
            final int D_ANSWER = 4;
            //假如为锁定状态,不触发监听器
            if (!lockState) {
                return;
            }
            //将做题情况设置为未选中,如果有选中选项则变为选中
            answerSave[selectType][childPosition][0] = 0;
            // 更改选项
            switch (selectType) {
                case SINGLE_CHOICE: {
                    switch (buttonView.getId()) {
                        case R.id.test_choice_A_checkbox: {
                            if (isChecked) {
                                testCheckedSingleChioce(R.id.test_choice_A_checkbox);
                            } else
                                answerSave[SINGLE_CHOICE][childPosition][A_ANSWER] = UNCHECKED;
                            break;
                        }
                        case R.id.test_choice_B_checkbox: {
                            if (isChecked)
                                testCheckedSingleChioce(R.id.test_choice_B_checkbox);
                            else
                                answerSave[SINGLE_CHOICE][childPosition][B_ANSWER] = UNCHECKED;
                            break;
                        }
                        case R.id.test_choice_C_checkbox: {
                            if (isChecked)
                                testCheckedSingleChioce(R.id.test_choice_C_checkbox);
                            else
                                answerSave[SINGLE_CHOICE][childPosition][C_ANSWER] = UNCHECKED;
                            break;
                        }
                        case R.id.test_choice_D_checkbox: {
                            if (isChecked)
                                testCheckedSingleChioce(R.id.test_choice_D_checkbox);
                            else
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
                                testSelectCheckBoxB.setChecked(false);
                                answerSave[JUDGE_CHOICE][childPosition][B_ANSWER] = UNCHECKED;
                                answerSave[JUDGE_CHOICE][childPosition][A_ANSWER] = CHECKED;
                            } else
                                answerSave[JUDGE_CHOICE][childPosition][A_ANSWER] = UNCHECKED;
                            break;
                        }
                        case R.id.test_choice_B_checkbox: {
                            if (testSelectCheckBoxB.isChecked()) {
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
//           检测是否已做
            testChangeColor();
            adaptAnswerColor();//调整当前位置颜色,换题调整颜色在列表监听器处
            if (isTrue(selectType, childPosition)) {
                answerTrue[selectType][childPosition] = true;
            }
        }

        /**
         * 1.更改本题存储的答案
         * 2.把未选中选项设置为未选中保证单选
         *
         * @param checkedCheckBoxId 被选中的CheckBox的ID
         */
        private void testCheckedSingleChioce(int checkedCheckBoxId) {
            for (int i = 0; i < 4; i++) {
                CheckBox checkBox = (CheckBox) view.findViewById(testSelectCheckBoxId[i]);
                if (testSelectCheckBoxId[i] == checkedCheckBoxId) {
                    answerSave[SINGLE_CHOICE][childPosition][i + 1] = CHECKED;//A选项值为1,比i值永远大1
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
                for (int i = 1; i < 5; i++) {//索引1234分别代表ABCD 索引0为点击情况 总共五个数据
                    if (answerSave[selectType][childPosition][i] == CHECKED) {//有被选择的选项
                        answerSave[selectType][childPosition][0] = 1;
                        break;
                    }
                }
            } else if (selectType == JUDGE_CHOICE) {
                for (int i = 1; i < 3; i++) {//索引12为正误两选项,索引0位点击情况,共3个数据
                    if (answerSave[selectType][childPosition][i] == CHECKED) {
                        answerSave[selectType][childPosition][0] = 1;
                        break;
                    }
                }
            } else throw new ThreadDeath();//若非这三类题 则抛出错误
            // 监听适配器的改变,改变颜色
            testListAdapter.notifyDataSetChanged();
            nextProblemAuto();
        }

        private void nextProblemAuto() {
            Log.d("测试", "执行方法");
            if (testSkipNextAuto.isChecked() && selectType != MULTIPLE_CHOICE) { //如果被选中,跳转到下一题
                Log.d("测试", "执行方法,被选中并且不是复选");
                if (answerSave[selectType][childPosition][0] == 1) { //已经选中
                    Log.d("测试", "执行方法,asd");
                    nextButton.performClick();
                }
            }
        }
    }

    /**
     * 列表监听器
     * 为四个选项各自设置监听器,在此处设置监听器,是为了随时记录childPosition
     */
    private class TestExpandableChildClickListener implements ExpandableListView.OnChildClickListener {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            position[GROUP_POSITION] = groupPosition;
            position[CHILD_POSITION] = childPosition;
            adaptAnswerColor();//改变题号时改变题颜色
            testListAdapter.notifyDataSetChanged();
            switch (groupPosition) {
                case SINGLE_CHOICE: {
                    initSingleSelection(SINGLE_CHOICE, childPosition);
                    for (int i = 0; i < testSelectCheckBoxId.length; i++) {
                        Log.i("ceshi", "点击123"+testSelectCheckBoxId[i]);
                        CheckBox checkBox = (CheckBox) view.findViewById(testSelectCheckBoxId[i]);
                        checkBox.setOnCheckedChangeListener(new testCheckBoxCheckedChangeListener(childPosition, SINGLE_CHOICE));
                    }
                    break;
                }
                case MULTIPLE_CHOICE: {
                    initMultipleSelection(MULTIPLE_CHOICE, childPosition);
                    for (int i = 0; i < testSelectCheckBoxId.length; i++) {
                        CheckBox checkBox = (CheckBox) view.findViewById(testSelectCheckBoxId[i]);
                        checkBox.setOnCheckedChangeListener(new testCheckBoxCheckedChangeListener(childPosition, MULTIPLE_CHOICE));
                    }
                    break;
                }
                case JUDGE_CHOICE: {
                    initJudgeSelection(JUDGE_CHOICE, childPosition);
                    for (int i = 0; i < 2; i++) {
                        CheckBox checkBox = (CheckBox) view.findViewById(testSelectCheckBoxId[i]);
                        checkBox.setOnCheckedChangeListener(new testCheckBoxCheckedChangeListener(childPosition, JUDGE_CHOICE));
                    }
                    break;
                }
                default:
                    throw new ThreadDeath();
            }
            return true;
        }
    }

    /**
     * 显示答案的点击监听器
     */

    private class showAnswerOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (!showAnswer) {//如果目前不显示答案
                showAnswer = true;
                showAnswerButton.setText("隐藏答案");
                answerTextView.setVisibility(View.VISIBLE);
            } else {
                showAnswer = false;
                showAnswerButton.setText("显示答案");
                answerTextView.setVisibility(View.GONE);
            }
        }
    }
}

