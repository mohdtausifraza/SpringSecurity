package com.example.springsecurity.controller;

import com.example.springsecurity.model.Accounts;
import com.example.springsecurity.model.Customer;
import com.example.springsecurity.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountsRepository accountsRepository;

    @PostMapping("/myAccount")
    public Accounts getAccountDetails(@RequestBody Customer customer) {
        return accountsRepository.findByCustomerId(customer.getId());
    }
}
