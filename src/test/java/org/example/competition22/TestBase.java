package org.example.competition22;

import org.example.competition22.data.Board;
import org.example.competition22.data.Coordinate;
import org.example.competition22.data.Direction;
import org.example.competition22.data.Game;
import org.example.competition22.data.Ruleset;
import org.example.competition22.data.RulesetName;
import org.example.competition22.data.Snake;

import java.util.Arrays;
import java.util.Collections;

public class TestBase {
    public Game getStandardGame() {
        return new Game(new Ruleset(RulesetName.STANDARD, "1.0.0", null));
    }

    public Game getWrappedGame() {
        return new Game(new Ruleset(RulesetName.WRAPPED, "1.0.0", null));
    }

    public Board getEmptyBoard(int height, int width) {
        return new Board(height, width, Collections.emptyList(), Collections.emptyList());
    }

    public Snake getSimpleSnake(int x, int y) {
        var head = new Coordinate(x, y);
        return new Snake("", "", 100, Arrays.asList(head, new Coordinate(x + 1, y), new Coordinate(x + 2, y)), "", head, 3);
    }
}
