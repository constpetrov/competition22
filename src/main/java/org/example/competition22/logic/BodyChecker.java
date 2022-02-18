package org.example.competition22.logic;

import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;

import java.util.Map;

public class BodyChecker {
    public static void check(MoveRequest request, Map<Direction, Double> directions) {
        if (request.you.body.size() == 1) return;
        for (Direction direction : Direction.values()) {
            var possibleMove = Coordinate.getNextCoordinate(request.you.head, direction);
            for (Coordinate coordinate : request.you.body.subList(0, request.you.body.size()-1)) {
                if (coordinate.equals(possibleMove)) {
                    directions.put(direction, -1.0);
                }
            }
        }
    }
}
