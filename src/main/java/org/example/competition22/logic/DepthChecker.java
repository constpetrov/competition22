package org.example.competition22.logic;

import org.example.competition22.data.Board;
import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class DepthChecker {
    public static void check(MoveRequest request, Map<Direction, Integer> directions) {
        directions.keySet().forEach(direction -> {
            final Set<Coordinate> obstacles = request.board.snakes.stream()
                    .flatMap(s -> s.body.stream())
                    .collect(Collectors.toSet());
            final Coordinate nextCoordinate = Coordinate.getNextCoordinate(request.you.head, direction);
            LongAdder acc = new LongAdder();
            calcDepth(nextCoordinate, new HashSet<>(obstacles), request.board, acc);
            final double score = acc.doubleValue() / (request.board.width * request.board.height);
            directions.put(direction, (int) (directions.get(direction) * score));
        });
    }

    private static void calcDepth(Coordinate point, Set<Coordinate> obstacles, Board board, LongAdder acc) {
        if (point.x < 0 || point.x >= board.width
                || point.y < 0 || point.y >= board.height) return;
        if (obstacles.contains(point)) return;

        acc.increment();
        obstacles.add(point);

        for (Direction stepDirection : Direction.values()) {
            final Coordinate nextPoint = Coordinate.getNextCoordinate(point, stepDirection);
            calcDepth(nextPoint, obstacles, board, acc);
        }
    }
}
