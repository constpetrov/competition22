package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RulesetSettings {

    @JsonProperty
    public final int foodSpawnChance;

    @JsonProperty
    public final int minimumFood;

    @JsonProperty
    public final int hazardDamagePerTurn;

    @JsonProperty
    public final String map;

    @JsonProperty
    public final RulesetSettingsRoyale royale;

    @JsonProperty
    public final RulesetSettingsSquad squad;

    @JsonCreator
    public RulesetSettings(@JsonProperty("foodSpawnChance") int foodSpawnChance,
                           @JsonProperty("minimumFood") int minimumFood,
                           @JsonProperty("hazardDamagePerTurn") int hazardDamagePerTurn,
                           @JsonProperty("map") String map,
                           @JsonProperty("royale") RulesetSettingsRoyale royale,
                           @JsonProperty("squad") RulesetSettingsSquad squad) {
        this.foodSpawnChance = foodSpawnChance;
        this.minimumFood = minimumFood;
        this.hazardDamagePerTurn = hazardDamagePerTurn;
        this.map = map;
        this.royale = royale;
        this.squad = squad;
    }

    @Override
    public String toString() {
        return "RulesetSettings{" +
                "foodSpawnChance=" + foodSpawnChance +
                ", minimumFood=" + minimumFood +
                ", hazardDamagePerTurn=" + hazardDamagePerTurn +
                ", map='" + map + '\'' +
                ", royale=" + royale +
                ", squad=" + squad +
                '}';
    }
}
