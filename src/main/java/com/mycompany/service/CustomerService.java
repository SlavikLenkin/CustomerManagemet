package com.mycompany.service;

import com.mycompany.model.*;
import com.mycompany.repository.Customer;
import com.mycompany.repository.CustomerRepository;
import com.mycompany.transfomer.CustomerTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CustomerService {

    final CustomerRepository repository;
    private final AccountService accountService;
    private final EngagedPartyService engagedPartyService;
    private final RelatedPartyService relatedPartyService;
    private final PaymentMethodService paymentMethodService;
    private final CharacteristicService characteristicService;
    private final AgreementService agreementService;
    private final CreditProfileService creditProfileService;
    private final CustomerTransformer customerTransformer;
    private final ContactMediumService contactMediumService;


    public CustomerService(CustomerRepository repository
            , AccountService accountService, EngagedPartyService engagedPartyService
            , RelatedPartyService relatedPartyService, PaymentMethodService paymentMethodService
            , CharacteristicService characteristicService, AgreementService agreementService
            , CreditProfileService creditProfileService, CustomerTransformer customerTransformer
            , ContactMediumService contactMediumService) {

        this.repository = repository;
        this.accountService = accountService;
        this.engagedPartyService = engagedPartyService;
        this.relatedPartyService = relatedPartyService;
        this.paymentMethodService = paymentMethodService;
        this.characteristicService = characteristicService;
        this.agreementService = agreementService;
        this.creditProfileService = creditProfileService;
        this.customerTransformer = customerTransformer;
        this.contactMediumService = contactMediumService;
    }

    @Transactional
    public List<CustomerDto> findAllCustomers() {
        log.debug("getAllFullCustomer");
        List<CustomerDto> allCustomerDto = new ArrayList<>();
        List<Customer> customers = repository.findAll();
        for (Customer customer : customers) {
            allCustomerDto.add(customerTransformer.transform(customer));
        }
        return allCustomerDto;
    }

    @Transactional
    public CustomerDto findCustomerById(String id) {
        log.debug("findCustomerById");
        return customerTransformer.transform(repository.findCustomerById(id));
    }

    @Transactional
    public void delete(CustomerDto customerDto) {
        log.debug("delete");
        repository.delete(customerTransformer.transform(customerDto));
    }

    @Transactional
    public CustomerDto save(CustomerDto customerDto) {
        log.debug("save");
        Customer customer = customerTransformer.transform(customerDto);
        String id = UUID.randomUUID().toString();
        customer.setId(id);
        customer.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);

        customerDto.setCustomer(repository.save(customer));
        customerDto.setEngagedParty(engagedPartyService.save(customerDto.getEngagedParty(), customer));
        customerDto.setAccounts(accountService.save(customerDto.getAccounts(), customer));
        customerDto.setAgreements(agreementService.save(customerDto.getAgreements(), customer));
        customerDto.setCharacteristics(characteristicService.save(customerDto.getCharacteristics(), customer));
        customerDto.setCreditProfiles(creditProfileService.save(customerDto.getCreditProfiles(), customer));
        customerDto.setPaymentMethods(paymentMethodService.save(customerDto.getPaymentMethods(), customer));
        customerDto.setRelatedParties(relatedPartyService.save(customerDto.getRelatedParties(), customer));
        customerDto.setContactMediumDtoList(contactMediumService.save(customerDto.getContactMediumDtoList(), customer));
        return customerDto;
    }

    @Transactional
    public CustomerDto update(String id, CustomerDto customerDtoUpdate) {
        log.debug("update");
        CustomerDto customerDto = findCustomerById(id);
        if (customerDto == null) {
            return null;
        }
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

        EngagedPartyDto engagedPartyUpdate = customerDtoUpdate.getEngagedParty();
        EngagedPartyDto engagedParty = customerDto.getEngagedParty();

        if (engagedPartyUpdate != null) {
            if (engagedPartyUpdate.getName() != null) {
                engagedParty.setName(engagedPartyUpdate.getName());
            }
        }

        customerDto.setEngagedParty(engagedPartyService.update(engagedParty));
        List<AccountDto> accountsUpdate = customerDtoUpdate.getAccounts();
        List<AccountDto> accounts = customerDto.getAccounts();

        if (accountsUpdate != null) {
            for (AccountDto accountUpdate : accountsUpdate) {
                if (accountUpdate != null)
                    for (AccountDto account : accounts) {
                        if (account.getId().equals(accountUpdate.getId())) {
                            if (accountUpdate.getName() != null) {
                                account.setName(accountUpdate.getName());
                            }
                            if (accountUpdate.getDescription() != null) {
                                account.setDescription(accountUpdate.getDescription());
                            }
                        }
                    }

            }
            customerDto.setAccounts(accountService.update(accounts));
        }

        List<AgreementDto> agreementsUpdate = customerDtoUpdate.getAgreements();
        List<AgreementDto> agreements = customerDto.getAgreements();

        if (agreementsUpdate != null) {
            for (AgreementDto agreementUpdate : agreementsUpdate) {
                for (AgreementDto agreement : agreements) {
                    if (agreement.getId().equals(agreementUpdate.getId())) {
                        if (agreementUpdate.getName() != null) {
                            agreement.setName(agreementUpdate.getName());
                        }
                    }
                }

            }
            customerDto.setAgreements(agreementService.update(agreements));
        }

        List<CharacteristicDto> characteristicsUpdate = customerDtoUpdate.getCharacteristics();
        List<CharacteristicDto> characteristics = customerDto.getCharacteristics();

        if (characteristicsUpdate != null) {
            for (CharacteristicDto characteristicUpdate : characteristicsUpdate) {
                for (CharacteristicDto characteristic : characteristics) {
                    if (characteristic.getId().equals(characteristicUpdate.getId())) {
                        if (characteristicUpdate.getName() != null) {
                            characteristic.setName(characteristicUpdate.getName());
                        }
                        if (characteristicUpdate.getValue() != null) {
                            characteristic.setValue(characteristicUpdate.getValue());
                        }
                        if (characteristicUpdate.getValueType() != null) {
                            characteristic.setValueType(characteristicUpdate.getValueType());
                        }
                    }
                }

            }
            customerDto.setCharacteristics(characteristicService.update(characteristics));
        }

        List<CreditProfileDto> creditProfilesUpdate = customerDtoUpdate.getCreditProfiles();
        List<CreditProfileDto> creditProfiles = customerDto.getCreditProfiles();

        if (creditProfilesUpdate != null) {
            for (CreditProfileDto creditProfileUpdate : creditProfilesUpdate) {
                for (CreditProfileDto creditProfile : creditProfiles) {
                    if (creditProfile.getId().equals(creditProfileUpdate.getId())) {
                        if (creditProfileUpdate.getCreditProfileDate() != null) {
                            creditProfile.setCreditProfileDate(creditProfileUpdate.getCreditProfileDate());
                        }
                        if (creditProfileUpdate.getCreditRiskRating() != 0) {
                            creditProfile.setCreditRiskRating(creditProfileUpdate.getCreditRiskRating());
                        }
                        if (creditProfileUpdate.getCreditScore() != 0) {
                            creditProfile.setCreditScore(creditProfileUpdate.getCreditScore());
                        }
                        if (creditProfileUpdate.getValidFor() != null) {
                            creditProfile.setValidFor(creditProfileUpdate.getValidFor());
                        }
                    }
                }

            }
            customerDto.setCreditProfiles(creditProfileService.update(creditProfiles));
        }

        List<PaymentMethodDto> paymentMethodsUpdate = customerDtoUpdate.getPaymentMethods();
        List<PaymentMethodDto> paymentMethods = customerDto.getPaymentMethods();

        if (paymentMethodsUpdate != null) {
            for (PaymentMethodDto paymentMethodUpdate : paymentMethodsUpdate) {
                for (PaymentMethodDto paymentMethod : paymentMethods) {
                    if (paymentMethod.getId().equals(paymentMethodUpdate.getId())) {
                        if (paymentMethodUpdate.getName() != null) {
                            paymentMethod.setName(paymentMethodUpdate.getName());
                        }
                    }
                }

            }
            customerDto.setPaymentMethods(paymentMethodService.update(paymentMethods));
        }

        List<RelatedPartyDto> relatedPartiesUpdate = customerDtoUpdate.getRelatedParties();
        List<RelatedPartyDto> relatedParties = customerDto.getRelatedParties();

        if (relatedPartiesUpdate != null) {
            for (RelatedPartyDto relatedPartyUpdate : relatedPartiesUpdate) {
                for (RelatedPartyDto relatedParty : relatedParties) {
                    if (relatedParty.getId().equals(relatedPartyUpdate.getId())) {
                        if (relatedPartyUpdate.getName() != null) {
                            relatedParty.setName(relatedPartyUpdate.getName());
                        }
                        if (relatedPartyUpdate.getRole() != null) {
                            relatedParty.setRole(relatedPartyUpdate.getRole());
                        }
                    }
                }

            }
            customerDto.setRelatedParties(relatedPartyService.update(relatedParties));
        }

        List<ContactMediumDto> contactMediumDtoListUpdate = customerDtoUpdate.getContactMediumDtoList();
        List<ContactMediumDto> contactMediumDtoList = customerDto.getContactMediumDtoList();

        if (contactMediumDtoListUpdate != null) {
            for (ContactMediumDto contactMediumDtoUpdate : contactMediumDtoListUpdate) {
                for (ContactMediumDto contactMediumDto : contactMediumDtoList) {
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
                        if (contactMediumDtoUpdate.getMediumCharacteristic() != null) {
                            MediumCharacteristicDto mediumCharacteristicUpdate = contactMediumDtoUpdate
                                    .getMediumCharacteristic();
                            MediumCharacteristicDto mediumCharacteristic = contactMediumDto
                                    .getMediumCharacteristic();
                            if (mediumCharacteristicUpdate.getCity() != null) {
                                mediumCharacteristic.setCity(mediumCharacteristicUpdate.getCity());
                            }
                            if (mediumCharacteristicUpdate.getContactType() != null) {
                                mediumCharacteristic.setContactType(mediumCharacteristicUpdate.getContactType());
                            }
                            if (mediumCharacteristicUpdate.getCountry() != null) {
                                mediumCharacteristic.setCountry(mediumCharacteristicUpdate.getCountry());
                            }
                            if (mediumCharacteristicUpdate.getEmailAddress() != null) {
                                mediumCharacteristic.setEmailAddress(mediumCharacteristicUpdate.getEmailAddress());
                            }
                            if (mediumCharacteristicUpdate.getFaxNumber() != null) {
                                mediumCharacteristic.setFaxNumber(mediumCharacteristicUpdate.getFaxNumber());
                            }
                            if (mediumCharacteristicUpdate.getPhoneNumber() != null) {
                                mediumCharacteristic.setPhoneNumber(mediumCharacteristicUpdate.getPhoneNumber());
                            }
                            if (mediumCharacteristicUpdate.getPostCode() != null) {
                                mediumCharacteristic.setPostCode(mediumCharacteristicUpdate.getPostCode());
                            }
                            if (mediumCharacteristicUpdate.getSocialNetworkId() != null) {
                                mediumCharacteristic.setSocialNetworkId(mediumCharacteristicUpdate
                                        .getSocialNetworkId());
                            }
                            if (mediumCharacteristicUpdate.getStateOrProvince() != null) {
                                mediumCharacteristic.setStateOrProvince(mediumCharacteristicUpdate
                                        .getStateOrProvince());
                            }
                            if (mediumCharacteristicUpdate.getStreet1() != null) {
                                mediumCharacteristic.setStreet1(mediumCharacteristicUpdate.getStreet1());
                            }
                            if (mediumCharacteristicUpdate.getStreet2() != null) {
                                mediumCharacteristic.setStreet2(mediumCharacteristicUpdate.getStreet2());
                            }
                            contactMediumDto.setMediumCharacteristic(mediumCharacteristic);
                        }
                    }
                }
            }
            customerDto.setContactMediumDtoList(contactMediumService.update(contactMediumDtoList, customerTransformer
                    .transform(customerDto)));
        }
        repository.updateCustomerById(customer);
        customerDto.setCustomer(customer);
        return customerDto;
    }
}
