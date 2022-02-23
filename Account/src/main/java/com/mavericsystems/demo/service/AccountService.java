package com.mavericsystems.demo.service;

import com.mavericsystems.demo.exceptions.AccountsNotFoundException;
import com.mavericsystems.demo.model.Account;
import com.mavericsystems.demo.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Provides the services for creating new account for a particular customer and obtaining the all accounts details

@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;

    //Obtaining all accounts details present
    public List<Account> getAccounts(){
        return (List<Account>) accountRepo.findAll();
    }

    //Obtaining an account by accountID
    public Account getAccountByID(Long id){
       // return  accountRepo.findById(id).get();
     Optional<Account> account = accountRepo.findById(id);
     if(!account.isPresent())
         throw new AccountsNotFoundException("Check the account number!!!");;
     return accountRepo.findById(id).get();
    }

    //Obtaining account by customerID
    public List<Account> getAccountByCustomerId(Integer id){
        return  (List<Account>) accountRepo.findByCustomerID(id);
    }

    //Creating new account
    public Account createAccount(Account account){

        return accountRepo.save(account);
    }

    //Deleting Account
    public List<Account> updateAccountStatus(Integer id){
        List<Account> accounts = getAccountByCustomerId(id);
        for(Account account : accounts){
            account.setActive(false);
            accountRepo.save(account);
        }
        List<Account> accountsUpdated = getAccountByCustomerId(id);
        return accountsUpdated;
    }

//    //Adding money to particular account
//    public  Account addMoney(Long id, Double money){
//        Account account = accountRepo.findById(id).get();
//        Double bal = account.getBalance()+money;
//        account.setBalance(bal);
//        return accountRepo.save(account);
//    }

}
