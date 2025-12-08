package com.Application.HelloWorld.Errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private HttpStatus statusCode;
    private String message;
}
