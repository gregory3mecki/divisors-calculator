package com.gregory3mecki.rest.api.boundary;

import com.gregory3mecki.rest.api.entity.MappingDTO;
import com.gregory3mecki.rest.api.entity.ResponseDTO;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("mapping-management/mappings")
public interface MappingManagementApi {

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
            responseCode = "200",
            description = "Get mapping with given name",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ResponseDTO.class)
            )
    )
    Response provideMapping(@PathParam("name") String name);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response createMapping(MappingDTO dto);

    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    Response removeMapping(@PathParam("name") String name);

}
