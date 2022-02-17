package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;

public class MoveResponse {
    public final String move;
    public final String shout;

    @JsonCreator
    public MoveResponse(String move, String shout) {this.move = move;
        this.shout = shout;
    }
}
