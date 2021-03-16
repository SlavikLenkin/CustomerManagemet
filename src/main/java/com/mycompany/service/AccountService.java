package com.mycompany.service;

import com.mycompany.repository.Account;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    final
    AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }


   /* public List<Account> findAllAccounts(Customer customer) {
        List<Account> accounts = new ArrayList<>();
        if (customer.getAccountId() == null)
            return accounts;
        accounts = repository.findAccountById(customer.getAccountId());
        return accounts;
    }*/


    public List<Account> save(List<Account> accounts) {
        if (accounts == null) {
            return null;
        }
        for (Account account : accounts) {
            String id = UUID.randomUUID().toString();
            account.setId(id);
            account.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
            repository.save(account);
        }
        return accounts;
    }

    public List<Account> update(List<Account> accounts){
        for (Account account :accounts){
            repository.save(account);
        }
        return accounts;
    }

    public void delete(Account account) {
        repository.delete(account);
    }


}
