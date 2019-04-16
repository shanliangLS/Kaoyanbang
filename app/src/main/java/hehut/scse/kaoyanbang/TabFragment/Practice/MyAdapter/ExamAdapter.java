package hehut.scse.kaoyanbang.TabFragment.Practice.MyAdapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import hehut.scse.kaoyanbang.TabFragment.Practice.Util.Subject;


public class ExamAdapter extends BaseExpandableListAdapter {
    private final int GROUP_POSITION = 0;
    private final int CHILD_POSITION = 1;
    private final String[] groupName = {"单选题", "多选题", "判断题"};
    //新加变量
    ArrayList<Subject> singleList;
    ArrayList<Subject> multipleList;
    ArrayList<Subject> judgeList;
    private Context context;
    private int singleCount;
    private int mulitipleCount;
    private int judgeCount;
    private int unreadColor;
    private int readColor;
    private int readingColor;
    private int[][][] answerSave;
    private boolean[][] answerTrue;
    private int[] position;


    public ExamAdapter(Context context, int singleSelectChoiceCount, int multipleSelectChoiceCount, int judgeChoiceCount, int unreadColor, int readColor, int readingColor, int[] position, int[][][] answerSave) {
        this.context = context;
        this.singleCount = singleSelectChoiceCount;
        this.mulitipleCount = multipleSelectChoiceCount;
        this.judgeCount = judgeChoiceCount;
        this.readColor = readColor;
        this.unreadColor = unreadColor;
        this.readingColor = readingColor;
        this.answerSave = answerSave;
        this.position = position;
    }
    public  ExamAdapter(Context context, int singleSelectChoiceCount, int multipleSelectChoiceCount, int judgeChoiceCount, int unreadColor, int readColor, int readingColor, int[] position, int[][][] answerSave, boolean[][] answerTrue) {
        this.context = context;
        this.singleCount = singleSelectChoiceCount;
        this.mulitipleCount = multipleSelectChoiceCount;
        this.judgeCount = judgeChoiceCount;
        this.readColor = readColor;
        this.unreadColor = unreadColor;
        this.readingColor = readingColor;
        this.answerSave = answerSave;
        this.position = position;
        this.answerTrue = answerTrue;
    }
    public ExamAdapter(Context context, int singleSelectChoiceCount, int multipleSelectChoiceCount, int judgeChoiceCount, int unreadColor, int readColor, int readingColor, int[] position, int[][][] answerSave, boolean[][] answerTrue,ArrayList<Subject> singleList, ArrayList<Subject> multipleList, ArrayList<Subject> judgeList) {
        this.context = context;
        this.singleCount = singleSelectChoiceCount;
        this.mulitipleCount = multipleSelectChoiceCount;
        this.judgeCount = judgeChoiceCount;
        this.readColor = readColor;
        this.unreadColor = unreadColor;
        this.readingColor = readingColor;
        this.answerSave = answerSave;
        this.position = position;
        this.answerTrue = answerTrue;

        Log.i("ceshi", "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊: ");
        this.singleList = singleList;
        this.multipleList = multipleList;
        this.judgeList = judgeList;
//        singleCount = singleList.size();
//        mulitipleCount = multipleList.size();
//        judgeCount = judgeList.size();
    }

    //构造函数
    public ExamAdapter(Context context, ArrayList<Subject> singleList, ArrayList<Subject> multipleList, ArrayList<Subject> judgeList, int unreadColor, int readColor, int readingColor) {
        Log.i("ceshi", "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊: "+singleList.size());
        //变量赋值
        this.context = context;
        this.singleList = singleList;
        this.multipleList = multipleList;
        this.judgeList = judgeList;
        this.unreadColor = unreadColor;
        this.readColor = readColor;
        this.readingColor = readingColor;
        //随动变量
        singleCount = singleList.size();
        mulitipleCount = multipleList.size();
        judgeCount = judgeList.size();
    }

    public ExamAdapter(Context context, ArrayList<Subject> singleList, ArrayList<Subject> multipleList, ArrayList<Subject> judgeList) {
        this(context, singleList, multipleList, judgeList, Color.BLACK, Color.BLUE, Color.GREEN);
    }

    /**
     * 自定义函数,用以返回自定义格式的TextView
     *
     * @return TextView
     */
    private View getView() {//返回一个TextView
        TextView textView = new TextView(context);
        textView.setPadding(8, 4, 8, 4);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(12);
        return textView;
    }

    @Override
    public int getGroupCount() {

        if (judgeList==null){
            return 0;
        }
        Log.i("ceshi", singleList.size()+"nulllllll  ");
        if (judgeList.size() != 0) {//如果判断题个数不是0
            return 3;
        } else if (multipleList.size() != 0) {
            return 2;
        } else if (judgeList.size() != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /*
        @Override
        public int getGroupCount() {
            if (judgeCount != 0) {
                return 3;
            } else if (mulitipleCount != 0) {
                return 2;
            } else {
                return 1;
            }
        }
    */
    @Override
    public int getChildrenCount(int groupPosition) {//返回每组item个数
        switch (groupPosition) {
            case 0:
                return singleCount;
            case 1:
                return mulitipleCount;
            case 2:
                return judgeCount;
            default:
                return 0;
        }
    }

    /**
     * 返回存储选项内容的数组
     *
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        switch (groupPosition) {
            case 0:
                return singleList;
            case 1:
                return multipleList;
            case 2:
                return judgeList;
            default:
                return null;
        }
    }

    /**
     * 获得对应位置的Subject实例
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        switch (groupPosition) {
            case 0:
                return singleList.get(childPosition);
            case 1:
                return multipleList.get(childPosition);
            case 2:
                return judgeList.get(childPosition);
            default:
                return null;
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {//如果尚未加载TextView,创建一个TextView,并设置属性
            textView = (TextView) getView();
            textView.setHeight(100);
        } else {
            textView = (TextView) convertView;
        }
        switch (groupPosition) {
            case 0:
                textView.setText("单选题");
                break;
            case 1:
                textView.setText("多选题");
                break;
            case 2:
                textView.setText("判断题");
                break;
            default:
                textView.setText("默认");
        }
        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView == null) {//如若未加载TextView布局,创建一个ListView并设置好属性
            textView = (TextView) getView();
            textView.setHeight(80);
        } else {//使用之前加载的ListView
            textView = (TextView) convertView;
        }
        textView.setText("第" + (childPosition + 1) + "题");
        //设置做过题目的颜色
        Log.i("ceshi", "getChildView: "+groupPosition+","+childPosition);
        Subject childSub = (Subject) getChild(groupPosition, childPosition);

        if (childSub.getAnswerStatus() != childSub.ANSWER_NO) {//做过的题
            textView.setBackgroundColor(readColor);
        } else {
            textView.setBackgroundColor(unreadColor);
        }
        //设置当前位置颜色
        if (groupPosition == Subject.positionX && childPosition == Subject.positionY) {
            textView.setTextColor(readingColor);
        } else {
            textView.setTextColor(Color.BLACK);
        }

/*
        if (answerSave[groupPosition][childPosition][0] == 1) {
            textView.setBackgroundColor(readColor);
        } else {
            textView.setBackgroundColor(unreadColor);
        }//以上调整是否有答案的颜色
        if (position[CHILD_POSITION] == childPosition && position[GROUP_POSITION] == groupPosition) {
            textView.setTextColor(readingColor);
        } else {
            textView.setTextColor(Color.BLACK);
        }
        if (answerTrue != null) {//如果传入了正确答案的数组
            if (answerTrue[groupPosition][childPosition] == false) {
                textView.setBackgroundColor(Color.RED);
            }
        }
        */
        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}

