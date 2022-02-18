package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RulesetName {

    @JsonProperty("standard")
    STANDARD,

    @JsonProperty("solo")
    SOLO,

    @JsonProperty("royale")
    ROYALE,

    @JsonProperty("squad")
    SQUAD,

    @JsonProperty("constrictor")
    CONSTRICTOR,

    @JsonProperty("wrapped")
    WRAPPED;
}
