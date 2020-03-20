
package com.product.api.controller;


import com.product.api.exceptions.ProductNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.seyi.api.model.ApiError;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> creditTransferErrHandler(ProductNotFoundException e){
        return new ResponseEntity<>(buildApiError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
            HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ApiError> serverError(InternalServerError e){
        return new ResponseEntity<>(buildApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ApiError> serverError(IllegalAccessException e){
        return new ResponseEntity<>(buildApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
            HttpStatus.BAD_REQUEST);
    }

    private ApiError buildApiError(Integer code, String message){
        ApiError apiError = new ApiError();
        apiError.setCode(code);
        apiError.setMessage(message);
        return apiError;
    }

}