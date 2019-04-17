package hehut.scse.kaoyanbang.TabFragment;

public class HomeCardItem {
    private int picPath;
    private String title;

    public HomeCardItem(int pp,String t) {
        picPath = pp;
        title = t;
    }

    public int getPicPath() {
        return picPath;
    }

    public String getTitle() {
        return title;
    }
}
