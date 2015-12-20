package com.aoe.capatcha.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by joey on 15-12-20.
 */
public class CapatchaInfo implements Serializable {

    @JsonProperty
    private byte[] image;

    @JsonProperty
    private String uuid;

    @JsonProperty("expire_after")
    private Date expireAfter;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public CapatchaInfo(byte[] image, String uuid) {
        this.image = image;
        this.uuid = uuid;
    }

    public Date getExpireAfter() {
        return expireAfter;
    }

    public void setExpireAfter(Date expireAfter) {
        this.expireAfter = expireAfter;
    }
}
