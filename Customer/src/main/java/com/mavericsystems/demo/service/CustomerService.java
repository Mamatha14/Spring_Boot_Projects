package com.mavericsystems.demo.service;

import com.mavericsystems.demo.controller.CustomerController;
import com.mavericsystems.demo.exceptions.CustomerAlreadyExistsException;
import com.mavericsystems.demo.exceptions.CustomerNotFoundException;
import com.mavericsystems.demo.feign.AccountFeign;
import com.mavericsystems.demo.model.Account;
import com.mavericsystems.demo.model.Customer;
import com.mavericsystems.demo.repository.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//Provides the services for creating new customer details and obtaining the details

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    AccountFeign accountFeign;

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public List<Customer> getCustomerDetails(){
        logger.info("Getting all customer information...");
        return customerRepo.findAll();
    }

    public void createNewCustomer(Customer customer) {
        logger.info("Adding the new customer...");
        Optional<Customer> customerPresent = customerRepo.findById(customer.getCustomerID());
        if(customerPresent.isPresent())
            throw new CustomerAlreadyExistsException("Customer already exist with same ID!!!");
        customerRepo.save(customer);
    }

    public Optional<Customer> getCustomerByID(Integer id){
        logger.info("Obtaining the customer details based on customer ID...");
        Optional<Customer> customer = customerRepo.findById(id);
        if(!customer.isPresent()) {
            logger.info("Customer not present, check customer ID...");
            throw new CustomerNotFoundException("Check the customer ID!!!");
        }
        return customerRepo.findById(id);
    }

    public Customer updateCustomerData(Integer id, Customer c){
        logger.info("Updating customer details based on customer ID...");
        Optional<Customer> reqdCustomer = customerRepo.findById(id); //checks if the data present or not n if not then returns null/false
        if(!reqdCustomer.isPresent()) {
            logger.info("Customer not present, check customer ID...");
            throw new CustomerNotFoundException("Check the customer ID for update operation!!!");
        }
        Customer customer = reqdCustomer.get();        //.orElseThrow(() -> new ResourceNot FoundEception("cust not found::"+id));
        customer.setMobileNo(c.getMobileNo());
        customer.setMailID(c.getMailID());
        customer.setAddress(c.getAddress());

        Customer customerUpdated = customerRepo.save(customer);
        return customerUpdated;
    }

    public Customer deleteCustomerData(Integer id){
        logger.info("Soft deletion based on customer ID...");
        Customer customer = getCustomerByID(id).get();
        if(customer==null){
            logger.info("Customer not present, check customer ID...");
            throw new CustomerNotFoundException("Check the customer ID!!!");
        }
        customer.setActive(false);
        customerRepo.save(customer);
        List<Account> accountLists = (List<Account>) accountFeign.updateAccountStatus(id);
        customer = getCustomerByID(id).get();
        return customer;
    }
}