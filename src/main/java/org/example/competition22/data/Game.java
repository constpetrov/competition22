package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

    @JsonProperty
    public final Ruleset ruleset;

    @JsonCreator
    public Game(@JsonProperty("ruleset") Ruleset ruleset) {
        this.ruleset = ruleset;
    }

    @Override
    public String toString() {
        return "Game{" +
                "ruleset=" + ruleset +
                '}';
    }
}
