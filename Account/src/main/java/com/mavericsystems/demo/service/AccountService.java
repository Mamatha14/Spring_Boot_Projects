package com.mavericsystems.demo.service;

import com.mavericsystems.demo.model.Account;
import com.mavericsystems.demo.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Provides the services for creating new account for a particular customer and obtaining the all accounts details

@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;

    //Obtaining all accounts details present
    public List<Account> getAccounts(){

        return (List<Account>) accountRepo.findAll();
    }

    //Creating new account
    public void createAccount(Account account){

        accountRepo.save(account);
    }
}
