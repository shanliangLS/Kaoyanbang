package hehut.scse.kaoyanbang.TabFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import hehut.scse.kaoyanbang.MainActivity;
import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.TabFragment.Practice.ExamActivity.ExamChooseActivity;
import hehut.scse.kaoyanbang.TabFragment.Practice.ExamActivity.ExamDetailsActivity;
import hehut.scse.kaoyanbang.TabFragment.Practice.Util.Subject;

public class TabFragment3 extends Fragment {

    private final int mayuanUnitCount = 1;
    private final int sixiuUnitCount = 1;
    private final int shigangUnitCount = 1;
    private final int maogaiUnitCount = 1;
    private RelativeLayout sixiuButton;
    private RelativeLayout maogaiButton;
    private RelativeLayout shigangButton;
    private RelativeLayout mayuanButton;
    private int tempUnitCount = 0;
    private String tempFileDirName;
    public  Context mContext;
    private ImageView backImage;
    public  static int flag=0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment3, container, false);


        //        Intent intent = new Intent(getActivity(), ExamChooseActivity.class);
//        startActivity(intent);

        Button button=view.findViewById(R.id.but1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set();
            }
        });

        return view;
    }

    private void set(){
//        if (flag==0){
//            flag=1;
//            return view;
//
//        }


        this.mContext=getContext();


        String firstPath = null;
        try {
            firstPath = mContext.getFilesDir().getCanonicalPath() + "/";///Dxsbang
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("路径", e.toString());
        }

        final String shigangDirName = "史纲";
//                final String shigangDirName = "test";
        final String shigangSingleChoice = "historySingleChoice";
        final String shigangMultipleChoice = "historyMultipleChoice";
        final String shigangJudgeChoice = "historyJudgeChoice";
        final String finalFirstPath2 = firstPath;
        String str=" People hope that they can find ____ soon。\n" +
                "A.good\n" +
                "B.way\n" +
                "C.peace\n" +
                "D.peaceful\n" +
                "C\n" +
                " Well, let’s put our heads together and find _______ to the problem。\n" +
                "A.an answer\n" +
                "B.a way\n" +
                "C.a solution\n" +
                "D.a method\n" +
                "C\n" +
                " On the ____ night, he had promised not to say a word, but the next day, the whole class knew the secret。\n" +
                "A.following\n" +
                "B.previous\n" +
                "C.next\n" +
                "D.other\n" +
                "B\n" +
                " Unfortunately, very few sheep _________ the severe winter last year。\n" +
                "A.survived\n" +
                "B.endured\n" +
                "C.spent\n" +
                "D.remained alive\n" +
                "A\n" +
                " While it may be more convenient to ______ a car, it is also expensive and troublesome at times。\n" +
                "A.owned\n" +
                "B.possess\n" +
                "C.had\n" +
                "D.owning\n" +
                "B\n" +
                " If you go on doing that kind of thing you’ll _______ in prison。\n" +
                "A.come to\n" +
                "B.end up\n" +
                "C.bring back\n" +
                "D.catch hold of\n" +
                "B\n" +
                " He takes pride ____ his daughter, who came first in the race。\n" +
                "A.in\n" +
                "B.for\n" +
                "C.upon\n" +
                "D.to\n" +
                "A\n" +
                " The boy’s bad behavior at school ________ his parents as well as his teachers。\n" +
                "A.sat on\n" +
                "B.handled\n" +
                "C.worry\n" +
                "D.bothered\n" +
                "D\n" +
                " You can pick ____ some useful ideas through talking with your teachers。\n" +
                "A.in\n" +
                "B.for\n" +
                "C.up\n" +
                "D.to\n" +
                "C\n" +
                " Our son is growing ____ and knows how to take care of himself。\n" +
                "A.old\n" +
                "B.out\n" +
                "C.up\n" +
                "D.on\n" +
                "C \n" +
                " The world market is _______ changing. We must anticipate the changes and make timely adjustments。\n" +
                "A.regularly\n" +
                "B.steadily\n" +
                "C.scarcely\n" +
                "D.always\n" +
                "D\n" +
                " A company may _______ its products by mean of newspapers, magazines, television or even skywriting。\n" +
                "A.protest\n" +
                "B.salary\n" +
                "C.advertise\n" +
                "D.incompetent\n" +
                "C\n" +
                " Though the job requires a great deal of effort, the ______ is quite low。\n" +
                "A.protest\n" +
                "B.salary\n" +
                "C.advertise\n" +
                "D.incompetent\n" +
                "B\n" +
                " It was because the applicant was too conceited _________ he failed in the interview。\n" +
                "A.that\n" +
                "B.so that\n" +
                "C.so\n" +
                "D.therefore\n" +
                "A\n" +
                " Consumers should do ________ than simply complain about the poor quality of goods。\n" +
                "A.much less\n" +
                "B.some more\n" +
                "C.far less\n" +
                "D.far more\n" +
                "D\n" +
                " Most westerners coming to China are curious ____ Chinese culture。\n" +
                "A.about\n" +
                "B.of\n" +
                "C.on\n" +
                "D.for\n" +
                "A\n" +
                " It _____ that you’ll be the only girl from our class at the party。\n" +
                "A.seems\n" +
                "B.sees\n" +
                "C.looks\n" +
                "D.appearing\n" +
                "A\n" +
                " If you want him to ____ you the money, you have to prove that you will be able to give it back。\n" +
                "A.give\n" +
                "B.lent\n" +
                "C.lend\n" +
                "D.borrow\n" +
                "A\n" +
                " We often ____ the happy time we spent at your hometown last summer。\n" +
                "A.recall\n" +
                "B.remind\n" +
                "C.present\n" +
                "D.provide\n" +
                "A\n" +
                " The old couple decided to move out of town to a quiet _________ , where they had spent several years immediately after their marriage。\n" +
                "A.space\n" +
                "B.suburb\n" +
                "C.neighborhood\n" +
                "D.area\n" +
                "B";

        FileOutputStream fos = null;
        try {
            FileOutputStream fos1 = mContext.openFileOutput("historySingleChoice0.txt",Context.MODE_PRIVATE);
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(fos1,"GB2312"));
            bufferedWriter.write("英语\n");
            bufferedWriter.write(str);
            bufferedWriter.flush();

            fos1 = mContext.openFileOutput("historyMultipleChoice0.txt",Context.MODE_PRIVATE);
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(fos1,"GB2312"));
            bufferedWriter.write("多选\n");
            bufferedWriter.write(str);
            bufferedWriter.flush();


            str=
                    "近代资本—帝国主义列强对中国的侵略，首先和主要的是进行军事侵略。     \n" +
                            "A\n" +
                            " 在近代历次侵华战争中，外国侵略者屠杀了大批中国人。                 \n" +
                            "A\n" +
                            " 1842年，英国强迫清政府签订《南京条约》，把香港岛割让给英国。           \n" +
                            "A\n" +
                            " 1895年，沙俄强迫清政府签订《马关条约》，割占台湾、澎湖列岛等。       \n" +
                            " B\n" +
                            " 1898年，日本强租中国山东的胶州湾，把山东划为其势力范围。         \n" +
                            "B\n" +
                            " 八国联军侵华时签订的《辛丑条约》，规定中国赔款 5亿两白银。           \n" +
                            "A\n" +
                            " 英国人赫德自1863年任总税务司开始，把持中国海关大权达40余年。     \n" +
                            "A\n" +
                            " 近代帝国主义没有瓜分中国，是因为它们并无瓜分中国的图谋。           \n" +
                            "B\n" +
                            " 经济技术的落后，是近代中国反侵略战争屡遭失败的最重要的原因。         \n" +
                            "B\n" +
                            " 鸦片战争以后，中国人民的民族意识开始普遍觉醒。                   \n" +
                            "B\n" +
                            " 社会制度的腐败，是近代中国反侵略战争屡遭失败的最重要的原因。    \n" +
                            " A\n" +
                            " 八国联军侵华战争期间，欧美报刊纷纷公开讨论如何瓜分中国。         \n" +
                            "A\n" +
                            " 由于一直标榜“保全中国”，美国并未参加1900年的八国联军侵华战争。 \n" +
                            "B\n" +
                            " 近代帝国主义列强之间的矛盾和妥协，是瓜分中国图谋破产的根本原因。  \n" +
                            "B\n" +
                            " 由于害怕人民群众，清政府常常压制和破坏人民群众的反侵略斗争。         \n" +
                            "A\n" +
                            " 经济技术的落后，意味着中国在反侵略战争中一定会打败仗。            \n" +
                            "B\n" +
                            " 近代列强发动的侵华战争以及中国反侵略战争的失败，从反面教育了中国人民，极大地促进了中国人的思考、探索和奋起。\n" +
                            "A\n" +
                            " 近代资本—帝国主义列强同中国发展经济关系是为了推动中国经济的发展。\n" +
                            "B\n" +
                            " 近代中国海关的高级职员，全部由外国人充任。                       \n" +
                            "A\n";
            fos1 = mContext.openFileOutput("historyJudgeChoice0.txt",Context.MODE_PRIVATE);
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(fos1,"GB2312"));
            bufferedWriter.write("判断\n");
            bufferedWriter.write(str);
            bufferedWriter.flush();

