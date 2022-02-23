package com.mavericsystems.demo.controller;

import com.mavericsystems.demo.enums.AccountType;
import com.mavericsystems.demo.model.Account;
import com.mavericsystems.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("accounts") //top-level mapping
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/getAccountInfo")  //mapping for obtaining the all account details
    public ResponseEntity<List<Account>> getAccounts(){
        return new ResponseEntity<List<Account>>(accountService.getAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountByID(@PathVariable("id")Long id){
        log.info("Obtaining account by ID: {}", id);
        return new ResponseEntity<Account>(accountService.getAccountByID(id),HttpStatus.OK);
    }

    @GetMapping("/customerId/{id}")
    public ResponseEntity<List<Account>> getAccountByCustomerId(@PathVariable("id")Integer id){
        log.info("Obtaining account by ID: {}", id);
        return new ResponseEntity<List<Account>>(accountService.getAccountByCustomerId(id),HttpStatus.OK);
    }

    @PostMapping("/createNewAccount") //mapping for creating new account
    public ResponseEntity<Account> createAccount(@RequestBody Integer id){
        Account account = new Account();
        account.setCustomerID(id);
        account.setDateOfCreation(new Date());
        account.setIfscCode("abs00001212");
        account.setBalance(500.00);
        account.setAccountType(AccountType.CASH);
        account.setActive(true);
        return new ResponseEntity<Account>(accountService.createAccount(account), HttpStatus.OK);
    }

    @DeleteMapping("/updateStatus/{id}")
    public ResponseEntity<List<Account>> updateAccountStatus(@PathVariable("id") Integer id){
        return new ResponseEntity<List<Account>>(accountService.updateAccountStatus(id), HttpStatus.OK);
    }

//    @PutMapping("/updateBalance/{id}")
//    public ResponseEntity<Account> updateBalance(@PathVariable("id") Long id, @RequestBody Double money){
//        return new ResponseEntity<Account>(accountService.addMoney(id, money), HttpStatus.OK);
//    }
}