package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;

public class MoveRequest {
//    Game game;
    public final int turn;
    public final Board board;
    public final Snake you;

    @JsonCreator
    public MoveRequest(int turn, Board board, Snake you) {
        this.turn = turn;
        this.board = board;
        this.you = you;
    }
}
