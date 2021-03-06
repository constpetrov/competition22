package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Coordinate {
    @JsonProperty public final int x;
    @JsonProperty public final int y;

    @JsonCreator
    public Coordinate(@JsonProperty("x") int x,
                      @JsonProperty("y") int y) {
        this.x = x;
        this.y = y;
    }

    public List<Coordinate> neighbours() {
        return Arrays.asList(
                new Coordinate(x, y+1),
                new Coordinate(x, y-1),
                new Coordinate(x+1, y),
                new Coordinate(x-1, y)
        );
    }

    public static Coordinate getNextCoordinate(Coordinate head, Direction direction) {
        return switch (direction) {
            case UP -> new Coordinate(head.x, head.y + 1);
            case DOWN -> new Coordinate(head.x, head.y - 1);
            case LEFT -> new Coordinate(head.x - 1, head.y);
            case RIGHT -> new Coordinate(head.x + 1, head.y);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
