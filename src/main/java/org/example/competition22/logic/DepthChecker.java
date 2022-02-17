package org.example.competition22.logic;

import org.example.competition22.data.Board;
import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class DepthChecker {
    private final static Coordinate EOQ = new Coordinate(Integer.MIN_VALUE, Integer.MIN_VALUE);

    public static void check(MoveRequest request, Map<Direction, Integer> directions) {
        directions.keySet().forEach(direction -> {
            final Set<Coordinate> obstacles = request.board.snakes.stream()
                    .flatMap(s -> s.body.stream())
                    .collect(Collectors.toSet());
            final ArrayDeque<Coordinate> points = new ArrayDeque<>();
            points.add(EOQ);
            final Coordinate nextCoordinate = Coordinate.getNextCoordinate(request.you.head, direction);
            points.addLast(nextCoordinate);
            final int depth = accumulate(points, obstacles, request.board);
            final double score = ((double)request.board.width * request.board.height) / depth;
            directions.put(direction, (int) (directions.get(direction) * score));
        });
    }

    private static int accumulate(Deque<Coordinate> points, Set<Coordinate> obstacles, Board board) {
        int result = 0;
        Coordinate point;
        while ((point = points.removeLast()) != EOQ) {
            obstacles.add(point);
            for (Direction stepDirection : Direction.values()) {
                final HashSet<Coordinate> localObstacles = new HashSet<>(obstacles);
                final Coordinate nextCoordinate = Coordinate.getNextCoordinate(point, stepDirection);
                localObstacles.add(nextCoordinate);
                if (nextCoordinate.x >= 0 && nextCoordinate.y < board.width
                        && nextCoordinate.y >= 0 && nextCoordinate.y < board.height
                        && !localObstacles.contains(nextCoordinate)
                ) {
                    result++;
                    points.addLast(nextCoordinate);
                }
            }
        }
        return result;
    }
}
