package com.aoe.service.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by joey on 15-12-24.
 */
@JsonAutoDetect
public class T implements Serializable{
    @JsonProperty
    private String k1;

    @JsonProperty
    private Long k2;

    public String getK1() {
        return k1;
    }

    public void setK1(String k1) {
        this.k1 = k1;
    }

    public Long getK2() {
        return k2;
    }

    public void setK2(Long k2) {
        this.k2 = k2;
    }
}
