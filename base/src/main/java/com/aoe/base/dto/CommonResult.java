package com.aoe.base.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 15-12-20.
 */
public class CommonResult {
    @JsonProperty
    private Integer code = 0;
    //针对开发者的提示
    @JsonProperty
    private String msg = "";
    //针对前端展示的提示
    @JsonProperty
    private String toast;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getToast() {
        return toast;
    }

    public void setToast(String toast) {
        this.toast = toast;
    }

    public CommonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
