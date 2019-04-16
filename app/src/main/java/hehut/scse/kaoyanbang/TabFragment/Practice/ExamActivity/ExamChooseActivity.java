package hehut.scse.kaoyanbang.TabFragment.Practice.ExamActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
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

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.TabFragment.Practice.MyInterface.DownFishListener;
import hehut.scse.kaoyanbang.TabFragment.Practice.Util.DownLoadFile;
import hehut.scse.kaoyanbang.TabFragment.Practice.Util.FixWindow;
import hehut.scse.kaoyanbang.TabFragment.Practice.Util.Subject;


public class ExamChooseActivity extends Activity implements View.OnClickListener {
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
    private Context mContext;
    private ImageView backImage;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };


    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

public void setData(){
    final String shigangDirName = "史纲";
//                final String shigangDirName = "test";
    final String shigangSingleChoice = "historySingleChoice";
    final String shigangMultipleChoice = "historyMultipleChoice";
    final String shigangJudgeChoice = "historyJudgeChoice";

    String path1 = mContext.getFilesDir() + "/Dxsbang/" + shigangDirName + "/";









    File targetFile = new File(path1, shigangSingleChoice + 0 + ".txt");
    FileOutputStream fileOutputStream = null;
    try {
        fileOutputStream = new FileOutputStream(targetFile);
        BufferedWriter bufferedWriter = null;

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "GB2312"));
        bufferedWriter.write("英语\n");

