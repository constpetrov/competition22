package org.example.competition22;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Main {
    public static void main(String[] args) throws Exception {
        URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(8080).build();
        ResourceConfig config = new ResourceConfig(MainResource.class, JsonMapperProvider.class, JacksonFeature.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config, false);
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
        server.start();
        Thread.currentThread().join();
    }
}
