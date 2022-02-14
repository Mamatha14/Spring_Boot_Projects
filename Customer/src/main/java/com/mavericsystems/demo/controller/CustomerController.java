package com.mavericsystems.demo.controller;

import com.mavericsystems.demo.model.Customer;
import com.mavericsystems.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")  // top-level mapping
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/getCustomerInfo") //get mapping for obtaining the customer details
    public List<Customer> getCustomerDetails(){

        return customerService.getCustomerDetails();
    }

    @PostMapping("/createNewCustomer") //post mapping for creating customer details
    public void createNewCustomer(@RequestBody Customer customer){
        customerService.createNewCustomer(customer);
    }
}