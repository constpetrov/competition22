package org.example.competition22;

import org.example.competition22.data.MoveRequest;
import org.example.competition22.data.MoveResponse;
import org.example.competition22.data.PingResponse;
import org.example.competition22.data.RootResponse;
import org.example.competition22.data.StartRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

    @GET
    @Path("/start")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String startGame(StartRequest startRequest) {
        return startRequest.you.name;
    }

    @GET
    @Path("/ping/{item}")
    @Produces(MediaType.APPLICATION_JSON)
    public PingResponse ping(@PathParam("item") String item) {
        return new PingResponse(item);
    }

    @POST
    @Path("/move")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MoveResponse move(MoveRequest moveRequest) {

        return new MoveResponse("up", moveRequest.you.toString());
    }
}
