package com.gregory3mecki.core.util.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StatusCodeException extends RuntimeException {

    private int statusCode;
    private String exceptionMessage;
    private String details;

}
