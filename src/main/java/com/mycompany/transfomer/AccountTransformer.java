package com.mycompany.transfomer;

import com.mycompany.model.AccountDto;
import com.mycompany.repository.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountTransformer {

    public Account transform(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        Account account = new Account();
        account.setHref(accountDto.getHref());
        account.setId(accountDto.getId());
        account.setName(accountDto.getName().orElse(null));
        account.setDescription(accountDto.getDescription().orElse(null));
        account.setCustomer(accountDto.getCustomer());
        return account;
    }

    public List<AccountDto> transform(List<Account> accountList) {
        if (accountList == null) {
            return null;
        }
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (Account account : accountList) {
            accountDtoList.add(transform(account));
        }
        return accountDtoList;
    }

    public AccountDto transform(Account account) {
        if (account == null) {
            return null;
        }
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setHref(account.getHref());
        accountDto.setName(Optional.ofNullable(account.getName()));
        accountDto.setDescription(Optional.ofNullable(account.getDescription()));
        accountDto.setCustomer(account.getCustomer());
        return accountDto;
    }
}
