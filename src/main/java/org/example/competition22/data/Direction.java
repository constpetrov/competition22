package org.example.competition22.data;

public enum Direction {
    UP("up"), DOWN("down"), LEFT("left"), RIGHT("right");

    private final String name;

    Direction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
