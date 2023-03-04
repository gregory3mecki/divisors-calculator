package com.gregory3mecki.rest.api.boundary;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("calculator-api")
public interface CalculatorApi {

    @GET
    @Path("dividers/mappers/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    Response provideMappedDividers(@PathParam("name") String mapperName, @QueryParam("number") List<String> numbers);

}
