package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {
    @JsonProperty public final int x;
    @JsonProperty public final int y;

    @JsonCreator
    public Coordinate(@JsonProperty("x") int x,
                      @JsonProperty("y") int y) {
        this.x = x;
        this.y = y;
    }
}
