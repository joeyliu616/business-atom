package com.aoe.capatcha.dto;

import java.io.Serializable;

/**
 * Created by joey on 15-12-20.
 */
public class CapatchaInfo implements Serializable {

    private byte[] image;

    private String uuid;

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
}
