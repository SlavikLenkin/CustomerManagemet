package com.mycompany.service;

import com.mycompany.repository.Account;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AccountService {

    final
    AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }


    public List<Account> save(List<Account> accounts, Customer customer) {
        log.info("save");
        if (accounts == null) {
            return null;
        }
        for (Account account : accounts) {
            account.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            account.setId(id);
            account.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
            repository.save(account);
        }
        return accounts;
    }

    public List<Account> update(List<Account> accounts) {
        log.info("update");
        for (Account account : accounts) {
            repository.save(account);
        }
        return accounts;
    }

    public void delete(Account account) {
        log.info("delete");
        repository.delete(account);
    }

}
