package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RulesetSettingsRoyale {

    @JsonProperty
    public final int shrinkEveryNTurns;

    @JsonCreator
    public RulesetSettingsRoyale(@JsonProperty("shrinkEveryNTurns") int shrinkEveryNTurns) {
        this.shrinkEveryNTurns = shrinkEveryNTurns;
    }

    @Override
    public String toString() {
        return "RulesetSettingsRoyale{" +
                "shrinkEveryNTurns=" + shrinkEveryNTurns +
                '}';
    }
}
