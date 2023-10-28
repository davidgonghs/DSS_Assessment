package com.davidgonghongshen.alienclock.req;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlienTimeRequest {
    private final String alienTime;

    @JsonCreator
    public AlienTimeRequest(@JsonProperty("alienTime") String alienTime) {
        this.alienTime = alienTime;
    }

    public String getAlienTime() {
        return alienTime;
    }
}