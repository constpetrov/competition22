package org.example.competition22.logic;

import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

public class FoodSearch {
    public static void searchFood(MoveRequest request, Map<Direction, Integer> directions) {
        final OptionalInt maxLength = request.board.snakes.stream().mapToInt(s -> s.length).max();
        if (request.you.length >= maxLength.orElse(Integer.MAX_VALUE)) return;

        Map<FoodDirection, Integer> weights = new HashMap<>();
        var longestPath = request.board.height + request.board.width;

        request.board.food.stream().map(foodCoordinate -> getDirection(request.you.head, foodCoordinate))
                .forEach(direction -> weights.put(direction, (longestPath - direction.getDistance())));

        for (var foodDirection : weights.keySet()) {
            directions.put(foodDirection.getFirstDirection(), directions.get(foodDirection.getFirstDirection()) + weights.get(foodDirection));
        }
    }

    private static FoodDirection getDirection(Coordinate head, Coordinate food) {
        FoodDirection direction = new FoodDirection();
        var deltaX = food.x - head.x;
        var deltaY = food.y - head.y;
        if (deltaX > 0) {
            direction.add(new Move(Direction.RIGHT, deltaX));
        } else if (deltaX < 0) {
            direction.add(new Move(Direction.LEFT, deltaX));
        }

        if (deltaY > 0) {
            direction.add(new Move(Direction.UP, deltaY));
        } else if (deltaY < 0) {
            direction.add(new Move(Direction.DOWN, deltaY));
        }
        return direction;
    }

    private static class FoodDirection {
        private final List<Move> moves = new java.util.ArrayList<>();
        private int distance;

        public FoodDirection() {
        }

        public void add(Move move) {
            this.moves.add(move);
            this.distance += move.getDistance();
        }

        public int getDistance() {
            return distance;
        }

        public Direction getFirstDirection() {
            return moves.get(0).getDirection();
        }

        public int compareTo(FoodDirection foodDirection) {
            return Integer.compare(this.distance, foodDirection.distance);
        }
    }

    private static class Move {
        private Direction direction;
        private int distance;

        public Move(Direction direction, int steps) {
            this.direction = direction;
            this.distance = steps;
        }

        public int getDistance() {
            return distance;
        }

        public Direction getDirection() {
            return direction;
        }
    }
}
