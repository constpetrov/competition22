package org.example.competition22.logic;

import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;

import java.util.Map;

public class WallsChecker {
    public static void check(MoveRequest request, Map<Direction, Integer> directions) {
        for (Direction direction : Direction.values()) {
            var possibleMove = Coordinate.getNextCoordinate(request.you.head, direction);
            if (possibleMove.x < 0 || possibleMove.x == request.board.width
                    || possibleMove.y < 0 || possibleMove.y == request.board.height) {
                directions.put(direction, 0);
            }
        }
    }
}
