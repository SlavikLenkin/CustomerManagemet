package com.mycompany.service;

import com.mycompany.model.AccountDto;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

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
        List<AccountDto> accountsDto = new ArrayList<>();
        AccountDto accountDto = new AccountDto();
        accountDto.setName("account");
        accountDto.setCustomer(customer);

        accountsDto.add(accountDto);


        List<AccountDto> accountsDtoTest = accountService.save(accountsDto, customer);

        for (AccountDto accountI : accountsDtoTest) {
            Assert.assertNotNull(accountI.getId());
            Assert.assertNotNull(accountI.getHref());
            Assert.assertNotNull(accountI.getName());
            Assert.assertNull(accountI.getDescription());
            Assert.assertEquals("account", accountI.getName());
        }
    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<AccountDto> accountsDto = new ArrayList<>();
        AccountDto accountDto = new AccountDto();
        accountDto.setId("id");
        accountDto.setHref("href");
        accountDto.setName("account");
        accountDto.setCustomer(customer);
        accountsDto.add(accountDto);

        List<AccountDto> accountsDtoUpdate = new ArrayList<>();
        AccountDto accountDtoUpdate = new AccountDto();
        accountDtoUpdate.setName("new account");
        accountsDtoUpdate.add(accountDtoUpdate);
        accountsDto.get(0).setName(accountsDtoUpdate.get(0).getName());

        List<AccountDto> accounts2 = accountService.update(accountsDto);

        for (AccountDto accountI : accounts2) {
            Assert.assertNotNull(accountI.getId());
            Assert.assertNotNull(accountI.getHref());
            Assert.assertNotNull(accountI.getName());
            Assert.assertNull(accountI.getDescription());
            Assert.assertEquals("new account", accountI.getName());
        }
    }
}
