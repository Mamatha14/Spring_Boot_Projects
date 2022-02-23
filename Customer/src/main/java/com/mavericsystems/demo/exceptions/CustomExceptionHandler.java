package com.mavericsystems.demo.exceptions;

import com.mavericsystems.demo.model.APIError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomerNotFoundException.class})
    ResponseEntity customerNotFoundHandler(Exception e, ServletWebRequest request){
        APIError apiError = new APIError();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setErrors(Arrays.asList(e.getMessage()));
        apiError.setMsg("No customer present for the specified ID. Please re-enter the customer ID!!!");
        apiError.setPath(request.getDescription(false));
        apiError.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CustomerAlreadyExistsException.class})
    ResponseEntity customerAlreadyExistsHandler(Exception e, ServletWebRequest request){
        APIError apiError = new APIError();
        apiError.setStatus(HttpStatus.CONFLICT);
        apiError.setErrors(Arrays.asList(e.getMessage()));
        apiError.setMsg("Customer present with the specified ID. Please enter different customer ID!!!");
        apiError.setPath(request.getDescription(false));
        apiError.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errors = fieldErrors.stream()
                .map(err -> err.getField() + " : " + err.getDefaultMessage())
                .collect(Collectors.toList());


        APIError apiError = new APIError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setTimeStamp(LocalDateTime.now());
        apiError.setPath(request.getDescription(false));
        apiError.setErrors(errors);
        apiError.setMsg("Enter the data!!!");

        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }
}