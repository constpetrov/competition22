package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveRequest {
    @JsonProperty
    public final Game game;
    @JsonProperty
    public final int turn;
    @JsonProperty
    public final Board board;
    @JsonProperty
    public final Snake you;

    @JsonCreator
    public MoveRequest(@JsonProperty("game") Game game, @JsonProperty("turn") int turn,
                       @JsonProperty("board") Board board,
                       @JsonProperty("you") Snake you) {
        this.game = game;
        this.turn = turn;
        this.board = board;
        this.you = you;
    }

    @Override
    public String toString() {
        return "MoveRequest{" +
                "game=" + game +
                ", turn=" + turn +
                ", board=" + board +
                ", you=" + you +
                '}';
    }
}
