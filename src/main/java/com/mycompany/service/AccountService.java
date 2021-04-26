package com.mycompany.service;

import com.mycompany.model.AccountDto;
import com.mycompany.repository.Account;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.Customer;
import com.mycompany.transfomer.AccountTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AccountService {

    final AccountRepository repository;
    final AccountTransformer accountTransformer;

    public AccountService(AccountRepository repository, AccountTransformer accountTransformer) {
        this.repository = repository;
        this.accountTransformer = accountTransformer;
    }

    public List<AccountDto> save(List<AccountDto> accountsDto, Customer customer) {
        log.debug("save");
        if (accountsDto == null) {
            return null;
        }
        int i = 0;
        for (AccountDto accountDto : accountsDto) {
            if (accountDto.getName() == null) {
                accountDto.setName(Optional.empty());
            }
            if (accountDto.getDescription() == null) {
                accountDto.setDescription(Optional.empty());
            }
            Account account = accountTransformer.transform(accountDto);
            account.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            account.setId(id);
            account.setHref("https://host:port/tmf-api/customerManagement/v4/account/" + id);
            repository.save(account);
            accountsDto.set(i, accountTransformer.transform(account));
            i++;
        }
        return accountsDto;
    }

    public List<AccountDto> update(List<AccountDto> accountsDto) {
        log.debug("update");
        for (AccountDto accountDto : accountsDto) {
            repository.save(accountTransformer.transform(accountDto));
        }
        return accountsDto;
    }

    public void delete(AccountDto accountDto) {
        log.debug("delete");
        repository.delete(accountTransformer.transform(accountDto));
    }
}
