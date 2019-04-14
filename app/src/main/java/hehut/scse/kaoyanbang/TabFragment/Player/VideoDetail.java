package hehut.scse.kaoyanbang.TabFragment.Player;

public class VideoDetail {
    private int coverPath;
    private String videoTitle;
    private String watchNum;

    public VideoDetail(int c,String v,int w) {
        coverPath = c;
        videoTitle = v;
        watchNum = w + "人观看";
    }

    public void setCoverPath(int coverPath) {
        this.coverPath = coverPath;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
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

    public String getWatchNum() {
        return watchNum;
    }
}
