package com.mycompany.service;

import com.mycompany.model.FullCustomer;
import com.mycompany.repository.Account;
import com.mycompany.repository.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FullCustomerService {


    private final CustomerService customerService;
    private final AccountService accountService;

    public FullCustomerService(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    /*public List<FullCustomer> getAllFullCustomer(){

    }*/

    public FullCustomer getFullCustomerById(String id){
        Customer customer = customerService.findCustomerById(id);
        List<Account> accounts = accountService.findAllAccount(customer);

        FullCustomer fullCustomer= new FullCustomer();
        fullCustomer.setCustomer(customer);
        fullCustomer.setAccount(accounts);

        return fullCustomer;
    }

}
