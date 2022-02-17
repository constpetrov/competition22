package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveResponse {
    @JsonProperty
    public final Direction move;
    @JsonProperty
    public final String shout;

    @JsonCreator
    public MoveResponse(@JsonProperty("move") Direction move,
                        @JsonProperty("shout") String shout) {
        this.move = move;
        this.shout = shout;
    }
}
