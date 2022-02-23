package com.mavericsystems.demo.service;

import com.mavericsystems.demo.exceptions.CustomerAlreadyExistsException;
import com.mavericsystems.demo.exceptions.CustomerNotFoundException;
import com.mavericsystems.demo.feign.AccountFeign;
import com.mavericsystems.demo.model.Account;
import com.mavericsystems.demo.model.Address;
import com.mavericsystems.demo.model.Customer;
import com.mavericsystems.demo.repository.CustomerRepo;
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

    //Obtaining all CustomerDetails
    public List<Customer> getCustomerDetails(){
        return customerRepo.findAll();
    }

    //Creating new customer
    public void createNewCustomer(Customer customer) {
        Optional<Customer> customerPresent = customerRepo.findById(customer.getCustomerID());
        if(customerPresent.isPresent())
            throw new CustomerAlreadyExistsException("Customer already exist with same ID!!!");
        customerRepo.save(customer);
    }

    public Optional<Customer> getCustomerByID(Integer id){
        Optional<Customer> customer = customerRepo.findById(id);
        if(!customer.isPresent())
            throw new CustomerNotFoundException("Check the customer ID!!!");
        return customerRepo.findById(id);
    }

    //Updating the customer data
    public Customer updateCustomerData(Integer id, Customer c){
        Optional<Customer> reqdCustomer = customerRepo.findById(id); //checks if the data present or not n if not then returns null/false
        if(!reqdCustomer.isPresent())
            throw new CustomerNotFoundException("Check the customer ID for update operation!!!");
        //obtains value present
        Customer customer = reqdCustomer.get();        //.orElseThrow(() -> new ResourceNot FoundEception("cust not found::"+id));
        customer.setMobileNo(c.getMobileNo());
        customer.setMailID(c.getMailID());
        customer.setAddress(c.getAddress());
        Customer customerUpdated = customerRepo.save(customer);
        return customerUpdated;
    }

    public Customer deleteCustomerData(Integer id){
        Optional<Customer> customer = getCustomerByID(id);
        if(!customer.isPresent())
            throw new CustomerNotFoundException("Check the customer ID!!!");
        customer.get().setActive(false);
        customerRepo.save(customer.get());
        List<Account> accountLists = (List<Account>) accountFeign.updateAccountStatus(id);
        customer = getCustomerByID(id);
        return customer.get();
    }
}