package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
public class AccountController {
    @GetMapping("/myAccount")
    public String getAccountDetails(String input) {
        return "Here are the account details from the DB";
    }
}
