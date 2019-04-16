package hehut.scse.kaoyanbang.TabFragment.Practice.ExamActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;


import java.util.ArrayList;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.TabFragment.Practice.Fragment.PractiseFragment;
import hehut.scse.kaoyanbang.TabFragment.Practice.Fragment.TestFragment;
import hehut.scse.kaoyanbang.TabFragment.Practice.Util.FixWindow;
import hehut.scse.kaoyanbang.TabFragment.Practice.Util.Subject;

class Data_i{
    public static ArrayList<Subject> singleAll, multipleAll, judgeAll;
    public static ArrayList<Subject>[] single, multiple,  judge;
    public static int unitCount;
}

public class ExamDetailsActivity extends FragmentActivity {
    private boolean firstOpenActivity = true;
    private Context mContext;
    //控件变量
    private ImageView backImage;
    private ImageView addImage;
    private Button btnLeft;
    private Button btnright;
    private View currentButton;
    private TestFragment testFragment = new TestFragment();
    private PractiseFragment praticeFragment = new PractiseFragment();
    private ViewGroup fragmentLayout;

    //数据变量
    private String singleFileNames;
    private String multipleFileNames;
    private String judgeFileNames;
    private int currentUnit = 1;//单元
    private int unitCount;
    private String fileDirName;
    //传递的数据变量
    private ArrayList<Subject> singleAll;
    private ArrayList<Subject> multipleAll;
    private ArrayList<Subject> judgeAll;
    private ArrayList<Subject>[] single;
    private ArrayList<Subject>[] multiple;
    private ArrayList<Subject>[] judge;


    /**
     * 启动学习模块
     *
     * @param context
     * @param singleFileNames   单选题文件名
     * @param multipleFileNames 复选题文件名
     * @param judgeFileNames    判断题文件名
     * @param unitCount         单元数
     * @param fileDirName       文件夹名
     */


    static public void actionStart(Context context, String singleFileNames, String multipleFileNames, String judgeFileNames, int unitCount, String fileDirName) {
        Intent intent = new Intent(context, ExamDetailsActivity.class);
        intent.putExtra("singleFileNames", singleFileNames);
        intent.putExtra("multipleFileNames", multipleFileNames);
        intent.putExtra("judgeFileNames", judgeFileNames);
        intent.putExtra("unitCount", unitCount);
        intent.putExtra("fileDirName", fileDirName);


        context.startActivity(intent);
    }

