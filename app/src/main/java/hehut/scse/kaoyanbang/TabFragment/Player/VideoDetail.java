package hehut.scse.kaoyanbang.TabFragment.Player;

public class VideoDetail {
    private int coverPath;
    private String videoTitle;
    private String videoTag;
    private String watchNum;

    public VideoDetail(int c,String v,String t,int w) {
        coverPath = c;
        videoTitle = v;
        videoTag = t;
        watchNum = w + "";
    }

    public void setCoverPath(int coverPath) {
        this.coverPath = coverPath;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setVideoTag(String videoTag) {
        this.videoTag = videoTag;
    }

    public void setWatchNum(String watchNum) {
        this.watchNum = watchNum;
    }

    public int getCoverPath() {
        return coverPath;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoTag() {
        return videoTag;
    }

    public String getWatchNum() {
        return watchNum;
    }
}
