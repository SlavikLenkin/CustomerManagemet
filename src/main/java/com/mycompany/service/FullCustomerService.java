package com.mycompany.service;

import com.mycompany.model.FullCustomer;
import com.mycompany.repository.Account;
import com.mycompany.repository.Customer;
import com.mycompany.repository.EngagedParty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FullCustomerService {


    private final CustomerService customerService;
    private final AccountService accountService;
    private final EngagedPartyService engagedPartyService;

    public FullCustomerService(CustomerService customerService, AccountService accountService,
                               EngagedPartyService engagedPartyService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.engagedPartyService = engagedPartyService;
    }

    private FullCustomer getFullCustomer(Customer customer) {
        List<Account> accounts = accountService.findAllAccount(customer);
        EngagedParty engagedParty = engagedPartyService.findEngagedParty(customer);
        FullCustomer fullCustomer = new FullCustomer();
        fullCustomer.setCustomer(customer);
        fullCustomer.setEngagedParty(engagedParty);
        fullCustomer.setAccounts(accounts);
        return fullCustomer;
    }

    public List<FullCustomer> getAllFullCustomer() {
        List<FullCustomer> allFullCustomer = new ArrayList<>();
        List<Customer> customers = customerService.findAllCustomers();
        for (Customer customer : customers) {
            allFullCustomer.add(getFullCustomer(customer));
        }
        return allFullCustomer;
    }

    public FullCustomer getFullCustomerById(String id) {
        Customer customer = customerService.findCustomerById(id);
        return getFullCustomer(customer);
    }

    public FullCustomer save(FullCustomer fullCustomer) {
        fullCustomer.setAccounts(accountService.save(fullCustomer.getAccounts()));
        // fullCustomer.setCustomer(customerService.save(fullCustomer.getCustomer()));
        return fullCustomer;
    }

    public void delete(FullCustomer fullCustomer) {
        List<Account> accounts = fullCustomer.getAccounts();
        for (Account account: accounts) {
            accountService.delete(account);
        }
        customerService.delete(fullCustomer.getCustomer());
    }

}
