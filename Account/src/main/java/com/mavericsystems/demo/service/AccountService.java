package com.mavericsystems.demo.service;

import com.mavericsystems.demo.model.Account;
import com.mavericsystems.demo.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;

    public List<Account> getAccounts(){
        return (List<Account>) accountRepo.findAll();
    }

    public void createAccount(Account account){
        accountRepo.save(account);
    }
}
