package com.aoe.sms.client.impl.emay.sp;

public class ResultInfo {

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultInfo [code=" + code + ", message=" + message + "]";
    }

}
