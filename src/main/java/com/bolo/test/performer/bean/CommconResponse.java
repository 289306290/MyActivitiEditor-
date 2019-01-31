package com.bolo.test.performer.bean;

public class CommconResponse {
    private String result;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    static public CommconResponse success() {
        return new CommconResponse("操作成功");
    }

    static public CommconResponse fail(String msg) {
        CommconResponse response = new CommconResponse("操作失败");
        response.setMsg(msg);
        return response;
    }

    CommconResponse(String result) {
        this.result = result;
    }
}
