package com.mavericsystems.demo.repository;

import com.mavericsystems.demo.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepo extends CrudRepository <Account, Integer> {
    List<Account> findByCustomerID(Integer customerID);
}
