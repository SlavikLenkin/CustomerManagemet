package com.mycompany.service;

import com.mycompany.model.CustomerDto;
import com.mycompany.repository.Account;
import com.mycompany.repository.Customer;
import com.mycompany.repository.EngagedParty;
import com.mycompany.repository.RelatedParty;
import com.mycompany.transfomer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerDtoService {


    private final CustomerService customerService;
    private final AccountService accountService;
    private final EngagedPartyService engagedPartyService;
    private final RelatedPartyService relatedPartyService;

    public CustomerDtoService(CustomerService customerService, AccountService accountService,
                              EngagedPartyService engagedPartyService, RelatedPartyService relatedPartyService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.engagedPartyService = engagedPartyService;
        this.relatedPartyService = relatedPartyService;
    }

    private CustomerDto getFullCustomer(Customer customer) {
        List<Account> accounts = accountService.findAllAccounts(customer);
        List<RelatedParty> relatedParties = relatedPartyService.findAllRelatedParties(customer);
        EngagedParty engagedParty = engagedPartyService.findEngagedParty(customer);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomer(customer);
        customerDto.setEngagedParty(engagedParty);
        customerDto.setAccounts(accounts);
        customerDto.setRelatedParties(relatedParties);
        return customerDto;
    }

    public List<CustomerDto> getAllFullCustomer() {
        List<CustomerDto> allCustomerDto = new ArrayList<>();
        List<Customer> customers = customerService.findAllCustomers();
        for (Customer customer : customers) {
            allCustomerDto.add(getFullCustomer(customer));
        }
        return allCustomerDto;
    }

    public CustomerDto getFullCustomerById(String id) {
        Customer customer = customerService.findCustomerById(id);
        return getFullCustomer(customer);
    }

    public CustomerDto save(CustomerDto customerDto) {
        CustomerTransformer transformer = new CustomerTransformer();

        EngagedParty engagedParty = customerDto.getEngagedParty();
        List<Account> accounts = customerDto.getAccounts();
        List<RelatedParty> relatedParties = customerDto.getRelatedParties();


        customerDto.setEngagedParty(engagedPartyService.save(engagedParty));
        customerDto.setAccounts(accountService.save(accounts));
        customerDto.setRelatedParties(relatedPartyService.save(relatedParties));


        accounts = customerDto.getAccounts();
        relatedParties = customerDto.getRelatedParties();
        engagedParty = customerDto.getEngagedParty();

        Customer customer = transformer.transform(customerDto);
        if (engagedParty != null) {
            customer.setEngagedPartyId(engagedParty.getId());
        }

        int i = 0;
        if (accounts != null) {
            String[] idAccounts = new String[accounts.size()];
            for (Account account : accounts) {
                idAccounts[i] = account.getId();
                i++;
            }
            customer.setAccountId(idAccounts);
        }

        if (relatedParties != null) {
            String[] idRelatedParties = new String[relatedParties.size()];
            i = 0;
            for (RelatedParty relatedParty : relatedParties) {
                idRelatedParties[i] = relatedParty.getId();
                i++;
            }
            customer.setRelatedPartyId(idRelatedParties);
        }

        //customer.setRelatedPartyId(null);

        customerDto.setCustomer(customerService.save(customer));
        return customerDto;
    }

    public void delete(CustomerDto customerDto) {
        CustomerTransformer transformer = new CustomerTransformer();
        EngagedParty engagedParty = customerDto.getEngagedParty();
        List<Account> accounts = customerDto.getAccounts();
        List<RelatedParty> relatedParties = customerDto.getRelatedParties();


        engagedPartyService.delete(engagedParty);

        for (Account account : accounts) {
            accountService.delete(account);
        }

        for (RelatedParty relatedParty : relatedParties) {
            relatedPartyService.delete(relatedParty);
        }

        customerService.delete(transformer.transform(customerDto));
    }

}
