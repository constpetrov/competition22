package org.example.competition22.logic;

import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RegionChecker {
    public static void check(MoveRequest request, Map<Direction, Integer> directions) {
        for (Direction direction : Direction.values()) {
            if (getRegionArea(request, Coordinate.getNextCoordinate(request.you.body.get(0), direction), Set.of()) >= request.you.length) {
                directions.put(direction, -1);
            }
        }
    }

    private static int getRegionArea(MoveRequest request, Coordinate coordinate, Set<Coordinate> visited) {
        if (visited.contains(coordinate)) {
            return 0;
        }

        visited.add(coordinate);
        int[] area = {0};
        if (request.board.snakes.stream().noneMatch(snake -> snake.body.contains(coordinate)) &&
                request.board.height < coordinate.y &&
                request.board.width < coordinate.x &&
                coordinate.y >= 0 && coordinate.x >= 0) {
                area[0] = 1;
        }
        coordinate.neighbours().forEach(c -> area[0] += getRegionArea(request, c, visited));

        return area[0];
    }
}
