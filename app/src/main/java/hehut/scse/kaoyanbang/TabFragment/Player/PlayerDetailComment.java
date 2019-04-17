package hehut.scse.kaoyanbang.TabFragment.Player;

public class PlayerDetailComment {
    private String mid;
    private String userCover;
    private String userName;
    private String floor;
    private String date;
    private String content;
    private String likeNum;

    public PlayerDetailComment(String ui,String uc,String un,int f,String d,String c,int ln) {
        mid = ui;
        userCover = uc;
        userName = un;
        floor = "#" + f;
        date = d;
        content = c;
        likeNum = ln + "";
    }

    public boolean mIdEqual(String u) {
        if (u.equals(mid)) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserCover() {
        return userCover;
    }

    public String getUserName() {
        return userName;
    }

    public String getFloor() {
        return floor;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getLikeNum() {
        return likeNum;
    }
}
