package com.mavericsystems.demo.controller;

import com.mavericsystems.demo.feign.AccountFeign;
import com.mavericsystems.demo.model.Account;
import com.mavericsystems.demo.model.Customer;
import com.mavericsystems.demo.model.CustomerAccountResponse;
import com.mavericsystems.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customers")  // top-level mapping
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountFeign accountFeign;

    @GetMapping("/") //get mapping for obtaining the customer details
    public ResponseEntity<List<Customer>> getCustomerDetails(){
        return new ResponseEntity<List<Customer>>(customerService.getCustomerDetails(), HttpStatus.OK);
    }

    @PostMapping("/newCustomer") //post mapping for creating customer details
    public void createNewCustomer(@Valid @RequestBody Customer customer){

        accountFeign.createAccount(customer.getCustomerID());
        customerService.createNewCustomer(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable("id") Integer id){
        return  new ResponseEntity<Customer>(customerService.getCustomerByID(id).get(),HttpStatus.OK);
    }

    @GetMapping("/customerAccounts/{id}")
    public ResponseEntity<CustomerAccountResponse> getDataByCommonID(@PathVariable("id") Integer id){
        CustomerAccountResponse caResponse = new CustomerAccountResponse();

        Optional<Customer> customer = customerService.getCustomerByID(id);
        caResponse.setCustomer(customer.get());

        List<Account> account = accountFeign.getAccountByCustomerId(id);
        caResponse.setAccount(account);

        return  new ResponseEntity<CustomerAccountResponse>(caResponse,HttpStatus.OK);
    }

    @PutMapping("/customerInfo/{id}")
    public ResponseEntity<Customer> updateCustomerData(@PathVariable("id") Integer id, @Valid @RequestBody Customer c){
        return new ResponseEntity<Customer>(customerService.updateCustomerData(id,c),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomerData(@PathVariable("id") Integer id){
        return new ResponseEntity<Customer>(customerService.deleteCustomerData(id), HttpStatus.OK);
    }
}