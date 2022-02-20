package com.mavericsystems.demo.service;

import com.mavericsystems.demo.exceptions.CustomerNotFoundException;
import com.mavericsystems.demo.model.Address;
import com.mavericsystems.demo.model.Customer;
import com.mavericsystems.demo.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//Provides the services for creating new customer details and obtaining the details

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RestTemplate restTemplate;

    //Obtaining all CustomerDetails
    public List<Customer> getCustomerDetails(){

        return customerRepo.findAll();
    }

    //Creating new customer
    public void createNewCustomer(Customer customer) {
        customerRepo.save(customer);
/*
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        customerRepo.save(customer);
        //Obtaining customerID as it is unique id for customer appln and acts as foreign key for accounts appln
        //and corresponding accounts data is set.
        Long customerId = customer.getCustomerID();
        HttpEntity<Long> httpEntity = new HttpEntity<>(customerId, header);
        //RestTemplate to communicate with Account application which is identified by using the URL
      //restTemplate.postForObject("http://account-application/accounts/createNewAccount", httpEntity, Long.class);*/
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
}
