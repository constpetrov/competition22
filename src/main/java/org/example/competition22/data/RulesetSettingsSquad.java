package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RulesetSettingsSquad {
    @JsonProperty
    public final boolean allowBodyCollisions;
    @JsonProperty
    public final boolean sharedElimination;
    @JsonProperty
    public final boolean sharedHealth;
    @JsonProperty
    public final boolean sharedLength;

    public RulesetSettingsSquad(@JsonProperty("allowBodyCollisions") boolean allowBodyCollisions,
                                @JsonProperty("sharedElimination") boolean sharedElimination,
                                @JsonProperty("sharedHealth") boolean sharedHealth,
                                @JsonProperty("sharedLength") boolean sharedLength) {
        this.allowBodyCollisions = allowBodyCollisions;
        this.sharedElimination = sharedElimination;
        this.sharedHealth = sharedHealth;
        this.sharedLength = sharedLength;
    }
}
