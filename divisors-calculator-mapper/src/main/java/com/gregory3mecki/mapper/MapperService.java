package com.gregory3mecki.mapper;

import com.gregory3mecki.mapper.data.Mapper;

public interface MapperService {

    Mapper provideMapper(String name);

    void createMapper(Mapper mapper);

    void deleteMapper(String name);

}
