package com.mycompany.transfomer;

import com.mycompany.model.CustomerDto;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerTransformer {

    final
    ContactMediumTransformer contactMediumTransformer;

    public CustomerTransformer(ContactMediumTransformer contactMediumTransformer) {
        this.contactMediumTransformer = contactMediumTransformer;
    }

    public Customer transform(CustomerDto customerDto) {
        Customer target = new Customer();
        target.setHref(customerDto.getHref());
        target.setId(customerDto.getId());
        target.setName(customerDto.getName());
        target.setStatus(customerDto.getStatus());
        target.setStatusReason(customerDto.getStatusReason());
        target.setValidFor(customerDto.getValidFor());
        return target;
    }

    public CustomerDto getFullCustomer(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEngagedParty(customer.getEngagedParty());
        customerDto.setAccounts(customer.getAccounts());
        customerDto.setAgreements(customer.getAgreements());
        customerDto.setCharacteristics(customer.getCharacteristics());
        customerDto.setCreditProfiles(customer.getCreditProfiles());
        customerDto.setPaymentMethods(customer.getPaymentMethods());
        customerDto.setRelatedParties(customer.getRelatedParties());
        customerDto.setContactMediumDtoList(contactMediumTransformer.getContactMediumDto(customer.getContactMediumList()));
        customerDto.setCustomer(customer);
        return customerDto;
    }
}
