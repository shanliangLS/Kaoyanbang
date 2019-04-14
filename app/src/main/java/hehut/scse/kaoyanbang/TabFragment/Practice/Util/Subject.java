package hehut.scse.kaoyanbang.TabFragment.Practice.Util;

import java.io.Serializable;

public class Subject implements Serializable {
    //状态变量
    //trueAnswer变量的值
    public static final int ANSWER_NO = 0;
    public static final int ANSWER_TURE = 1;
    public static final int ANSWER_FALSE = 2;
    //标记当前位置
    public static int positionX = 0;
    public static int positionY = 0;
    //数据变量
    private String a;
    private String b;
    private String c;
    private String d;
    private String title;
    private String answer;
    private int answerStatus;

    private boolean answerAStatic;
    private boolean answerBStatic;
    private boolean answerCStatic;
    private boolean answerDStatic;

    /**
     * 单选多选题构造函数
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param title
     * @param answer
     */
    public Subject(String a, String b, String c, String d, String title, String answer) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.title = title;
        this.answer = answer;
        answerStatus = ANSWER_NO;
        answerAStatic = false;
        answerBStatic = false;
        answerCStatic = false;
        answerDStatic = false;
    }

    /**
     * 判断题构造函数
     *
     * @param judgeTitle
     * @param answer
     */
    public Subject(String judgeTitle, String answer) {
        this.title = judgeTitle;
        this.answer = answer;
        answerStatus = ANSWER_NO;
        answerAStatic = false;
        answerBStatic = false;
        answerCStatic = false;
        answerDStatic = false;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    /**
     * 获得消除了题号的题目
     *
     * @return
     */

    public String getTitle() {
        int tempLocation = 0;
        if ((tempLocation = title.indexOf(".")) >= 0) {
            return title.substring(tempLocation + 1);
        } else if ((tempLocation = title.indexOf("锛�")) >= 0) {
            return title.substring(tempLocation + 1);
        } else if ((tempLocation = title.indexOf("銆�")) >= 0) {
            return title.substring(tempLocation + 1);
        }
        return title;
    }

    public String getAnswer() {
        return answer;
    }

    /**
     * 没有答案,正确,错误分别为 0,1,2     *
     *
     * @return
     */
    public int getAnswerStatus() {
        return answerStatus;
    }

    /**
     * ANSWER_NO=0;
     * ANSWER_TRUE=1;
     * ANSWER_FALSE=2;    *
     *
     * @param answerStatus
     */
    public void setAnswerStatus(int answerStatus) {
        this.answerStatus = answerStatus;
    }

    public boolean getAnswerAStatic() {
        return answerAStatic;
    }

    public void setAnswerAStatic(boolean answerAStatic) {
        answerAStatic = answerAStatic;
    }

    public boolean getAnswerBStatic() {
        return answerBStatic;
    }

    public void setAnswerBStatic(boolean answerBStatic) {
        this.answerBStatic = answerBStatic;
    }

    public boolean getAnswerCStatic() {
        return answerCStatic;
    }

    public void setAnswerCStatic(boolean answerCStatic) {
        this.answerCStatic = answerCStatic;
    }

    public boolean getAnswerDStatic() {
        return answerDStatic;
    }

    public void setAnswerDStatic(boolean answerDStatic) {
        this.answerDStatic = answerDStatic;
    }
}