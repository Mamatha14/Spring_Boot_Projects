package com.mavericsystems.demo.repository;

import com.mavericsystems.demo.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository <Account, Long> {
}
