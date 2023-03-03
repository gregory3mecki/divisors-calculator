package com.gregory3mecki.rest.api.entity;

import lombok.Data;

@Data
public class ResponseDTO {

    private Object data;
    private ErrorDTO error;

}
