package org.example.competition22.data;

import org.example.competition22.TestBase;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class CoordinateTest extends TestBase {

    @Test
    public void getNextCoordinate() {
        MoveRequest request = new MoveRequest(getStandardGame(), 0, getEmptyBoard(5, 5), getSimpleSnake(2, 2));
        Coordinate coordinate = new Coordinate(1, 2);
        Coordinate nextCoordinate = Coordinate.getNextCoordinate(request, coordinate, Direction.RIGHT);
        assertEquals(new Coordinate(2, 2), nextCoordinate);
    }

    @Test
    public void getNextCoordinateWrapped() {
        MoveRequest request = new MoveRequest(getWrappedGame(), 0, getEmptyBoard(5, 5), getSimpleSnake(2, 2));
        Coordinate coordinate = new Coordinate(4, 2);
        Coordinate nextCoordinate = Coordinate.getNextCoordinate(request, coordinate, Direction.RIGHT);
        assertEquals(new Coordinate(0, 2), nextCoordinate);

        coordinate = new Coordinate(0, 2);
        nextCoordinate = Coordinate.getNextCoordinate(request, coordinate, Direction.LEFT);
        assertEquals(new Coordinate(4, 2), nextCoordinate);
    }
}