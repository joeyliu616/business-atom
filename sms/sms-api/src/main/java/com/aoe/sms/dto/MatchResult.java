package com.aoe.sms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 15-12-20.
 */
public class MatchResult {
    @JsonProperty("is_match")
    boolean isMatch;

    public boolean isMatch() {
        return isMatch;
    }

    public void setIsMatch(boolean isMatch) {
        this.isMatch = isMatch;
    }
}
