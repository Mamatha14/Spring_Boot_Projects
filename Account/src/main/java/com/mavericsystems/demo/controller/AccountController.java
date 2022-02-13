package com.mavericsystems.demo.controller;

import com.mavericsystems.demo.model.Account;
import com.mavericsystems.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/getAccountInfo")
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @PostMapping("/createNewAccount")
    public void createAccount(@RequestBody Account account){
        accountService.createAccount(account);
    }
}
