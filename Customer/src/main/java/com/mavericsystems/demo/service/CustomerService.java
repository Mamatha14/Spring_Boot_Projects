package com.mavericsystems.demo.service;

import com.mavericsystems.demo.model.Customer;
import com.mavericsystems.demo.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

//Provides the services for creating new customer details and obtaining the details

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RestTemplate restTemplate;

    //Obtaining all CustomerDetails
    public List<Customer> getCustomerDetails(){

        return (List<Customer>) customerRepo.findAll();
    }

    //Creating new customer
    public void createNewCustomer(Customer customer) {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        customerRepo.save(customer);

        //Obtaining customerID as it is unique id for customer appln and acts as foreign key for accounts appln
        //and corresponding accounts data is set.
        String customerId = customer.getCustomerID();
        HttpEntity<String> httpEntity = new HttpEntity<>(customerId, header);

        //RestTemplate to communicate with Account application which is identified by using the URL
        restTemplate.postForObject("http://account-application/accounts/createNewAccount", httpEntity, Long.class);
    }
}
