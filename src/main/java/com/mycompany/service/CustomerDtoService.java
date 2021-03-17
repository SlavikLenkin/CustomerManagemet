package com.mycompany.service;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.model.CustomerDto;
import com.mycompany.repository.*;
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
    private final PaymentMethodService paymentMethodService;
    private final CharacteristicService characteristicService;
    private final AgreementService agreementService;
    private final ContactMediumDtoService contactMediumDtoService;
    private final CreditProfileService creditProfileService;
    private final CustomerTransformer customerTransformer;
    private final CustomerAccountService customerAccountService;


    ////// Можно ли так????
    private EngagedParty engagedParty;
    private List<Account> accounts;
    private List<RelatedParty> relatedParties;
    private List<PaymentMethod> paymentMethods;
    private List<Characteristic> characteristics;
    private List<Agreement> agreements;
    private List<ContactMediumDto> contactMediumDtoList;
    private List<CreditProfile> creditProfiles;

    public CustomerDtoService(CustomerService customerService, AccountService accountService,
                              EngagedPartyService engagedPartyService, RelatedPartyService relatedPartyService,
                              PaymentMethodService paymentMethodService, CharacteristicService characteristicService,
                              AgreementService agreementService, ContactMediumDtoService contactMediumService,
                              CreditProfileService creditProfileService, CustomerTransformer customerTransformer, CustomerAccountService customerAccountService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.engagedPartyService = engagedPartyService;
        this.relatedPartyService = relatedPartyService;
        this.paymentMethodService = paymentMethodService;
        this.characteristicService = characteristicService;
        this.agreementService = agreementService;
        this.contactMediumDtoService = contactMediumService;
        this.creditProfileService = creditProfileService;
        this.customerTransformer = customerTransformer;
        this.customerAccountService = customerAccountService;
    }


    private CustomerDto getFullCustomer(Customer customer) {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setEngagedParty(customer.getEngagedParty());
        customerDto.setAccounts(customer.getAccounts());
        customerDto.setAgreements(customer.getAgreements());
        customerDto.setCharacteristics(customer.getCharacteristics());
        customerDto.setCreditProfiles(customer.getCreditProfiles());
        customerDto.setPaymentMethods(customer.getPaymentMethods());
        customerDto.setRelatedParties(customer.getRelatedParties());
        customerDto.setCustomer(customer);


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

    public CustomerDto updateFullCustomerById(String id, CustomerDto customerDtoUpdate) {
        CustomerDto customerDto = getFullCustomerById(id);

        Customer customerUpdate = customerTransformer.transform(customerDtoUpdate);
        Customer customer = customerTransformer.transform(customerDto);

        if (customerUpdate.getName() != null) {
            customer.setName(customerUpdate.getName());
        }

        if (customerUpdate.getStatus() != null) {
            customer.setStatus(customerUpdate.getStatus());
        }

        if (customerUpdate.getStatusReason() != null) {
            customer.setStatusReason(customerUpdate.getStatusReason());
        }

        if (customerUpdate.getValidFor() != null) {
            customer.setValidFor(customerUpdate.getValidFor());
        }


       EngagedParty engagedPartyUpdate = customerDtoUpdate.getEngagedParty();
        engagedParty = customerDto.getEngagedParty();

        if (engagedPartyUpdate !=null){
            if (engagedPartyUpdate.getName()!=null){
                engagedParty.setName(engagedPartyUpdate.getName());
            }
        }

        customerDto.setEngagedParty(engagedPartyService.update(engagedParty));


        List<Account> accountsUpdate = customerDtoUpdate.getAccounts();
        accounts = customerDto.getAccounts();


        if (accountsUpdate != null) {
            for (Account accountUpdate : accountsUpdate) {
                if (accountUpdate!= null)
                for (Account account : accounts) {
                    if (account.getId().equals(accountUpdate.getId())) {
                        if (accountUpdate.getName()!=null){
                            account.setName(accountUpdate.getName());
                        }
                        if (accountUpdate.getDescription()!=null){
                            account.setDescription(accountUpdate.getDescription());
                        }
                    }
                }

            }
            customerDto.setAccounts(accountService.update(accounts));

        }


        List<Agreement> agreementsUpdate = customerDtoUpdate.getAgreements();
        agreements = customerDto.getAgreements();

        if (agreementsUpdate != null) {
            for (Agreement agreementUpdate : agreementsUpdate) {
                for (Agreement agreement : agreements) {
                    if (agreement.getId().equals(agreementUpdate.getId())) {
                        if (agreementUpdate.getName()!=null){
                            agreement.setName(agreementUpdate.getName());
                        }
                    }
                }

            }
            customerDto.setAgreements(agreementService.update(agreements));
        }


        List<Characteristic> characteristicsUpdate = customerDtoUpdate.getCharacteristics();
        characteristics = customerDto.getCharacteristics();

        if (characteristicsUpdate != null) {
            for (Characteristic characteristicUpdate : characteristicsUpdate) {
                for (Characteristic characteristic : characteristics) {
                    if (characteristic.getId().equals(characteristicUpdate.getId())) {
                        if (characteristicUpdate.getName()!=null){
                            characteristic.setName(characteristicUpdate.getName());
                        }
                        if (characteristicUpdate.getValue()!=null){
                            characteristic.setValue(characteristicUpdate.getValue());
                        }
                        if (characteristicUpdate.getValueType()!=null){
                            characteristic.setValueType(characteristicUpdate.getValueType());
                        }
                    }
                }

            }
            customerDto.setCharacteristics(characteristicService.update(characteristics));
        }

        List<CreditProfile> creditProfilesUpdate = customerDtoUpdate.getCreditProfiles();
        creditProfiles = customerDto.getCreditProfiles();

        if (creditProfilesUpdate != null) {
            for (CreditProfile creditProfileUpdate : creditProfilesUpdate) {
                for (CreditProfile creditProfile : creditProfiles) {
                    if (creditProfile.getId().equals(creditProfileUpdate.getId())) {
                        if (creditProfileUpdate.getCreditProfileDate()!=null){
                            creditProfile.setCreditProfileDate(creditProfileUpdate.getCreditProfileDate());
                        }
                        if(creditProfileUpdate.getCreditRiskRating()!=0){
                            creditProfile.setCreditRiskRating(creditProfileUpdate.getCreditRiskRating());
                        }
                        if (creditProfileUpdate.getCreditScore()!=0){
                            creditProfile.setCreditScore(creditProfileUpdate.getCreditScore());
                        }
                        if (creditProfileUpdate.getValidFor()!=null){
                            creditProfile.setValidFor(creditProfileUpdate.getValidFor());
                        }
                    }
                }

            }

            customerDto.setCreditProfiles(creditProfileService.update(creditProfiles));
        }

        List<PaymentMethod> paymentMethodsUpdate = customerDtoUpdate.getPaymentMethods();
        paymentMethods = customerDto.getPaymentMethods();

        if (paymentMethodsUpdate != null) {
            for (PaymentMethod paymentMethodUpdate : paymentMethodsUpdate) {
                for (PaymentMethod paymentMethod : paymentMethods) {
                    if (paymentMethod.getId().equals(paymentMethodUpdate.getId())) {
                        if (paymentMethodUpdate.getName()!=null){
                            paymentMethod.setName(paymentMethodUpdate.getName());
                        }
                    }
                }

            }
            customerDto.setPaymentMethods(paymentMethodService.update(paymentMethods));
        }

        List<RelatedParty> relatedPartiesUpdate = customerDtoUpdate.getRelatedParties();
        relatedParties = customerDto.getRelatedParties();

        if (relatedPartiesUpdate != null) {
            for (RelatedParty relatedPartyUpdate : relatedPartiesUpdate) {
                for (RelatedParty relatedParty : relatedParties) {
                    if (relatedParty.getId().equals(relatedPartyUpdate.getId())) {
                        if (relatedPartyUpdate.getName()!=null){
                            relatedParty.setName(relatedPartyUpdate.getName());
                        }
                        if (relatedPartyUpdate.getRole()!=null){
                            relatedParty.setRole(relatedPartyUpdate.getRole());
                        }
                    }
                }

            }
            customerDto.setRelatedParties(relatedPartyService.update(relatedParties));
        }






        /*


        //////MediumCharacteristic
        List<ContactMediumDto> contactMediumDtoListUpdate = customerDtoUpdate.getContactMediumDtoList();
        contactMediumDtoList = customerDto.getContactMediumDtoList();

        if(contactMediumDtoListUpdate!=null){
            for (ContactMediumDto contactMediumDtoUpdate : contactMediumDtoListUpdate){
                for (ContactMediumDto contactMediumDto : contactMediumDtoList){
                    if (contactMediumDto.getId().equals(contactMediumDtoUpdate.getId())) {
                        if (contactMediumDtoUpdate.isPreferred() != contactMediumDto.isPreferred()) {
                            contactMediumDto.setPreferred(contactMediumDtoUpdate.isPreferred());
                        }
                        if (contactMediumDtoUpdate.getMediumType() != null) {
                            contactMediumDto.setMediumType(contactMediumDtoUpdate.getMediumType());
                        }
                        if (contactMediumDtoUpdate.getValidFor() != null) {
                            contactMediumDto.setValidFor(contactMediumDtoUpdate.getValidFor());
                        }
                        if (contactMediumDtoUpdate.getMediumCharacteristic()!=null){
                            MediumCharacteristic mediumCharacteristicUpdate = contactMediumDtoUpdate
                                    .getMediumCharacteristic();
                            MediumCharacteristic mediumCharacteristic = contactMediumDto
                                    .getMediumCharacteristic();
                            if (mediumCharacteristicUpdate.getCity()!=null){
                                mediumCharacteristic.setCity(mediumCharacteristicUpdate.getCity());
                            }
                            if (mediumCharacteristicUpdate.getContactType()!=null){
                                mediumCharacteristic.setContactType(mediumCharacteristicUpdate.getContactType());
                            }
                            if (mediumCharacteristicUpdate.getCountry()!=null){
                                mediumCharacteristic.setCountry(mediumCharacteristicUpdate.getCountry());
                            }
                            if (mediumCharacteristicUpdate.getEmailAddress()!=null){
                                mediumCharacteristic.setEmailAddress(mediumCharacteristicUpdate.getEmailAddress());
                            }
                            if (mediumCharacteristicUpdate.getFaxNumber()!=null){
                                mediumCharacteristic.setFaxNumber(mediumCharacteristicUpdate.getFaxNumber());
                            }
                            if (mediumCharacteristicUpdate.getPhoneNumber()!=null){
                                mediumCharacteristic.setPhoneNumber(mediumCharacteristicUpdate.getPhoneNumber());
                            }
                            if (mediumCharacteristicUpdate.getPostCode()!=null) {
                                mediumCharacteristic.setPostCode(mediumCharacteristicUpdate.getPostCode());
                            }
                            if (mediumCharacteristicUpdate.getSocialNetworkId()!=null){
                                mediumCharacteristic.setSocialNetworkId(mediumCharacteristicUpdate
                                        .getSocialNetworkId());
                            }
                            if (mediumCharacteristicUpdate.getStateOrProvince()!=null){
                                mediumCharacteristic.setStateOrProvince(mediumCharacteristicUpdate
                                        .getStateOrProvince());
                            }
                            if (mediumCharacteristicUpdate.getStreet1()!=null) {
                                mediumCharacteristic.setStreet1(mediumCharacteristicUpdate.getStreet1());
                            }
                            if (mediumCharacteristicUpdate.getStreet2()!=null){
                                mediumCharacteristic.setStreet2(mediumCharacteristicUpdate.getStreet2());
                            }
                            contactMediumDto.setMediumCharacteristic(mediumCharacteristic);
                        }
                    }
                }
            }
            customerDto.setContactMediumDtoList(contactMediumDtoService.update(contactMediumDtoList));
        }

        if (contactMediumDtoList != null) {
            String[] idContactMedium = new String[contactMediumDtoList.size()];
            i = 0;
            for (ContactMediumDto contactMediumDto : contactMediumDtoList) {
                idContactMedium[i] = contactMediumDto.getId();
                i++;
            }
            customer.setContactMediumId(idContactMedium);
        }




*/



        customerDto.setCustomer(customerService.update(customer));
        return customerDto;
    }

    public CustomerDto save(CustomerDto customerDto) {

        Customer customer = customerTransformer.transform(customerDto);
        customerDto.setCustomer(customerService.save(customer));

        customerDto.setEngagedParty(engagedPartyService.save(customerDto.getEngagedParty(),customer));
        customerDto.setAccounts(accountService.save(customerDto.getAccounts(),customer));
        customerDto.setAgreements(agreementService.save(customerDto.getAgreements(),customer));
        customerDto.setCharacteristics(characteristicService.save(customerDto.getCharacteristics(),customer));
        customerDto.setCreditProfiles(creditProfileService.save(customerDto.getCreditProfiles(),customer));
        customerDto.setPaymentMethods(paymentMethodService.save(customerDto.getPaymentMethods(),customer));
        customerDto.setRelatedParties(relatedPartyService.save(customerDto.getRelatedParties(),customer));
        customerDto.setContactMediumDtoList(contactMediumDtoService.save(customerDto.getContactMediumDtoList(),customer));



        return customerDto;
    }



    public void delete(CustomerDto customerDto) {
        customerService.delete(customerTransformer.transform(customerDto));
    }
}
