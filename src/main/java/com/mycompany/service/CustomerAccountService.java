package com.mycompany.service;

import com.mycompany.repository.CustomerAccount;
import com.mycompany.repository.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAccountService {

    final
    CustomerAccountRepository repository;

    public CustomerAccountService(CustomerAccountRepository repository) {
        this.repository = repository;
    }

    public List<CustomerAccount> save(List<CustomerAccount> customerAccounts){
       /* for (CustomerAccount customerAccount : customerAccounts){
            repository.save(customerAccount);*/
            repository.saveAll(customerAccounts);
       /* }*/
        return customerAccounts;
    }
}
