package org.example.competition22.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PingResponse {
    @JsonProperty
    String query;
    @JsonProperty
    String reply;

    public PingResponse(String query) {
        this.query = query;
        this.reply = "You've sent " + query;
    }
}
