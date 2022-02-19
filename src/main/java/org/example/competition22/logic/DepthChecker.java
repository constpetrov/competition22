package org.example.competition22.logic;

import org.example.competition22.data.Board;
import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;
import org.example.competition22.data.RulesetName;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class DepthChecker {
    public static void check(MoveRequest request, Map<Direction, Double> directions) {
        directions.keySet().forEach(direction -> {
            final Set<Coordinate> obstacles = request.board.snakes.stream()
                    .flatMap(s -> s.body.subList(0,s.body.size()-1).stream())
                    .collect(Collectors.toSet());
            final Coordinate nextCoordinate = Coordinate.getNextCoordinate(request, direction);
            LongAdder acc = new LongAdder();
            calcDepth(nextCoordinate, new HashSet<>(obstacles), request, acc);
            final double score = acc.doubleValue() / (request.board.width * request.board.height);
            directions.put(direction, directions.get(direction) * score);
        });
    }

    private static void calcDepth(Coordinate point, Set<Coordinate> obstacles, MoveRequest request, LongAdder acc) {
        if (point.x < 0 || point.x >= request.board.width
                || point.y < 0 || point.y >= request.board.height) return;
        if (obstacles.contains(point)) return;
        if (request.game.ruleset.name == RulesetName.WRAPPED &&
                acc.longValue() >= request.board.height * request.board.width) return;

        acc.increment();
        obstacles.add(point);

        for (Direction stepDirection : Direction.values()) {
            final Coordinate nextPoint = Coordinate.getNextCoordinate(request, point, stepDirection);
            calcDepth(nextPoint, obstacles, request, acc);
        }
    }
}
