package hehut.scse.kaoyanbang.TabFragment;

public class MyHomeCardItem {
    private int picPath;
    private String title;
    private String url;

    public MyHomeCardItem(int pp, String t, String url) {
        picPath = pp;
        title = t;
        this.url = url;
    }

    public int getPicPath() {
        return picPath;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
