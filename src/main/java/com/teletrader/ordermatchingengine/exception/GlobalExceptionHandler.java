package com.teletrader.ordermatchingengine.exception;

import com.teletrader.ordermatchingengine.controller.v1.payload.response.ErrorResponse;
import com.teletrader.ordermatchingengine.controller.v1.payload.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<ErrorResponse> errorInfos = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError fieldError) {
                errorInfos.add(new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()));
            } else {
                errorInfos.add(new ErrorResponse(error.getObjectName(), error.getDefaultMessage()));
            }
        });

        Response<Void> body = Response.<Void>builder()
                .success(false)
                .errors(errorInfos)
                .data(null)
                .build();

        return new ResponseEntity<>(body, headers, status);
    }
}
