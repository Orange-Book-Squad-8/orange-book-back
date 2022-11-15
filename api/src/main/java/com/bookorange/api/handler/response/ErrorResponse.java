package com.bookorange.api.handler.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String message;
    private Integer status;
}
