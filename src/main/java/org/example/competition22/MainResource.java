package org.example.competition22;

import org.example.competition22.data.Board;
import org.example.competition22.data.Direction;
import org.example.competition22.data.EndRequest;
import org.example.competition22.data.MoveRequest;
import org.example.competition22.data.MoveResponse;
import org.example.competition22.data.RootResponse;
import org.example.competition22.data.Snake;
import org.example.competition22.data.StartRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
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
        Direction bestDirection = Direction.UP;
        for (Map.Entry<Direction, Integer> entry : result.entrySet()) {
            if (entry.getValue() > bestWeight) {
                bestWeight = entry.getValue();
                bestDirection = entry.getKey();
            }
        }
        return bestDirection;
    }

    private Map<Direction, Integer> assignWeights(MoveRequest moveRequest) {
        Map<Direction, Integer> result = new HashMap<>();
        doNotHitWalls(moveRequest.board, moveRequest.you, result);
        return result;
    }

    private void doNotHitWalls(Board board, Snake you, Map<Direction, Integer> result) {

    }
}
