package com.mycompany.service;

import com.mycompany.model.AccountDto;
import com.mycompany.repository.Account;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.Customer;
import com.mycompany.transfomer.AccountTransformer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountTransformer accountTransformer;

    @InjectMocks
    private AccountService accountService;


    @Test
    void save() {
        AccountDto a = new AccountDto();
        Account account = new Account();
        Customer customer = new Customer();
        List<AccountDto> accountsDto = new ArrayList<>();
        AccountDto accountDto = new AccountDto();
        accountDto.setName("account");
        accountDto.setCustomer(customer);

        account.setName("customer");
        account.setCustomer(customer);
        accountsDto.add(accountDto);

        Mockito.when(accountTransformer.transform(accountDto)).thenReturn(account);
        Mockito.when(accountTransformer.transform(account)).thenReturn(accountDto);
        Mockito.when(accountRepository.save(account)).thenReturn(account);

        accountService.save(accountsDto, customer);
        
        Mockito.verify(accountTransformer).transform(accountDto);
        Mockito.verify(accountTransformer).transform(account);
        Mockito.verify(accountRepository).save(account);

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

