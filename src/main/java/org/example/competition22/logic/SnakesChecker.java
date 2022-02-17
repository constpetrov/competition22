package org.example.competition22.logic;

import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;

import java.util.List;
import java.util.Map;

public class SnakesChecker {
    public static void check(MoveRequest request, Map<Direction, Integer> directions) {
        for (Direction direction : Direction.values()) {
            var possibleMove = Coordinate.getNextCoordinate(request.you.head, direction);
            for (var snake : request.board.snakes) {
                List<Coordinate> body = snake.body;
                if (body.size() > 1) body = body.subList(0, body.size()-1);
                for (Coordinate coordinate : body) {
                    if (coordinate.equals(possibleMove)) {
                        directions.put(direction, 0);
                    }
                }
            }
        }
    }
}
