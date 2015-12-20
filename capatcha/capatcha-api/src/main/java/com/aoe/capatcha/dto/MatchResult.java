package com.aoe.capatcha.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 15-12-20.
 */
public class MatchResult {
    @JsonProperty("is_match")
    private boolean isMatch;

    public boolean isMatch() {
        return isMatch;
    }

    public void setIsMatch(boolean isMatch) {
        this.isMatch = isMatch;
    }
}
