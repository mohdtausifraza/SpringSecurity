package com.example.springsecurity.controller;

import com.example.springsecurity.model.AccountTransactions;
import com.example.springsecurity.model.Customer;
import com.example.springsecurity.repository.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @PostMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestBody Customer customer) {
        return accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(customer.getId());
    }
}
