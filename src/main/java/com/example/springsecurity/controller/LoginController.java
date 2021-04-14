package com.example.springsecurity.controller;

import com.example.springsecurity.model.Customer;
import com.example.springsecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Principal user){
        List<Customer> customers = customerRepository.findByEmail(user.getName());
        if (customers.size() > 0){
            return customers.get(0);
        }else {
            return null;
        }
    }
}
