package com.divyanshCode.BlogApplication.Exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    /// handling exception for resource not found.
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> handleException(ResourceNotFound e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }




    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(
            BadCredentialsException ex)
    {
        return new ResponseEntity<>("Invalid Email or Password", HttpStatus.UNAUTHORIZED );
    }



    /// handling exception for validation.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException e )
    {
        Map<String, String> error = new HashMap<>();
        e.getBindingResult()
                .getFieldErrors()              // directly gives List<FieldError>
                .forEach(err -> {
                    error.put(err.getField(),
                            err.getDefaultMessage());
                });
        return new ResponseEntity<>( error, HttpStatus.BAD_REQUEST);
    }

}
