package com.Application.HelloWorld.Errors;

import com.Application.HelloWorld.ExceptionHandling.ProductNotFoundException;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class ProductResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Error> getErrorMessage(ProductNotFoundException exception, WebRequest request){
        Error error=new Error(HttpStatus.OK,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
