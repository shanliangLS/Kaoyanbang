package hehut.scse.kaoyanbang.TabFragment.Practice.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.TabFragment.Practice.ExamActivity.ExamDetailsActivity;
import hehut.scse.kaoyanbang.TabFragment.Practice.MyAdapter.ExamAdapter;
import hehut.scse.kaoyanbang.TabFragment.Practice.Util.LogUtil;
import hehut.scse.kaoyanbang.TabFragment.Practice.Util.Subject;

public class PractiseFragment extends Fragment {

    private View view;
    private Context mContext;
    private ExamDetailsActivity examActivity;
    //布局控件
    private ExpandableListView problemElv;
    private Button nextBt;
    private TextView titleTv;
    private TextView answerTv;
    private Button preBt;
    private TextView showAnswerBt;
    private CheckBox skipAutoCb;
    private CheckBox selectACb;
    private CheckBox selectBCb;
    private CheckBox selectCCb;
    private CheckBox selectDCb;
    //数据变量
    private ArrayList<Subject> singleAll;
    private ArrayList<Subject> multipleAll;
    private ArrayList<Subject> judgeAll;
    private ArrayList<Subject>[] single;
    private ArrayList<Subject>[] multiple;
    private ArrayList<Subject>[] judge;
    //记录数据
    private int unitCurrent = 0;
    private int unitCount = 0;
    private int singleCount = 0;
    private int multipleCount = 0;
    private int judgeCount = 0;

    private int groupPosition=0, childPosition=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO �Զ����ɵķ������
        if (view == null) {
            view = inflater.inflate(R.layout.exam_practise, null);
            mContext = getActivity();
            getData();
            findView();
            initData();
            setButtonListener();
            initStatus();
            setClick();
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

    /**
     * 存储做题位置
     */
    @Override
    public void onStop() {//存储上一次位置操作
        super.onStop();
    }

    private void findView() {
        problemElv = (ExpandableListView) view.findViewById(R.id.test_expandableListView);//列表
        titleTv = (TextView) view.findViewById(R.id.test_problem_textview);//题目
        selectACb = (CheckBox) view.findViewById(R.id.test_choice_A_checkbox);
        selectBCb = (CheckBox) view.findViewById(R.id.test_choice_B_checkbox);
        selectCCb = (CheckBox) view.findViewById(R.id.test_choice_C_checkbox);
        selectDCb = (CheckBox) view.findViewById(R.id.test_choice_D_checkbox);
        nextBt = (Button) view.findViewById(R.id.bt_next_title);
        preBt = (Button) view.findViewById(R.id.bt_pre_title);
        showAnswerBt = (TextView) view.findViewById(R.id.tv_show_answer);
        answerTv = (TextView) view.findViewById(R.id.tv_answer);
        skipAutoCb = (CheckBox) view.findViewById(R.id.skip_next_auto);
    }

    private void getData() {
        unitCurrent = 0;
        examActivity = (ExamDetailsActivity) getActivity();
        unitCount = examActivity.getUnitCount();
        single = examActivity.getSingle();
        multiple = examActivity.getMultiple();
        judge = examActivity.getJudge();
        singleCount = single[unitCurrent].size();
        multipleCount = multiple[unitCurrent].size();
        judgeCount = judge[unitCurrent].size();
    }

    private void initData() {
        problemElv.setAdapter(new ExamAdapter(mContext, single[0], multiple[0], judge[0], Color.WHITE, Color.BLUE, Color.GREEN));
        problemElv.setGroupIndicator(null);//消除箭头
    }

    private void setButtonListener() {//设置监听器
        problemElv.setOnChildClickListener(new ProblemItemClickListener());//列表的点击事件


    }

    /**
     * 初始化首次打开后的执行状态
     */
    private void initStatus() {
//进入默认选中某道题
        //  new ProblemItemClickListener().onChildClick(problemElv, null, Subject.positionX, Subject.positionY, 0);
        problemElv.expandGroup(0);


        initProblemAll((TextView) problemElv.getExpandableListAdapter().getChildView(0, 0, false, null, null), 0, 0);

    }

    /**
     *
     */
private void setClick(){
    TextView tv_show_answer = (TextView) view.findViewById(R.id.tv_show_answer);
    tv_show_answer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            answerTv.setVisibility(View.VISIBLE);

        }
    });
    nextBt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            TextView itemTv = (TextView) getViewByPosition(Subject.positionX, Subject.positionY+1);
//            adjustCurrentColor((TextView) problemElv.getExpandableListAdapter().getChildView(0, 0, false, null, null),groupPosition,childPosition);
            Log.i("ceshi", "positionX"+Subject.positionX+",positionY"+Subject.positionY+",");
            TextView temp=(TextView) getViewByPosition(Subject.positionX,(Subject.positionY));
            TextView temp1=(TextView) getViewByPosition(Subject.positionX,(++Subject.positionY));
            if (temp1==null){
                Toast.makeText(mContext, "已经是最后一个了",Toast.LENGTH_SHORT).show();
                Subject.positionY--;
                return;
            }else{

                temp1.setTextColor(Color.GREEN);
            }
            temp.setTextColor(Color.RED);
