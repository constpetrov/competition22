package org.example.competition22;


import org.example.competition22.data.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(org.junit.runners.JUnit4.class)
public class MainResourceTest {

    @Test
    public void assignWeightsTestGoAnywhere() {
        MainResource mainResource = new MainResource();
        Snake you = new Snake("", "", 5, Collections.singletonList(new Coordinate(2,2)), "", new Coordinate(2,2), 2);
        Board board = new Board(5, 5, Collections.emptyList(), Collections.emptyList());
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
    public void shouldGoUp() {
        MoveRequest moveRequest = new MoveRequest(82,
                new Board(7, 7, Arrays.asList(
                        new Coordinate(6,5),
                        new Coordinate(5,6), new Coordinate(1,4)),
                        Arrays.asList(
                                new Snake("gs_vMJ6HHQG7Q4rFXBvqDFPkKcT", "Jawa dev - oleg", 99,
                                        Arrays.asList(new Coordinate(3,1), new Coordinate(2,1), new Coordinate(1,1), new Coordinate(1,0), new Coordinate(2,0), new Coordinate(3,0), new Coordinate(4,0), new Coordinate(5,0), new Coordinate(5,1), new Coordinate(4,1), new Coordinate(4,2), new Coordinate(5,2), new Coordinate(5,3), new Coordinate(4,3), new Coordinate(3,3), new Coordinate(2,3)),
                                        "167", new Coordinate(3,1), 16))),
                new Snake("gs_vMJ6HHQG7Q4rFXBvqDFPkKcT", "Jawa dev - oleg", 99,
                        Arrays.asList(new Coordinate(3,1), new Coordinate(2,1), new Coordinate(1,1), new Coordinate(1,0), new Coordinate(2,0), new Coordinate(3,0), new Coordinate(4,0), new Coordinate(5,0), new Coordinate(5,1), new Coordinate(4,1), new Coordinate(4,2), new Coordinate(5,2), new Coordinate(5,3), new Coordinate(4,3), new Coordinate(3,3), new Coordinate(2,3)),
                        "167", new Coordinate(3,1), 16));
        Map<Direction, Integer> directionIntegerMap = new MainResource().assignWeights(moveRequest);
        assertEquals(-1, (int) directionIntegerMap.get(Direction.LEFT));
        assertEquals(-1, (int) directionIntegerMap.get(Direction.RIGHT));
        assertEquals(-1, (int) directionIntegerMap.get(Direction.DOWN));
        assertTrue((int) directionIntegerMap.get(Direction.UP) > 0);
    }
}
