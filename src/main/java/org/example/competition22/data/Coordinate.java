package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Coordinate {
    @JsonProperty
    public final int x;
    @JsonProperty
    public final int y;

    @JsonCreator
    public Coordinate(@JsonProperty("x") int x,
                      @JsonProperty("y") int y) {
        this.x = x;
        this.y = y;
    }

    public List<Coordinate> neighbours(MoveRequest request) {
        return Arrays.asList(
                getNextCoordinate(request, Direction.UP),
                getNextCoordinate(request, Direction.DOWN),
                getNextCoordinate(request, Direction.LEFT),
                getNextCoordinate(request, Direction.RIGHT)
        );
    }

    public static Coordinate getNextCoordinate(MoveRequest request, Direction direction) {
        return getNextCoordinate(request, request.you.head, direction);
    }

    public static Coordinate getNextCoordinate(MoveRequest request, Coordinate point, Direction direction) {
        if (request.game.ruleset.name == RulesetName.WRAPPED) {
            return getNextCoordinateWrapped(request.board, point, direction);
        }
        return getNextCoordinateStandard(request.board, point, direction);
    }

    private static Coordinate getNextCoordinateStandard(Board board, Coordinate point, Direction direction) {
        return switch (direction) {
            case UP -> new Coordinate(point.x, point.y + 1);
            case DOWN -> new Coordinate(point.x, point.y - 1);
            case LEFT -> new Coordinate(point.x - 1, point.y);
            case RIGHT -> new Coordinate(point.x + 1, point.y);
        };
    }

    private static Coordinate getNextCoordinateWrapped(Board board, Coordinate point, Direction direction) {
        return switch (direction) {
            case UP -> new Coordinate(point.x, (point.y + 1) % board.height);
            case DOWN -> new Coordinate(point.x, (point.y - 1 + board.height) % board.height);
            case LEFT -> new Coordinate((point.x - 1 + board.width) % board.width, point.y);
            case RIGHT -> new Coordinate((point.x + 1) % board.width, point.y);
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
