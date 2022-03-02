package com.mavericsystems.demo.service;

import com.mavericsystems.demo.controller.AccountController;
import com.mavericsystems.demo.exceptions.AccountsNotFoundException;
import com.mavericsystems.demo.model.Account;
import com.mavericsystems.demo.repository.AccountRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Provides the services for creating new account for a particular customer and obtaining the all accounts details

@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    public List<Account> getAccounts(){
        logger.info("Getting all account information...");
        return (List<Account>) accountRepo.findAll();
    }

    public Account getAccountByID(Long id){
        logger.info("Getting account information based on account no...");
        Optional<Account> account = accountRepo.findById(id);
        if(!account.isPresent())
            throw new AccountsNotFoundException("Check the account number!!!");
        return accountRepo.findById(id).get();
//        if(account==null)
//            throw new AccountsNotFoundException("Check the account number!!!");
//        return account;
        //return accountRepo.findById(id).get();
    }

    public List<Account> getAccountByCustomerId(Integer id){
        logger.info("Getting account information based on customer ID...");
        return  (List<Account>) accountRepo.findByCustomerID(id);
    }

    public Account createAccount(Account account){
        logger.info("Adding account for a particular customer...");
        return accountRepo.save(account);
    }

    public List<Account> updateAccountStatus(Integer id){
        List<Account> accounts = getAccountByCustomerId(id);
        for(Account account : accounts){
            account.setActive(false);
            accountRepo.save(account);
        }
        List<Account> accountsUpdated = getAccountByCustomerId(id);
        return accountsUpdated;
    }
}