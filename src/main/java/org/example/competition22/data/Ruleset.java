package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ruleset {
    @JsonProperty
    public final RulesetName name;

    @JsonProperty
    public final String version;

    @JsonProperty
    public final RulesetSettings settings;

    @JsonCreator
    public Ruleset(@JsonProperty("name") RulesetName name,
                   @JsonProperty("version") String version,
                   @JsonProperty("settings") RulesetSettings settings) {
        this.name = name;
        this.version = version;
        this.settings = settings;
    }
}
