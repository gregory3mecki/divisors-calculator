package com.gregory3mecki.mapping;

import com.gregory3mecki.mapping.data.Mapping;

public interface MappingService {

    Mapping provideMapping(String name);

    void createMapping(Mapping mapping);

    void deleteMapping(String name);

}