    //以下为新添加内容
    static public void actionStart(Context context, ArrayList<Subject> singleAll, ArrayList<Subject> multipleAll, ArrayList<Subject> judgeAll,
                                   ArrayList<Subject>[] single, ArrayList<Subject>[] multiple, ArrayList<Subject>[] judge, int unitCount) {
//        Intent intent = new Intent(context, ExamDetailsActivity.class);
        Intent intent =new Intent(context,ExamDetailsActivity.class);
        Data_i.singleAll=singleAll;
        Data_i.single=single;
        for (int i=0;i<single.length;i++){
             for (int j=0;j<single[i].size();j++) {

                 Log.i("ceshi", "single   " + single[i].get(j).getTitle());
             }
        }
        Data_i.multipleAll=multipleAll;
        Data_i.multiple=multiple;
        Data_i.judgeAll=judgeAll;
        Data_i.judge=judge;
        Data_i.unitCount=unitCount;

//        intent.putExtra("singleAll", singleAll);
//        intent.putExtra("single", single);
//        intent.putExtra("multipleAll", multipleAll);
//        intent.putExtra("multiple", multiple);
//        intent.putExtra("judgeAll", judgeAll);
//        intent.putExtra("judge", judge);
//        intent.putExtra("unitCount", unitCount);
        Log.i("ceshi", "actionStart: 执行");

        final String shigangDirName = "史纲";
        final String shigangSingleChoice = "historySingleChoice";
        final String shigangMultipleChoice = "historyMultipleChoice";
        final String shigangJudgeChoice = "historyJudgeChoice";

        actionStart(context,shigangSingleChoice, shigangMultipleChoice, shigangJudgeChoice, unitCount, shigangDirName);
//        context.startActivity(intent);




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO �Զ����ɵķ������
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_details);
        Log.i("ceshi", "onCreate: 执行");
        mContext = this;
        new FixWindow(getWindow(), mContext).beauti();
        findView();
        getData();
        init();
        btnLeft.performClick();
    }

    private void findView() {
        btnLeft = (Button) findViewById(R.id.btn_center_left);
        btnright = (Button) findViewById(R.id.btn_center_right);
        backImage = (ImageView) findViewById(R.id.title_back_image);
        addImage = (ImageView) findViewById(R.id.title_add_image);
        fragmentLayout = (ViewGroup) findViewById(R.id.study_content);
    }

    private void getData() {
        //获得intent传递的数据
        Intent intent = getIntent();
        singleFileNames = intent.getStringExtra("singleFileNames");
        multipleFileNames = intent.getStringExtra("multipleFileNames");
        judgeFileNames = intent.getStringExtra("judgeFileNames");
//        Log.i("ceshi", singleFileNames);
//        singleAll = (ArrayList<Subject>) intent.getSerializableExtra("singleAll");
//        multipleAll = (ArrayList<Subject>) intent.getSerializableExtra("multipleAll");
//        judgeAll = (ArrayList<Subject>) intent.getSerializableExtra("judgeAll");
//        single = (ArrayList<Subject>[]) intent.getSerializableExtra("single");
//        multiple = (ArrayList<Subject>[]) intent.getSerializableExtra("multiple");
//        judge = (ArrayList<Subject>[]) intent.getSerializableExtra("judge");

        unitCount = intent.getIntExtra("unitCount", 0);

        singleAll=Data_i.singleAll;
        multipleAll=Data_i.multipleAll;
        judgeAll=Data_i.judgeAll;
        single=Data_i.single;
        multiple=Data_i.multiple;
        judge=Data_i.judge;
        unitCount=Data_i.unitCount;

           fileDirName = intent.getStringExtra("fileDirName");
    }

    private void init() {
        btnLeft.setText("练习");
        btnright.setText("测试");
        btnLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO �Զ����ɵķ������
                android.app.FragmentManager fragmentManager = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.study_content, praticeFragment);
                fragmentTransaction.commit();
                startAnimation(fragmentLayout);
                setButton(v);
            }
        });
        btnright.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO �Զ����ɵķ������
                android.app.FragmentManager fragmentManager = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.study_content, testFragment);
                fragmentTransaction.commit();
                startAnimation(fragmentLayout);
                addImage.setVisibility(View.VISIBLE);
                setButton(v);
            }
        });
        backImage.setImageResource(R.drawable.back);
        backImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO �Զ����ɵķ������
                finish();
            }
        });
        addImage.setImageResource(R.drawable.add);
        addImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO �Զ����ɵķ������
                ErrorExamActivity.activityStart(mContext, singleFileNames, multipleFileNames, judgeFileNames, fileDirName);
            }
        });
    }

    private void setButton(View v) {
        if (currentButton != null && currentButton.getId() != v.getId()) {
            currentButton.setEnabled(true);
        }
        v.setEnabled(false);
        currentButton = v;
    }

    public String getSingleFileNames() {
        return singleFileNames;
    }

    public String getMultipleFileNames() {
        return multipleFileNames;
    }

    public String getJudgeFileNames() {
        return judgeFileNames;
    }

    public int getUnitCount() {
        return unitCount;
    }

    public String getFileDirName() {
        return fileDirName;
    }

    public void startAnimation(ViewGroup viewGroup) {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(200);
        viewGroup.setAnimation(inFromRight);
    }

    public boolean getFirstOpenActivity() {
        return firstOpenActivity;
    }

    public void setFirstOpenActivity(boolean isTrue) {
        firstOpenActivity = isTrue;
    }

    public ArrayList<Subject> getSingleAll() {
        return singleAll;
    }

    public ArrayList<Subject> getMultipleAll() {
        return multipleAll;
    }

    public ArrayList<Subject> getJudgeAll() {
        return judgeAll;
    }

    public ArrayList<Subject>[] getSingle() {
        return single;
    }

    public ArrayList<Subject>[] getMultiple() {
        return multiple;
    }

    public ArrayList<Subject>[] getJudge() {
        return judge;
    }
}