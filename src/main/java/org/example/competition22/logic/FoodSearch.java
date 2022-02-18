package org.example.competition22.logic;

import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;

import java.util.List;
import java.util.Map;

public class FoodSearch {
    public static void searchFood(MoveRequest request, Map<Direction, Integer> directions) {
        var longestPath = request.board.height + request.board.width;
        final var maxLength = request.board.snakes.stream().map(s -> s.length).max(Integer::compareTo).get(); //there is always at least one snake
        if (request.you.health < 1.5 * longestPath || //we are not healthy enough
                (request.you.length < maxLength &&  //we are not the longest
                request.board.snakes.size() > 1)) { //we are not alone
            request.board.food.stream()
                    .map(foodCoordinate -> getDirection(request.you.head, foodCoordinate))
                    .min(FoodDirection::compareTo) // go to the closest food
                    .ifPresent(foodDirection -> directions.put(foodDirection.getFirstDirection(), directions.get(foodDirection.getFirstDirection()) + longestPath - foodDirection.getDistance()));
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

        public FoodDirection() {
        }

        public void add(Move move) {
            this.moves.add(move);
        }

        public int getDistance() {
            return moves.stream().mapToInt(Move::getDistance).sum();
        }

        public Direction getFirstDirection() {
            return moves.get(0).getDirection();
        }

        public int compareTo(FoodDirection foodDirection) {
            return Integer.compare(this.getDistance(), foodDirection.getDistance());
        }
    }

    private static class Move {
        private final Direction direction;
        private final int distance;

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
