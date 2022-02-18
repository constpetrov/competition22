package org.example.competition22;


import org.example.competition22.data.Board;
import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.MoveRequest;
import org.example.competition22.data.MoveResponse;
import org.example.competition22.data.Snake;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(org.junit.runners.JUnit4.class)
public class MainResourceTest {

    @Test
    public void assignWeightsTestGoAnywhere() {
        MainResource mainResource = new MainResource();
        Snake you = new Snake("", "", 5, Collections.singletonList(new Coordinate(2, 2)), "", new Coordinate(2, 2), 2);
        Board board = new Board(5, 5, Collections.emptyList(), Collections.singletonList(you));
        MoveRequest moveRequest = new MoveRequest(1, board, you);
        Map<Direction, Integer> directionIntegerMap = mainResource.assignWeights(moveRequest);

        for (Map.Entry<Direction, Integer> entry : directionIntegerMap.entrySet()) {
            assertTrue(entry.getValue() > 0);
        }
    }

    @Test
    public void assignWeightsTestDontGoUp() {
        MainResource mainResource = new MainResource();
        Coordinate head = new Coordinate(2, 4);
        Snake you = new Snake("", "", 5, Collections.singletonList(head), "", head, 2);
        Board board = new Board(5, 5, Collections.emptyList(), Collections.singletonList(you));
        MoveRequest moveRequest = new MoveRequest(1, board, you);
        Map<Direction, Integer> directionIntegerMap = mainResource.assignWeights(moveRequest);

        assertEquals(-1, (int) directionIntegerMap.get(Direction.UP));
    }

    @Test
    @Ignore
    public void assignWeightsTestDontGoInClosedSpace() {
        MainResource mainResource = new MainResource();
        Coordinate head = new Coordinate(3, 4);
        var body = Arrays.asList(head, new Coordinate(3, 3), new Coordinate(4, 3));
        Snake you = new Snake("", "", 5, body, "", head, 3);
        Board board = new Board(5, 5, Collections.emptyList(), Collections.singletonList(you));
        MoveRequest moveRequest = new MoveRequest(1, board, you);
        Map<Direction, Integer> directionIntegerMap = mainResource.assignWeights(moveRequest);

        assertEquals(-1, (int) directionIntegerMap.get(Direction.RIGHT));
        assertEquals(-1, (int) directionIntegerMap.get(Direction.DOWN));
        assertEquals(-1, (int) directionIntegerMap.get(Direction.UP));

        assertNotEquals(-1, (int) directionIntegerMap.get(Direction.LEFT));
    }

    @Test
    public void shouldGoUp() {
        MoveRequest moveRequest = new MoveRequest(82,
                new Board(7, 7, Arrays.asList(
                        new Coordinate(6, 5),
                        new Coordinate(5, 6), new Coordinate(1, 4)),
                        Arrays.asList(
                                new Snake("gs_vMJ6HHQG7Q4rFXBvqDFPkKcT", "Jawa dev - oleg", 99,
                                        Arrays.asList(new Coordinate(3, 1), new Coordinate(2, 1), new Coordinate(1, 1), new Coordinate(1, 0), new Coordinate(2, 0), new Coordinate(3, 0), new Coordinate(4, 0), new Coordinate(5, 0), new Coordinate(5, 1), new Coordinate(4, 1), new Coordinate(4, 2), new Coordinate(5, 2), new Coordinate(5, 3), new Coordinate(4, 3), new Coordinate(3, 3), new Coordinate(2, 3)),
                                        "167", new Coordinate(3, 1), 16))),
                new Snake("gs_vMJ6HHQG7Q4rFXBvqDFPkKcT", "Jawa dev - oleg", 99,
                        Arrays.asList(new Coordinate(3, 1), new Coordinate(2, 1), new Coordinate(1, 1), new Coordinate(1, 0), new Coordinate(2, 0), new Coordinate(3, 0), new Coordinate(4, 0), new Coordinate(5, 0), new Coordinate(5, 1), new Coordinate(4, 1), new Coordinate(4, 2), new Coordinate(5, 2), new Coordinate(5, 3), new Coordinate(4, 3), new Coordinate(3, 3), new Coordinate(2, 3)),
                        "167", new Coordinate(3, 1), 16));
        Map<Direction, Integer> directionIntegerMap = new MainResource().assignWeights(moveRequest);
        assertEquals(-1, (int) directionIntegerMap.get(Direction.LEFT));
        assertEquals(-1, (int) directionIntegerMap.get(Direction.RIGHT));
        assertEquals(-1, (int) directionIntegerMap.get(Direction.DOWN));
        assertTrue(directionIntegerMap.get(Direction.UP) > 0);
    }

    @Test
    public void makeMoves(){
        MainResource mainResource = new MainResource();
        Snake you = new Snake("", "", 5, Arrays.asList(new Coordinate(2, 2), new Coordinate(1, 2), new Coordinate(0,2)), "", new Coordinate(2, 2), 2);
        Board board = new Board(5, 5, Collections.emptyList(), Collections.singletonList(you));
        MoveRequest moveRequest = new MoveRequest(1, board, you);
        for (int i = 0; i < 100; i++) {
            var response = mainResource.move(moveRequest);
            moveRequest = updateMoveRequest(moveRequest, response);
            assertTrue(isAlive(moveRequest));
        }
    }

    private MoveRequest updateMoveRequest(MoveRequest request, MoveResponse response) {
        var newBody = new ArrayList<Coordinate>();
        newBody.add(Coordinate.getNextCoordinate(request.you.head, Direction.valueOf(response.move.toUpperCase())));
        newBody.addAll(request.you.body.subList(0, request.you.body.size() - 1));
        return new MoveRequest(request.turn + 1, request.board, new Snake(request.you.id, request.you.name, request.you.health, newBody, request.you.latency, newBody.get(0), request.you.body.size()));
    }

    private boolean isAlive(MoveRequest request) {
        return isHealthy(request.you.health) &&
                !hasCollidedWithBody(request) &&
                isOnBoard(request, request.you.head);
    }

    private boolean isOnBoard(MoveRequest request, Coordinate head) {
        return head.x >= 0 && head.x < request.board.width && head.y >= 0 && head.y < request.board.height;
    }

    private boolean isHealthy(int you) {
        return you > 0;
    }

    private boolean hasCollidedWithBody(MoveRequest request) {
        return request.you.body.subList(1, request.you.body.size()).contains(request.you.head);
    }
}
