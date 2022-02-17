package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Coordinate {
    public final int x;
    public final int y;

    @JsonCreator
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
