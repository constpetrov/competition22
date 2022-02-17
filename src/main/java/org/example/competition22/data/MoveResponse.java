package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveResponse {
    @JsonProperty
    public final String move;
    @JsonProperty
    public final String shout;

    @JsonCreator
    public MoveResponse(@JsonProperty("move") String move,
                        @JsonProperty("shout") String shout) {
        this.move = move;
        this.shout = shout;
    }
}
