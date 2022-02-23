package com.mavericsystems.demo.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException(String msg){
        super(msg);
    }
}
