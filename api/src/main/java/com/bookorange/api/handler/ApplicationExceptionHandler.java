package com.bookorange.api.handler;

import com.bookorange.api.handler.exception.BadRequestException;
import com.bookorange.api.handler.exception.ForbiddenException;
import com.bookorange.api.handler.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorResponse> handlerBadRequestException(Exception e){
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(e.getMessage())
                .status(400)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<ErrorResponse> handlerForbiddenException(Exception e){
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(e.getMessage())
                .status(403)
                .build(), HttpStatus.FORBIDDEN);
    }

}
