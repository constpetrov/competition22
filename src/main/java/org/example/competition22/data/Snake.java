package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class Snake {
    public final String id;
    public final String name;
    public final int health;
    public final List<Coordinate> body;
    public final String latency;
    public final Coordinate head;
    public final int length;
//    public final String shout;
//    public final String squad;
//    public final Customizations customizations;


    @JsonCreator
    public Snake(String id, String name, int health, List<Coordinate> body, String latency, Coordinate head, int length) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.body = body;
        this.latency = latency;
        this.head = head;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", health=" + health +
                ", body=" + body +
                ", latency='" + latency + '\'' +
                ", head=" + head +
                ", length=" + length +
                '}';
    }
}

