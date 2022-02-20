package com.mavericsystems.demo.config;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

import java.util.Date;

public class CustomErrorDecoder implements ErrorDecoder {
    private final  ErrorDecoder defaultErrorDecoder = new Default();


    @Override
    public Exception decode(String s, Response response) {
        Exception exception = defaultErrorDecoder.decode(s, response);

        if(exception instanceof RetryableException){
            return  exception;
        }

        if(response.status() == 500){
            System.out.println("Retrying in ....");
            return new RetryableException(response.status(), "503 error",  response.request().httpMethod(), new Date(),response.request());
        }

        return exception;

    }
}
