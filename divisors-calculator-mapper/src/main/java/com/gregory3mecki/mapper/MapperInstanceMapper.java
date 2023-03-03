package com.gregory3mecki.mapper;

import com.gregory3mecki.mapper.data.Mapper;
import com.gregory3mecki.rest.api.entity.MapperDTO;

@org.mapstruct.Mapper(componentModel = "cdi")
public interface MapperInstanceMapper {

    Mapper toModel(MapperDTO dto);

    MapperDTO toDto(Mapper model);

}
