package com.firstproject.demo.expetion;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception){
        return status(HttpStatus.NOT_FOUND).body(new ErrorMessageDetails(404, exception.getLocalizedMessage(), null));
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> handleBindException(BindException exception, HttpServletRequest request){
        var error = exception.getBindingResult().getAllErrors()
                .stream()
                .map(exc->
                        new BeanValidationException(Objects.requireNonNull(exc.getCodes())[1] , exc.getDefaultMessage())
                )
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessageDetails(HttpStatus.BAD_REQUEST.value(), "Binding Error", error));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException exception){
        return status(HttpStatus.BAD_REQUEST).body(new ErrorMessageDetails(HttpStatus.BAD_REQUEST.value(), exception.getLocalizedMessage(), null));
    }

    // Other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllOtherExceptions(Exception exception){
        return status(500).body(new ErrorMessageDetails(500, exception.getMessage(), null));
    }
}
