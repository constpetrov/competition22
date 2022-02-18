package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Direction {
    @JsonProperty("up")
    UP,

    @JsonProperty("down")
    DOWN,

    @JsonProperty("left")
    LEFT,

    @JsonProperty("right")
    RIGHT;
}
