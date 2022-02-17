package org.example.competition22;

import org.example.competition22.data.Direction;
import org.example.competition22.data.EndRequest;
import org.example.competition22.data.MoveRequest;
import org.example.competition22.data.MoveResponse;
import org.example.competition22.data.RootResponse;
import org.example.competition22.data.StartRequest;
import org.example.competition22.logic.BodyChecker;
import org.example.competition22.logic.FoodSearch;
import org.example.competition22.logic.SnakesChecker;
import org.example.competition22.logic.WallsChecker;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/")
public class MainResource {
    private final RootResponse rootResponse = new RootResponse();

    @GET
    @Path("/info")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return String.join(" ",
                System.getProperty("os.name"), System.getProperty("os.version"), System.getProperty("os.arch"),
                System.getProperty("java.vendor"), System.getProperty("java.version"), "\n");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RootResponse root() {
        return rootResponse;
    }

    @POST
    @Path("/start")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String startGame(StartRequest startRequest) {
        return startRequest.you.name;
    }

    @POST
    @Path("/end")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String endGame(EndRequest endRequest) {
        return endRequest.you.name;
    }

    @POST
    @Path("/move")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MoveResponse move(MoveRequest moveRequest) {
        Map<Direction, Integer> result = assignWeights(moveRequest);

        return new MoveResponse(pickBest(result), moveRequest.you.toString());
    }

    private Direction pickBest(Map<Direction, Integer> result) {
        int bestWeight = 0;
        for (Map.Entry<Direction, Integer> entry : result.entrySet()) {
            if (entry.getValue() > bestWeight) {
                bestWeight = entry.getValue();
            }
        }
        List<Direction> bestDirections = new ArrayList<>();
        for (Map.Entry<Direction, Integer> entry : result.entrySet()) {
            if (entry.getValue() == bestWeight) {
                bestDirections.add(entry.getKey());
            }
        }
        return bestDirections.get((int)(Math.random()*bestDirections.size()));
    }

    private Map<Direction, Integer> assignWeights(MoveRequest moveRequest) {
        Map<Direction, Integer> result = new HashMap<>();
        for (Direction direction : Direction.values()) {
            result.put(direction, 10);
        }
        FoodSearch.searchFood(moveRequest, result);
        BodyChecker.check(moveRequest, result);
        SnakesChecker.check(moveRequest, result);
        WallsChecker.check(moveRequest, result);
        return result;
    }

}
