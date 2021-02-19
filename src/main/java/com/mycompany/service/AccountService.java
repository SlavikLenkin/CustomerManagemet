package com.mycompany.service;

import com.mycompany.repository.Account;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    final
    AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account findAccountById(String id) {
        return repository.findByIdAccount(id);
    }

    public List<Account> findAllAccount(Customer customer) {
        List<Account> accounts = new ArrayList<>();
        if (customer.getAccountId() == null)
            return accounts;
        String[] idAccounts = customer.getAccountId().split(",");
        for (String idAccount : idAccounts) {
            idAccount = idAccount.
                    replaceAll("[^A-Za-zА-Яа-я0-9]", "");
            Account account = findAccountById(idAccount);
            if (account != null)
                accounts.add(account);
        }
        return accounts;
    }


}
