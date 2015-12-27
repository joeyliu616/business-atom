package com.aoe.base.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by joey on 15-12-18.
 * 通用Dto
 */
public class CommonResponse<T extends Serializable> implements Serializable{

    @JsonProperty
    CommonResult result = new CommonResult(0,"");

    @JsonProperty
    private T data;

    public CommonResult getResult() {
        return result;
    }

    //hack.
    public CommonResponse<T> setResult(CommonResult result) {
        this.result = result;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
