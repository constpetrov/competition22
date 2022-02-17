package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;


public class Board {
    public final int height;
    public final int width;
    public final List<Coordinate> food;
//   public final List<Coordinate> hazards;
    public final List<Snake> snakes;

    @JsonCreator
    public Board(int height, int width, List<Coordinate> food, List<Snake> snakes) {
        this.height = height;
        this.width = width;
        this.food = food;
        this.snakes = snakes;
    }
}
