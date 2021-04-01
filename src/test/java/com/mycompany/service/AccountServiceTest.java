package com.mycompany.service;

import com.mycompany.repository.Account;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AccountServiceTest {

    @MockBean
    AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @Test
    void save() {
        Customer customer = new Customer();
        List<Account> accounts = new ArrayList<>();
        Account account = new Account();
        account.setName("account");
        account.setCustomer(customer);

        accounts.add(account);

        List<Account> accounts1 = accountService.save(accounts, customer);


        for (Account accountI : accounts1) {
            Assert.assertNotNull(accountI.getId());
            Assert.assertNotNull(accountI.getHref());
            Assert.assertNotNull(accountI.getName());
            Assert.assertNull(accountI.getDescription());
            Assert.assertEquals("account", accountI.getName());
        }


        for (Account accountI : accounts) {
            Mockito.verify(accountRepository, Mockito.times(1)).save(accountI);
        }


    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<Account> accounts = new ArrayList<>();
        Account account = new Account();
        account.setName("account");
        account.setCustomer(customer);

        accounts.add(account);

        List<Account> accounts1 = accountService.save(accounts, customer);


        List<Account> accountsUpdate = new ArrayList<>();
        Account accountUpdate = new Account();
        accountUpdate.setName("new account");


        accountsUpdate.add(accountUpdate);

        accounts1.get(0).setName(accountsUpdate.get(0).getName());

        List<Account> accounts2 = accountService.update(accounts1);


        for (Account accountI : accounts2) {
            Assert.assertNotNull(accountI.getId());
            Assert.assertNotNull(accountI.getHref());
            Assert.assertNotNull(accountI.getName());
            Assert.assertNull(accountI.getDescription());
            Assert.assertEquals("new account", accountI.getName());
            Mockito.verify(accountRepository, Mockito.times(2)).save(accountI);
        }

    }
}