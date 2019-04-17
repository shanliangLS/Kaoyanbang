package hehut.scse.kaoyanbang.bean;

public class MyResponse {
    public String status;
    public String reason;

    public MyResponse()
    {

    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
