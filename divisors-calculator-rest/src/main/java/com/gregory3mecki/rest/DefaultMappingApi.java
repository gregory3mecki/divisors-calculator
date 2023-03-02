package com.gregory3mecki.rest;

import com.gregory3mecki.rest.api.boundary.MappingApi;

public class DefaultMappingApi implements MappingApi {

    public String getMappings() {
        return "Hello moto!";
    }

}
