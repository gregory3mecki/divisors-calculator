package com.gregory3mecki.rest.api.entity;

import lombok.Data;

import java.util.Map;

@Data
public class MappingDTO {

    private String identifier;
    private String name;
    private Map<Integer, String> mappings;

}
