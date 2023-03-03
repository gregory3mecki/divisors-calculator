package com.gregory3mecki.mapper.data;

import lombok.Data;

import java.util.Map;

@Data
public class Mapper {

    private String identifier;
    private String name;
    private Map<Integer, String> mapping;

}
