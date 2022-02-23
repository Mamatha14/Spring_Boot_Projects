package com.mavericsystems.demo.exceptions;

import com.mavericsystems.demo.model.APIError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler({AccountsNotFoundException.class})
        ResponseEntity accountNotFoundHandler(Exception e, ServletWebRequest request){
        APIError apiError = new APIError();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setErrors(Arrays.asList(e.getMessage()));
        apiError.setMsg("No account present for the specified number. Please re-enter the account number!!!");
        apiError.setPath(request.getDescription(false));
        apiError.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }
}
