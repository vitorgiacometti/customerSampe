package br.example.app.common;

public class ErrorMsg {

    private String msg;
    private String status;
    private String detail;
    private int code;

    public ErrorMsg(String status, String msg, String detail, int code) {
        this.status=status;
        this.msg = msg;
        this.detail = detail;
        this.code = code;
    }

   public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
