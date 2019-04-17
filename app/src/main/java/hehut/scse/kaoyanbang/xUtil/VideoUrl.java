package hehut.scse.kaoyanbang.xUtil;

import java.util.List;

public class VideoUrl {
    /**
     * from : local
     * result : suee
     * quality : 6
     * format : mp4
     * timelength : 199829
     * accept_format : mp4
     * accept_quality : [6]
     * video_codecid : 7
     * video_project : false
     * seek_param : start
     * seek_type : second
     * durl : [{"order":1,"length":199829,"size":5913929,"url":"http://upos-hz-mirrorwcsu.acgvideo.com/upgcxcode/39/42/66504239/66504239-1-6.mp4?e=ig8euxZM2rNcNbug7WdVtWug7WdVNEVEuCIv29hEn0l5QK==&deadline=1555507794&gen=playurl&nbs=1&oi=3723376653&os=wcsu&platform=html5&trid=22c38fccbb8a40b3a2e921389f1615b5&uipk=5&upsig=478d41506f494758e20bfb2375e2c2ba&uparams=e,deadline,gen,nbs,oi,os,platform,trid,uipk"}]
     * img : https://i0.hdslb.com/bfs/archive/d9533e2079e3f37ad49049165872592635c09474.jpg
     * cid : https://comment.bilibili.com/66504239.xml
     * fromview : vupload
     */

    private String from;
    private String result;
    private int quality;
    private String format;
    private int timelength;
    private String accept_format;
    private int video_codecid;
    private boolean video_project;
    private String seek_param;
    private String seek_type;
    private String img;
    private String cid;
    private String fromview;
    private List<Integer> accept_quality;
    private List<DurlBean> durl;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getTimelength() {
        return timelength;
    }

    public void setTimelength(int timelength) {
        this.timelength = timelength;
    }

    public String getAccept_format() {
        return accept_format;
    }

    public void setAccept_format(String accept_format) {
        this.accept_format = accept_format;
    }

    public int getVideo_codecid() {
        return video_codecid;
    }

    public void setVideo_codecid(int video_codecid) {
        this.video_codecid = video_codecid;
    }

    public boolean isVideo_project() {
        return video_project;
    }

    public void setVideo_project(boolean video_project) {
        this.video_project = video_project;
    }

    public String getSeek_param() {
        return seek_param;
    }

    public void setSeek_param(String seek_param) {
        this.seek_param = seek_param;
    }

    public String getSeek_type() {
        return seek_type;
    }

    public void setSeek_type(String seek_type) {
        this.seek_type = seek_type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getFromview() {
        return fromview;
    }

    public void setFromview(String fromview) {
        this.fromview = fromview;
    }

    public List<Integer> getAccept_quality() {
        return accept_quality;
    }

    public void setAccept_quality(List<Integer> accept_quality) {
        this.accept_quality = accept_quality;
    }

    public List<DurlBean> getDurl() {
        return durl;
    }

    public void setDurl(List<DurlBean> durl) {
        this.durl = durl;
    }

    public static class DurlBean {
        /**
         * order : 1
         * length : 199829
         * size : 5913929
         * url : http://upos-hz-mirrorwcsu.acgvideo.com/upgcxcode/39/42/66504239/66504239-1-6.mp4?e=ig8euxZM2rNcNbug7WdVtWug7WdVNEVEuCIv29hEn0l5QK==&deadline=1555507794&gen=playurl&nbs=1&oi=3723376653&os=wcsu&platform=html5&trid=22c38fccbb8a40b3a2e921389f1615b5&uipk=5&upsig=478d41506f494758e20bfb2375e2c2ba&uparams=e,deadline,gen,nbs,oi,os,platform,trid,uipk
         */

        private int order;
        private int length;
        private int size;
        private String url;

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
