package org.example.competition22.logic;

import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;

import java.util.Map;

public class SnakesChecker {
    public static void check(MoveRequest request, Map<Direction, Integer> directions) {
        for (Direction direction : Direction.values()) {
            var possibleMove = Coordinate.getNextCoordinate(request.you.head, direction);
            for (var snake : request.board.snakes) {
                for (Coordinate coordinate : snake.body) {
                    if (coordinate.equals(possibleMove)) {
                        directions.put(direction, 0);
                        break;
                    }
                }
            }
        }
    }
}
