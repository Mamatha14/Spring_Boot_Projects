package com.mavericsystems.demo.exceptions;

public class AccountsNotFoundException extends RuntimeException{
    public AccountsNotFoundException(String msg){
        super(msg);
    }
}
