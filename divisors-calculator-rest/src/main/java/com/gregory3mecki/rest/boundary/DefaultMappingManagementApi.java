package com.gregory3mecki.rest.boundary;

import com.gregory3mecki.mapping.MappingMapper;
import com.gregory3mecki.mapping.MappingService;
import com.gregory3mecki.mapping.data.Mapping;
import com.gregory3mecki.rest.api.boundary.MappingManagementApi;
import com.gregory3mecki.rest.api.entity.MappingDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class DefaultMappingManagementApi implements MappingManagementApi {

    @Inject
    MappingMapper mappingMapper;
    @Inject
    MappingService mappingService;

    @Override
    public Response provideMapping(final String name) {
        final Mapping mapping = mappingService.provideMapping(name);
        final MappingDTO dto = mappingMapper.toDto(mapping);
        return Response.ok(dto).build();
    }

    @Override
    public Response createMapping(final MappingDTO dto) {
        final Mapping mapping = mappingMapper.toModel(dto);
        mappingService.createMapping(mapping);
        return Response.ok().build();
    }

    @Override
    public Response removeMapping(final String name) {
        mappingService.deleteMapping(name);
        return Response.ok().build();
    }

}