//            adjustCurrentColor();
//            Log.i("ceshi", "positionX"+Subject.positionX+",positionY"+Subject.positionY+",1");
            initProblemText(Subject.positionX, Subject.positionY);//初始化题目的文字信息

        }
    });
    preBt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView temp=(TextView) getViewByPosition(Subject.positionX,(Subject.positionY));
            if(--Subject.positionY<0){
                Toast.makeText(mContext, "已经是第一个了",Toast.LENGTH_SHORT).show();
                Subject.positionY++;
                return;
            }
            TextView temp1=(TextView) getViewByPosition(Subject.positionX,(Subject.positionY));
            if (temp1==null){
                Toast.makeText(mContext, "已经是第一个了",Toast.LENGTH_SHORT).show();
                Subject.positionY++;
                return;
            }else{

                temp1.setTextColor(Color.GREEN);
            }
            temp.setTextColor(Color.RED);
//            adjustCurrentColor();
//            Log.i("ceshi", "positionX"+Subject.positionX+",positionY"+Subject.positionY+",1");
            initProblemText(Subject.positionX, Subject.positionY);//初始化题目的文字信息
        }
    });
}

    /**
     * 打开对应分组并关闭其他分组
     */
    private void expandGroup(int groupPosition) {
        problemElv.expandGroup(groupPosition);
        for (int i = 0; i < problemElv.getExpandableListAdapter().getGroupCount(); i++) {
            if (i==groupPosition){

            }
        }

    }


    /**
     * 获得对应位置的view,若未获得相应view,则返回null
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    private View getViewByPosition(int groupPosition, int childPosition) {
        int positionXY = 0;
        switch (Subject.positionX) {
            case 0:
                positionXY = Subject.positionY + 1;//组控件也算一个position
                break;
            case 1:
                positionXY = Subject.positionY + 2;
                if (problemElv.isGroupExpanded(0)) {
                    positionXY += singleCount;
                }
                break;
            case 2:
                positionXY = Subject.positionY + 3;
                if (problemElv.isGroupExpanded(0)) {//单选题框打开
                    positionXY += singleCount;
                }
                if (problemElv.isGroupExpanded(1)) {
                    positionXY += multipleCount;
                }
                break;
            default:
                positionXY = 0;
        }
        //获得上一个当前位置的控件
        View view = problemElv.getChildAt(positionXY - problemElv.getFirstVisiblePosition());
        return view;
    }


    /**
     * 调整当前位置的颜色和上次位置的颜色
     *
     * @param v
     * @param groupPosition
     * @param childPosition
     */
    private void adjustCurrentColor(TextView v, int groupPosition, int childPosition) {
        //计算上一个当前位置
//        boolean isLastPosition = false;
//        if (Subject.positionX == 0 && Subject.positionY == singleCount - 1) {
//            isLastPosition = true;
//        } else if (Subject.positionX == 1 && Subject.positionY == multipleCount - 1) {
//            isLastPosition = true;
//        } else if (Subject.positionX == 2 && Subject.positionY == judgeCount - 1) {
//            isLastPosition = true;
//        }
//        TextView itemTv = (TextView) problemElv.getExpandableListAdapter().getChildView(Subject.positionX, Subject.positionY, isLastPosition, null, null);
        TextView itemTv = (TextView) getViewByPosition(Subject.positionX, Subject.positionY);
        if (itemTv != null) {
            itemTv.setTextColor(Color.RED);
        } else {
            LogUtil.d("测试", "null");
        }
        //设置当前颜色为绿色
        v.setTextColor(Color.GREEN);
        //调整新的当前位置
        Subject.positionX = groupPosition;
        Subject.positionY = childPosition;
    }

    /**
     * 初始化全部题目信息
     *
     * @param v
     * @param groupPosition
     * @param childPosition
     */
    private void initProblemAll(TextView v, int groupPosition, int childPosition) {
        adjustCurrentColor(v, groupPosition, childPosition);//调整颜色,并且更改当前位置
        initProblemText(groupPosition, childPosition);//初始化题目的文字信息

    }


    /**
     * 初始化调整显示的题目信息,选项个数等
     *
     * @param groupPosition
     * @param childPosition
     */
    void initProblemText(int groupPosition, int childPosition) {
        ExamAdapter examAdapter = (ExamAdapter) problemElv.getExpandableListAdapter();
        Subject tempSub = (Subject) examAdapter.getChild(groupPosition, childPosition);//获得当前点击处的题目信息
        answerTv.setVisibility(View.GONE);
        if (groupPosition == 2) {//如果是判断题
            selectCCb.setVisibility(View.GONE);
            selectDCb.setVisibility(View.GONE);
            titleTv.setText(tempSub.getTitle());
            selectACb.setText("正确");
            selectBCb.setText("错误");
            String ans="";
            if (tempSub.getAnswer().equals("A")){
                ans="正确";
            }else{
                ans="错误";
            }
            answerTv.setText(ans);
        } else {
            selectCCb.setVisibility(View.VISIBLE);
            selectDCb.setVisibility(View.VISIBLE);
            titleTv.setText(tempSub.getTitle());
            selectACb.setText(tempSub.getA());
            selectBCb.setText(tempSub.getB());
            selectCCb.setText(tempSub.getC());
            selectDCb.setText(tempSub.getD());
            answerTv.setText(tempSub.getAnswer());//

        }
    }

    /**
     * 列表的点击响应,监听器
     */
    class ProblemItemClickListener implements ExpandableListView.OnChildClickListener {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            initProblemAll((TextView) v, groupPosition, childPosition);
            return true;
        }
    }
}

