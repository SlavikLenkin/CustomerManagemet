package com.mycompany.transfomer;

import com.mycompany.model.CustomerDto;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerTransformer {

    final ContactMediumTransformer contactMediumTransformer;
    final AccountTransformer accountTransformer;
    final AgreementTransformer agreementTransformer;
    final CharacteristicTransformer characteristicTransformer;
    final CreditProfileTransformer creditProfileTransformer;
    final EngagedPartyTransformer engagedPartyTransformer;
    final PaymentMethodTransformer paymentMethodTransformer;
    final RelatedPartyTransformer relatedPartyTransformer;

    public CustomerTransformer(ContactMediumTransformer contactMediumTransformer
            , AccountTransformer accountTransformer
            , AgreementTransformer agreementTransformer
            , CharacteristicTransformer characteristicTransformer
            , CreditProfileTransformer creditProfileTransformer
            , EngagedPartyTransformer engagedPartyTransformer
            , PaymentMethodTransformer paymentMethodTransformer
            , RelatedPartyTransformer relatedPartyTransformer) {
        this.contactMediumTransformer = contactMediumTransformer;
        this.accountTransformer = accountTransformer;
        this.agreementTransformer = agreementTransformer;
        this.characteristicTransformer = characteristicTransformer;
        this.creditProfileTransformer = creditProfileTransformer;
        this.engagedPartyTransformer = engagedPartyTransformer;
        this.paymentMethodTransformer = paymentMethodTransformer;
        this.relatedPartyTransformer = relatedPartyTransformer;
    }

    public Customer transform(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setHref(customerDto.getHref());
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName().orElse(null));
        customer.setStatus(customerDto.getStatus().orElse(null));
        customer.setStatusReason(customerDto.getStatusReason().orElse(null));
        customer.setValidFor(customerDto.getValidFor());
        return customer;
    }

    public CustomerDto transform(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEngagedParty(engagedPartyTransformer.transform(customer.getEngagedParty()));
        customerDto.setAccounts(accountTransformer.transform(customer.getAccounts()));
        customerDto.setAgreements(agreementTransformer.transform(customer.getAgreements()));
        customerDto.setCharacteristics(characteristicTransformer.transform(customer.getCharacteristics()));
        customerDto.setCreditProfiles(creditProfileTransformer.transform(customer.getCreditProfiles()));
        customerDto.setPaymentMethods(paymentMethodTransformer.transform(customer.getPaymentMethods()));
        customerDto.setRelatedParties(relatedPartyTransformer.transform(customer.getRelatedParties()));
        customerDto.setContactMediumDtoList(contactMediumTransformer.transform(customer.getContactMediumList()));
        customerDto.setCustomer(customer);
        return customerDto;
    }
}
