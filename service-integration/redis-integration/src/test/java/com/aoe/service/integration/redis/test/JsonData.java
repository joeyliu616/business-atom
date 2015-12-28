package com.aoe.service.integration.redis.test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by joey on 15-12-27.
 */
public class JsonData implements Serializable{
    @JsonProperty
    private String id;
    @JsonProperty
    private Long value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
