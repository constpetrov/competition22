package org.example.competition22;


import org.example.competition22.data.*;
import org.junit.Test;
import org.junit.runner.RunWith;

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

        assertEquals(0, (int) directionIntegerMap.get(Direction.UP));
    }


}
