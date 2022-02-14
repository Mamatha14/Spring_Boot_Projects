package com.mavericsystems.demo.controller;

import com.mavericsystems.demo.model.Account;
import com.mavericsystems.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("accounts") //top-level mapping
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/getAccountInfo")  //mapping for obtaining the all account details
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @PostMapping("/createNewAccount") //mapping for creating new account
    public void createAccount(@RequestBody String id){

        //For each customer the account is created
        Account acc = new Account();
        acc.setCustomerID(id);
        acc.setDateOfCreation(new Date());
        accountService.createAccount(acc);
    }
}