package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Snake {
    @JsonProperty
    public final String id;
    @JsonProperty
    public final String name;
    @JsonProperty
    public final int health;
    @JsonProperty
    public final List<Coordinate> body;
    @JsonProperty
    public final String latency;
    @JsonProperty
    public final Coordinate head;
    @JsonProperty
    public final int length;
//    @JsonProperty public final String shout;
//    @JsonProperty public final String squad;
//    @JsonProperty public final Customizations customizations;


    @JsonCreator
    public Snake(@JsonProperty("id") String id,
                 @JsonProperty("name") String name,
                 @JsonProperty("health") int health,
                 @JsonProperty("body") List<Coordinate> body,
                 @JsonProperty("latency") String latency,
                 @JsonProperty("head") Coordinate head,
                 @JsonProperty("length") int length) {
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

