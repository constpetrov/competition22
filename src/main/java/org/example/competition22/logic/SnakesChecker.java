package org.example.competition22.logic;

import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;
import org.example.competition22.data.Snake;

import java.util.List;
import java.util.Map;

public class SnakesChecker {
    public static void check(MoveRequest request, Map<Direction, Integer> directions) {
        for (Direction direction : Direction.values()) {
            var possibleMove = Coordinate.getNextCoordinate(request.you.head, direction);
            for (var snake : request.board.snakes) {
                if (snake.length > 1) {
                    List<Coordinate> body = snake.body.subList(0, snake.body.size()-1);
                    for (Coordinate coordinate : body) {
                        if (coordinate.equals(possibleMove)) {
                            directions.put(direction, 0);
                        }
                    }
                }
                if (!snake.id.equals(request.you.id)) avoidHeadCollision(request.you, snake, directions);
            }
        }
    }

    private static void avoidHeadCollision(Snake we, Snake enemy, Map<Direction, Integer> directions) {
        if (enemy.length < we.length) return; // we will win collision
        directions.keySet().forEach(direction -> {
            enemy.head.neighbours().forEach(enemyCoord -> {
                final Coordinate ourCoord = Coordinate.getNextCoordinate(we.head, direction);
                if (enemyCoord.equals(ourCoord)) directions.put(direction, directions.get(direction)/2);
            });
        });
    }
}
