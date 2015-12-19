package com.aoe.base.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 15-12-18.
 * 通用Dto
 */
public class CommonResponse<T> {

    @JsonProperty
    CommonResult result;

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
