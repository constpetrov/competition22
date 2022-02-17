package org.example.competition22.logic;

import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RegionChecker {
    public static void check(MoveRequest request, Map<Direction, Integer> directions) {
        for (Direction direction : Direction.values()) {
            if (getRegionArea(request, Coordinate.getNextCoordinate(request.you.head, direction), new HashSet<Coordinate>()) <= request.you.length) {
                directions.put(direction, -1);
            }
        }
    }

    private static int getRegionArea(MoveRequest request, Coordinate coordinate, Set<Coordinate> visited) {
        if (visited.contains(coordinate) ||
                !isOnBoard(request, coordinate) ||
                request.board.snakes.stream().anyMatch(snake -> snake.body.contains(coordinate))) {
            return 0;
        } else {
            visited.add(coordinate);
            for (Coordinate c : coordinate.neighbours()) {
                if (!visited.contains(c) && isOnBoard(request, c)) {
                    return 1 + getRegionArea(request, c, visited);
                }
            }
        }
        return 0;
    }

    private static boolean isOnBoard(MoveRequest request, Coordinate c) {
        return request.board.height > c.y && request.board.width > c.x && c.y >= 0 && c.x >= 0;
    }
}
