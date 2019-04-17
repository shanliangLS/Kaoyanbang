package hehut.scse.kaoyanbang.bean;

public class MyNews {
    public String news_type;
    public String news_url;
    public String news_title;

    public MyNews() {

    }

    public MyNews(String news_type, String news_url, String news_title) {
        this.news_type = news_type;
        this.news_url = news_url;
        this.news_title = news_title;
    }

    public String getNews_title() {
        return news_title;
    }

    public String getNews_url() {
        return news_url;
    }

    public String getNews_type() {
        return news_type;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public void setNews_url(String news_url) {
        this.news_url = news_url;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }
}
