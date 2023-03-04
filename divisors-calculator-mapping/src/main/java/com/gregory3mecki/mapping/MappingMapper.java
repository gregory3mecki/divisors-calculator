package com.gregory3mecki.mapping;

import com.gregory3mecki.mapping.data.Mapping;
import com.gregory3mecki.rest.api.entity.MappingDTO;

@org.mapstruct.Mapper(componentModel = "cdi")
public interface MappingMapper {

    Mapping toModel(MappingDTO dto);

    MappingDTO toDto(Mapping model);

}