//
//
//                    FILE_NAME="historyJudgeChoice0.txt";
//                    fos = openFileOutput(FILE_NAME,Context.MODE_PRIVATE);
//                    fos.write("判断\n".getBytes());

//                    fos.write(str.getBytes());
//                    fos.flush();
//                    fos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//                String path1 = mContext.getFilesDir()  + "/";//+ "/Dxsbang/" + shigangDirName
//                File targetFile = new File(path1, shigangSingleChoice + 0 + ".txt");
//                Log.i("shi", ""+targetFile.canWrite());


//                        new DownTask(mContext, shigangUnitCount, shigangSingleChoice, shigangMultipleChoice, shigangJudgeChoice, shigangDirName, new DownFishListener() {
//                            @Override
//                            public void onFinish() {


        if (new File(finalFirstPath2  + "" + shigangSingleChoice + "0.txt").exists()) {//+ shigangDirName

//                                    String path1 = mContext.getFilesDir() + "/Dxsbang/" + shigangDirName + "/";

//                                     path1 = mContext.getFilesDir()  + "/";//+ "/Dxsbang/" + shigangDirName
//
//
//
//                                    File targetFile1 = new File("/storage/");
//
//
//
//                                     targetFile = new File(path1, shigangSingleChoice + 0 + ".txt");


            try {
//                                        File targetFile1 = new File(path1);
//                                        if (!targetFile1.exists()){
//                                            targetFile1.mkdir();
//                                            new FileWriter(path1+"qqq.txt");
//                                        }


                FileInputStream fileInputStream = null;
                fileInputStream = mContext.openFileInput("historySingleChoice0.txt");
//                                        FileInputStream fileInputStream = new FileInputStream(fis);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, "GB2312"));
                Log.i("shi", reader.readLine()+"石凯峰");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
//
//                                    setData();
            Log.i("SKF", "ceshi");
            //存储所有单选,多选,判断题的数组
            ArrayList<Subject> singleHistoryAllList = new ArrayList<Subject>();
            ArrayList<Subject> multipleHistoryAllList = new ArrayList<Subject>();
            ArrayList<Subject> judgeHistoryAllList = new ArrayList<Subject>();
            //分单元存储的数组
            ArrayList<Subject>[] singleHistoryList;
            ArrayList<Subject>[] multipleHistoryList;
            ArrayList<Subject>[] judgeHistoryList;
            singleHistoryList = new ArrayList[shigangUnitCount];
            multipleHistoryList = new ArrayList[shigangUnitCount];
            judgeHistoryList = new ArrayList[shigangUnitCount];
            for (int i = 0; i < shigangUnitCount; i++) {//读取文件内容到list里
                singleHistoryList[i] = getSaveList(shigangDirName, shigangSingleChoice + i + ".txt", false);
                multipleHistoryList[i] = getSaveList(shigangDirName, shigangMultipleChoice + i + ".txt", false);
                judgeHistoryList[i] = getSaveList(shigangDirName , shigangJudgeChoice+ i + ".txt", true);
                singleHistoryAllList.addAll(singleHistoryList[i]);
                multipleHistoryAllList.addAll(multipleHistoryList[i]);
                judgeHistoryAllList.addAll(judgeHistoryList[i]);
            }
            Log.i("ceshi", "史纲: 执行"+singleHistoryAllList.size());
            for (int i=0;i<singleHistoryAllList.size();i++){
                Log.i("shi", singleHistoryAllList.get(i).getTitle());
            }

            ExamDetailsActivity.actionStart(mContext, singleHistoryAllList, multipleHistoryAllList, judgeHistoryAllList, singleHistoryList, multipleHistoryList, judgeHistoryList,
                    shigangUnitCount);


//
//            ExamDetailsActivity.actionStart(mContext, shigangSingleChoice, shigangMultipleChoice, shigangJudgeChoice, shigangUnitCount, shigangDirName);
        } else {
            Log.i("ceshi", finalFirstPath2  + "" + shigangSingleChoice + "0.txt");
            Toast.makeText(mContext, "该题库尚未更新，请查看其他题库", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 获取一个文件中的题库
     * 注意:!!!!!!
     * 此处的文件名为文件全名,加上后缀和标号     *
     *
     * @param fileName
     * @return
     */
    private ArrayList<Subject> getSaveList(String dirName, String fileName, boolean isJudgeProblem) {
        ArrayList<Subject> unitProblemList = new ArrayList<>();
        try {
            String path = mContext.getFilesDir()  + "/";//+ "/Dxsbang/" + dirName
            File targetFile = new File(path, fileName);
            FileInputStream fileInputStream = new FileInputStream(targetFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, "GB2312"));
            boolean firstRead = true;
            String line = "";
            int cirCount = 0;
            String title = new String("空的题目");
            String a = new String("空的选项");
            String b = new String("空的选项");
            String c = new String("空的选项");
            String d = new String("空的选项");
            String answer = new String("暂无答案");
            while ((line = reader.readLine()) != null) {
                if (!"".equals(line.trim())) {
                    if (firstRead) {
                        firstRead = false;
                        continue;
                    }
                    if (isJudgeProblem) {//如果是判断
                        switch (cirCount) {
                            case 0:
                                title = line;
                                break;
                            case 1:
                                answer = line;
                                unitProblemList.add(new Subject(title, answer));
                                break;
                            default:
                                throw new Exception("循环读取数据错误");
                        }
                        cirCount = (cirCount + 1) % 2;
                    } else {//单选或者复选题读取数据
                        switch (cirCount) {
                            case 0:
                                title = line;
                                break;
                            case 1:
                                a = line;
                                break;
                            case 2:
                                b = line;
                                break;
                            case 3:
                                c = line;
                                break;
                            case 4:
                                d = line;
                                break;
                            case 5:
                                answer = line;
                                unitProblemList.add(new Subject(a, b, c, d, title, answer));
                                Log.d("测试", "添加成功了数据");
//                                Log.i("ceshi", unitProblemList.get(i).getTitle());
                                break;
                            default:
                                Log.d("测试", "循环读取数据错误");
                                throw new Exception("循环读取数据错误");
                        }
                        cirCount = (cirCount + 1) % 6;
                    }
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();

//            Log.i("ceshi", e.toString());
        } catch (Exception e) {
//            e.toString();

            e.printStackTrace();
//            Log.i("ceshi", e.toString());
        }

        return unitProblemList;
/*
        ArrayList<String> list = new ArrayList<String>();
        try {
            String path = mContext.getFilesDir() + "/Dxsbang/" + dirName + "/";
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
        */
    }
}
