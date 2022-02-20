package com.mavericsystems.demo.feign;

import com.mavericsystems.demo.config.CustomerRetryConfig;
import com.mavericsystems.demo.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="account-application", configuration = CustomerRetryConfig.class,fallbackFactory = HystrixFallBackFactory.class)
public interface AccountFeign{
//    @GetMapping(value = "/accounts/id/{id}")
//    Account getAccountByID(@PathVariable("id")Long id);

    @GetMapping("/accounts/customer_id/{id}")
    List<Account> getAccountByCustomerId(@PathVariable("id")Integer id);

    @PostMapping("/accounts/createNewAccount")
    Account createAccount(@RequestBody Integer id);

//    //inlcude putmapping for adding money to particular account and this method has to be present in  account appln.
//    @PutMapping("/accounts/updateBalance/{id}")
//    Account updateBalance(@PathVariable("id") Integer id, @RequestBody Double money);

}