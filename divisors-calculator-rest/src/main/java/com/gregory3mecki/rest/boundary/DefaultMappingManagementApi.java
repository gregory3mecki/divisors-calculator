package com.gregory3mecki.rest.boundary;

import com.gregory3mecki.core.util.rest.RestExceptionHandler;
import com.gregory3mecki.mapping.MappingMapper;
import com.gregory3mecki.mapping.MappingService;
import com.gregory3mecki.mapping.data.Mapping;
import com.gregory3mecki.rest.api.boundary.MappingManagementApi;
import com.gregory3mecki.rest.api.entity.MappingDTO;
import com.gregory3mecki.rest.api.entity.ResponseDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class DefaultMappingManagementApi implements MappingManagementApi {

    @Inject
    MappingMapper mappingMapper;
    @Inject
    MappingService mappingService;

    @RestExceptionHandler
    @Override
    public Response provideMapping(final String name) {
        final Mapping mapping = mappingService.provideMapping(name);
        final MappingDTO dto = mappingMapper.toDto(mapping);
        final ResponseDTO response = new ResponseDTO();
        response.setData(dto);
        return Response
                .ok(response)
                .build();
    }

    @RestExceptionHandler
    @Override
    public Response createMapping(final MappingDTO dto) {
        final Mapping mapping = mappingMapper.toModel(dto);
        mappingService.createMapping(mapping);
        final ResponseDTO response = new ResponseDTO();
        response.setData(dto);
        return Response
                .status(201)
                .entity(response)
                .build();
    }

    @RestExceptionHandler
    @Override
    public Response removeMapping(final String name) {
        mappingService.deleteMapping(name);
        return Response
                .status(204)
                .build();
    }

}
