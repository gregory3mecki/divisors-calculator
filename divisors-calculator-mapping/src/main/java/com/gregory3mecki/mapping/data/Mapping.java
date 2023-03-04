package com.gregory3mecki.mapping.data;

import lombok.Data;

import java.util.Map;

@Data
public class Mapping {

    private String identifier;
    private String name;
    private Map<Integer, String> mappings;

}