//                bufferedWriter.write(" People hope that they can find ____ soon\n");
//                bufferedWriter.write("A.good\n");
//                bufferedWriter.write("B.way\n");
//                bufferedWriter.write("C.peace\n");
//                bufferedWriter.write("D.peaceful\n");
//                bufferedWriter.write("C\n");
        String str=" People hope that they can find ____ soon\n" +
                "A.good\n" +
                "B.way\n" +
                "C.peace\n" +
                "D.peaceful\n" +
                "C\n" +
                " Well, lets put our heads together and find _______ to the problem.\n" +
                "A.an answer\n" +
                "B.a way\n" +
                "C.a solution\n" +
                "D.a method\n" +
                "C\n" +
                " On the ____ night, he had promised not to say a word, but the next day, the whole class knew the secret.\n" +
                "A.following\n" +
                "B.previous\n" +
                "C.next\n" +
                "D.other\n" +
                "B\n" +
                " Unfortunately, very few sheep _________ the severe winter last year.\n" +
                "A.survived\n" +
                "B.endured\n" +
                "C.spent\n" +
                "D.remained alive\n" +
                "A\n" +
                " While it may be more convenient to ______ a car, it is also expensive and troublesome at times.\n" +
                "A.owned\n" +
                "B.possess\n" +
                "C.had\n" +
                "D.owning\n" +
                "B\n" +
                " If you go on doing that kind of thing you’ll _______ in prison.\n" +
                "A.come to\n" +
                "B.end up\n" +
                "C.bring back\n" +
                "D.catch hold of\n" +
                "B\n" +
                " He takes pride ____ his daughter, who came first in the race.\n" +
                "A.in\n" +
                "B.for\n" +
                "C.upon\n" +
                "D.to\n" +
                "A\n" +
                " The boy’s bad behavior at school ________ his parents as well as his teachers.\n" +
                "A.sat on\n" +
                "B.handled\n" +
                "C.worry\n" +
                "D.bothered\n" +
                "D\n" +
                " You can pick ____ some useful ideas through talking with your teachers\n" +
                "A.in\n" +
                "B.for\n" +
                "C.up\n" +
                "D.to\n" +
                "C\n" +
                " Our son is growing ____ and knows how to take care of himself.\n" +
                "A.old\n" +
                "B.out\n" +
                "C.up\n" +
                "D.on\n" +
                "C \n" +
                " The world market is _______ changing. We must anticipate the changes and make timely adjustments\n" +
                "A.regularly\n" +
                "B.steadily\n" +
                "C.scarcely\n" +
                "D.always\n" +
                "D\n" +
                " A company may _______ its products by mean of newspapers, magazines, television or even skywriting.\n" +
                "A.protest\n" +
                "B.salary\n" +
                "C.advertise\n" +
                "D.incompetent\n" +
                "C\n" +
                " Though the job requires a great deal of effort, the ______ is quite low\n" +
                "A.protest\n" +
                "B.salary\n" +
                "C.advertise\n" +
                "D.incompetent\n" +
                "B\n" +
                " It was because the applicant was too conceited _________ he failed in the interview. \n" +
                "A.that\n" +
                "B.so that\n" +
                "C.so\n" +
                "D.therefore\n" +
                "A\n" +
                " Consumers should do ________ than simply complain about the poor quality of goods.\n" +
                "A.much less\n" +
                "B.some more\n" +
                "C.far less\n" +
                "D.far more\n" +
                "D\n" +
                " Most westerners coming to China are curious ____ Chinese culture.\n" +
                "A.about\n" +
                "B.of\n" +
                "C.on\n" +
                "D.for\n" +
                "A\n" +
                " It _____ that you’ll be the only girl from our class at the party.\n" +
                "A.seems\n" +
                "B.sees\n" +
                "C.looks\n" +
                "D.appearing\n" +
                "A\n" +
                " If you want him to ____ you the money, you have to prove that you will be able to give it back\n" +
                "A.give\n" +
                "B.lent\n" +
                "C.lend\n" +
                "D.borrow\n" +
                "A\n" +
                " We often ____ the happy time we spent at your hometown last summer.\n" +
                "A.recall\n" +
                "B.remind\n" +
                "C.present\n" +
                "D.provide\n" +
                "A\n" +
                " The old couple decided to move out of town to a quiet _________ , where they had spent several years immediately after their marriage.\n" +
                "A.space\n" +
                "B.suburb\n" +
                "C.neighborhood\n" +
                "D.area\n" +
                "B";
        Log.i("ceshi1", str);
            bufferedWriter.write(str);
        bufferedWriter.flush();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }catch (IOException e) {
        e.printStackTrace();
    }


}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_choose);
        mContext = this;
        verifyStoragePermissions(ExamChooseActivity.this);
        new FixWindow(getWindow(), mContext).beauti();
        findView();
        setListener();

    }


    private void findView() {
        backImage = (ImageView) findViewById(R.id.title_left_image);
        backImage.setImageResource(R.drawable.back);
        sixiuButton = (RelativeLayout) findViewById(R.id.sixiu);
        maogaiButton = (RelativeLayout) findViewById(R.id.maogai);
        shigangButton = (RelativeLayout) findViewById(R.id.shigang);
        mayuanButton = (RelativeLayout) findViewById(R.id.mayuan);




////                            }
    }

    private void setListener() {
        sixiuButton.setOnClickListener(this);
        maogaiButton.setOnClickListener(this);
        shigangButton.setOnClickListener(this);
        mayuanButton.setOnClickListener(this);
        backImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String firstPath = null;
        try {
            firstPath = getFilesDir().getCanonicalPath() + "/";///Dxsbang
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("路径", e.toString());
        }
        switch (v.getId()) {
            case R.id.title_left_image:
                finish();
                break;
            case R.id.sixiu: {
                final String sixiuFileDirName = "思修";
                final String sixiuSingleChoice = "mindSingleChoice";
                final String sixiuMultipleChoice = "mindMultipleChoice";
                final String sixiuJudgeChoice = "mindJudgeChoice";
                final String finalFirstPath = firstPath;
                new DownTask(mContext, sixiuUnitCount, sixiuSingleChoice, sixiuMultipleChoice, sixiuJudgeChoice, sixiuFileDirName, new DownFishListener() {
                    @Override
                    public void onFinish() {

                        if (new File(finalFirstPath + sixiuFileDirName + "/" + sixiuSingleChoice + "0.txt").exists()) {
                            ExamDetailsActivity.actionStart(mContext, sixiuSingleChoice, sixiuMultipleChoice, sixiuJudgeChoice, sixiuUnitCount, sixiuFileDirName);
                        } else {
                            Toast.makeText(mContext, "该题库尚未更新，请查看其他题库", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).execute();
            }
            break;


            case R.id.maogai: {
                final String maogaiFileDirName = "毛概";
                final String maogaiSingleChoice = "maoSingleChoice";
                final String maogaiMultipleChoice = "maoMultipleChoice";
                final String maogaiJudgeChoice = "maoJudgeChoice";
                final String finalFirstPath1 = firstPath;
                new DownTask(mContext, maogaiUnitCount, maogaiSingleChoice, maogaiMultipleChoice, maogaiJudgeChoice, maogaiFileDirName, new DownFishListener() {
                    @Override
                    public void onFinish() {
                        if (new File(finalFirstPath1 + maogaiFileDirName + "/" + maogaiSingleChoice + "0.txt").exists()) {
                            ExamDetailsActivity.actionStart(mContext, maogaiSingleChoice, maogaiMultipleChoice, maogaiJudgeChoice, maogaiUnitCount, maogaiFileDirName);
                        } else {
                            Toast.makeText(mContext, "该题库尚未更新，请查看其他题库 ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).execute();
            }
            break;
            case R.id.shigang: {

////                        }).execute();



            }
            break;
            case R.id.mayuan: {
                tempUnitCount = mayuanUnitCount;
                final String mayuanFileDirName = "马原";
                final String mayuanSingleChoice = "maSingleChoice";
                final String mayuanMultipleChoice = "maMultipleChoice";
                final String mayuanJudgeChoice = "maJudgeChoice";
                final String finalFirstPath3 = firstPath;
                new DownTask(mContext, mayuanUnitCount, mayuanSingleChoice, mayuanMultipleChoice, mayuanJudgeChoice, mayuanFileDirName, new DownFishListener() {
                    @Override
                    public void onFinish() {
                        if (new File(finalFirstPath3 + mayuanFileDirName + "/" + mayuanSingleChoice + "0.txt").exists()) {
                            ExamDetailsActivity.actionStart(mContext, mayuanSingleChoice, mayuanMultipleChoice, mayuanJudgeChoice, mayuanUnitCount, mayuanFileDirName);
                        } else {
                            Toast.makeText(mContext, "该题库尚未更新，请查看其他题库", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).execute();

            }
            default:
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

    class DownTask extends AsyncTask {
        DownFishListener listener;
        Context mContext;
        int unitCount;
        String singleNames;
        String multipleNames;
        String judgeNames;
        String dirName;
        ProgressDialog progressDialog;

        public DownTask(Context mContext, int unitCount, String singleNames, String multipleName, String judgeName, String dirName, DownFishListener listener) {
            this.mContext = mContext;
            this.unitCount = unitCount;
            this.listener = listener;
            this.singleNames = singleNames;
            this.multipleNames = multipleName;
            this.judgeNames = judgeName;
            this.dirName = dirName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setTitle("正在加载题库");
            progressDialog.setMessage("题库加载中,请耐心等待");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                String firstUrl = "http://localhost:8088/practise/";
                String path = getFilesDir().getCanonicalPath() + "/";//'/Dxsbang
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                    Log.d("下载", file.getCanonicalPath());
                }
String dirName="";
                for (int i = 0; i < unitCount; i++) {
                    String temp;
                    temp = DownLoadFile.getFile(firstUrl + singleNames + i + ".txt", path + dirName + "/");
                    Log.i("下载", "doInBackground: "+singleNames+","+dirName);
                    DownLoadFile.getFile(firstUrl + multipleNames + i + ".txt", path + dirName + "/");
                    DownLoadFile.getFile(firstUrl + judgeNames + i + ".txt", path + dirName + "/");
                    Log.d("下载", temp);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("下载", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            progressDialog.dismiss();
            listener.onFinish();
            super.onPostExecute(o);
        }
    }
}

