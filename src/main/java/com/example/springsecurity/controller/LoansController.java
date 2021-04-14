package com.example.springsecurity.controller;

import com.example.springsecurity.model.Customer;
import com.example.springsecurity.model.Loans;
import com.example.springsecurity.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoansRepository loansRepository;

    @PostMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestBody Customer customer) {
        return loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
    }
}
