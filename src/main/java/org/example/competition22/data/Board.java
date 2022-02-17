package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {
    @JsonProperty public final int height;
    @JsonProperty public final int width;
    @JsonProperty public final List<Coordinate> food;
//   public final List<Coordinate> hazards;
    @JsonProperty public final List<Snake> snakes;

    @JsonCreator
    public Board(@JsonProperty("height") int height,
                 @JsonProperty("width") int width,
                 @JsonProperty("food") List<Coordinate> food,
                 @JsonProperty("snakes") List<Snake> snakes) {
        this.height = height;
        this.width = width;
        this.food = food;
        this.snakes = snakes;
    }
}
