package org.example.competition22.logic;

import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;

import java.util.HashMap;
import java.util.Map;

public class BodyChecker {
    public static void check(MoveRequest request, Map<Direction, Integer> directions) {
        for (Direction direction : Direction.values()) {
            var possibleMove = getNextCoordinate(request.you.head, direction);
            for (Coordinate coordinate : request.you.body) {
                if (coordinate.equals(possibleMove)) {
                    directions.put(direction, 0);
                    break;
                } else {
                    directions.put(direction, 1);
                }
            }
        }
    }

    private static Coordinate getNextCoordinate(Coordinate head, Direction direction) {
        return switch (direction) {
            case UP -> new Coordinate(head.x, head.y + 1);
            case DOWN -> new Coordinate(head.x, head.y - 1);
            case LEFT -> new Coordinate(head.x - 1, head.y);
            case RIGHT -> new Coordinate(head.x + 1, head.y);
        };
    }
}
