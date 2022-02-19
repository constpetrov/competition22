package org.example.competition22.logic;

import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;
import org.example.competition22.data.Snake;

import java.util.List;
import java.util.Map;

public class SnakesChecker {
    public static void check(MoveRequest request, Map<Direction, Double> directions) {
        for (Direction direction : Direction.values()) {
            var possibleMove = Coordinate.getNextCoordinate(request, direction);
            for (var snake : request.board.snakes) {
                if (snake.length > 1) {
                    List<Coordinate> body = snake.body.subList(0, snake.body.size()-1);
                    for (Coordinate coordinate : body) {
                        if (coordinate.equals(possibleMove)) {
                            directions.put(direction, -1.0);
                        }
                    }
                }
                if (!snake.id.equals(request.you.id)) avoidHeadCollision(request, snake, directions);
            }
        }
    }

    private static void avoidHeadCollision(MoveRequest request, Snake enemy, Map<Direction, Double> directions) {
        double weight = enemy.length < request.you.length ? 2 : 0.3;
        directions.keySet().forEach(direction -> {
            enemy.head.neighbours(request).forEach(enemyCoord -> {
                final Coordinate ourCoord = Coordinate.getNextCoordinate(request, direction);
                if (enemyCoord.equals(ourCoord)) directions.put(direction, directions.get(direction) * weight);
            });
        });
    }
}
