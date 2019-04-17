package hehut.scse.kaoyanbang.bean;

import java.util.ArrayList;

public class MyNewsResponse {
    public String status;
    public String reason;
    public ArrayList<MyNews> data;

    public MyNewsResponse() {

    }

    public MyNewsResponse(String status, String reason, ArrayList<MyNews> data) {
        this.status = status;
        this.reason = reason;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public ArrayList<MyNews> getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setData(ArrayList<MyNews> data) {
        this.data = data;
    }
}
