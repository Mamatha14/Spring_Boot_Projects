package com.mavericsystems.demo.feign;

import com.mavericsystems.demo.config.CustomerRetryConfig;
import com.mavericsystems.demo.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="account-application", configuration = CustomerRetryConfig.class, fallbackFactory = HystrixFallBackFactory.class)
public interface AccountFeign{

    @GetMapping("/accounts/customerId/{id}")
    List<Account> getAccountByCustomerId(@PathVariable("id")Integer id);

    @PostMapping("/accounts/newAccount")
    Account createAccount(@RequestBody Integer id);

    @DeleteMapping("/accounts/{id}")
    List<Account> updateAccountStatus(@PathVariable("id")Integer id);
}