package com.gregory3mecki.rest.api.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("mapping-api")
public interface MappingApi {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String getMappings();

}
