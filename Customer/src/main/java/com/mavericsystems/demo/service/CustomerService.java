package com.mavericsystems.demo.service;

import com.mavericsystems.demo.model.Customer;
import com.mavericsystems.demo.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

//    @Autowired
  //  RestTemplate restTemplate;

    public List<Customer> getCustomerDetails(){
        return (List<Customer>) customerRepo.findAll();
    }

    public void createNewCustomer(Customer customer){
      /*
        String customerId = customer.getCustomerID();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(customerId,header);
        Long accNo = restTemplate.postForObject("http://accounts/createNewAccount",
                httpEntity,Long.class);
        customerRepo.save(new Customer(customer.getCustomerID(), customer.getCustomerName(),
                customer.getMobileNo(), customer.getMailID(),customer.getDob()));

       */
        customerRepo.save(customer);
    }
}
