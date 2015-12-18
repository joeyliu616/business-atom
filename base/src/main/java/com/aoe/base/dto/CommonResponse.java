package com.aoe.base.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 15-12-18.
 * 通用Dto
 */
public class CommonResponse<T> {
    @JsonProperty
    private Integer code;
    @JsonProperty
    private String msg;
    @JsonProperty
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
