package com.example.springsecurity.config;

import com.example.springsecurity.model.Customer;
import com.example.springsecurity.model.SecurityCustomer;
import com.example.springsecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customer = customerRepository.findByEmail(username);
        if (customer.size() == 0){
            throw new UsernameNotFoundException("User Details Not found for the user : "+ username);
        }
        return new SecurityCustomer(customer.get(0));
    }
}
