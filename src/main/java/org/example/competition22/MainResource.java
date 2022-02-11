package org.example.competition22;

import org.example.competition22.data.PingResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api")
public class MainResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return String.join(" ",
                System.getProperty("os.name"), System.getProperty("os.version"), System.getProperty("os.arch"),
                System.getProperty("java.vendor"), System.getProperty("java.version"), "\n");
    }

    @GET
    @Path("/ping/{item}")
    @Produces(MediaType.APPLICATION_JSON)
    public PingResponse ping(@PathParam("item") String item) {
        return new PingResponse(item);
    }
}
