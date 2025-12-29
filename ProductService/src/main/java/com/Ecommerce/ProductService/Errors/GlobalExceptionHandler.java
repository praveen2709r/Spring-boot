package com.Ecommerce.ProductService.Errors;

import com.Ecommerce.ProductService.ExceptionHandling.ProductNotFoundException;
import com.Ecommerce.ProductService.ExceptionHandling.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<APIError> handlerProductNotFound(ProductNotFoundException exception){
        APIError error=new APIError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(error.getStatusCode()).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->
                errors.put(error.getField(),error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> handlerUserExistsException(UserAlreadyExistException userAlreadyExistException){
        return ResponseEntity.ok(userAlreadyExistException.getMessage());
    }
}
