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

  /*  public Account findAccountById(String id) {
        return repository.findAccountById(id);
    }*/

    public List<Account> findAllAccount(Customer customer) {
        List<Account> accounts = new ArrayList<>();
        if (customer.getAccountId() == null)
            return accounts;
        String[] idAccounts = customer.getAccountId()
                .substring(1,customer.getAccountId().length()-1)
                .split(",");
        System.out.println(idAccounts[0]);
        System.out.println(idAccounts[1]);
        accounts = repository.findAccountById(idAccounts);
        /*for (String idAccount : idAccounts) {
            List<String> strings = new ArrayList<>();
            strings.add(idAccount);
          *//*  idAccount = idAccount.
                    replaceAll("[^A-Za-zА-Яа-я0-9]", "");*//*
            Account account = findAccountById(idAccount);
            if (account != null)
                accounts.add(account);
        }*/
        return accounts;
    }


    public List<Account> save(List<Account> accounts) {
        for (Account account: accounts) {
            String id = UUID.randomUUID().toString();
            account.setId(id);
            account.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
            repository.save(account);
        }
        return accounts;
    }

    public void delete(Account account){
        repository.delete(account);
    }


}
