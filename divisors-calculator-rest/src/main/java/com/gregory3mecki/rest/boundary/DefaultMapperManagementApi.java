package com.gregory3mecki.rest.boundary;

import com.gregory3mecki.mapper.MapperInstanceMapper;
import com.gregory3mecki.mapper.MapperService;
import com.gregory3mecki.mapper.data.Mapper;
import com.gregory3mecki.rest.api.boundary.MapperManagementApi;
import com.gregory3mecki.rest.api.entity.MapperDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class DefaultMapperManagementApi implements MapperManagementApi {

    @Inject
    MapperInstanceMapper mapperInstanceMapper;
    @Inject
    MapperService mapperService;

    @Override
    public Response provideMapper(final String name) {
        Mapper mapperModel = mapperService.provideMapper(name);
        MapperDTO dto = mapperInstanceMapper.toDto(mapperModel);
        return Response.ok(dto).build();
    }

    @Override
    public Response createMapper(final MapperDTO mapper) {
        Mapper mapperModel = mapperInstanceMapper.toModel(mapper);
        mapperService.createMapper(mapperModel);
        return Response.ok().build();
    }

    @Override
    public Response removeMapper(final String name) {
        mapperService.deleteMapper(name);
        return Response.ok().build();
    }

}
