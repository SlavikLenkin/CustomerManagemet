package com.mycompany.service;

import com.mycompany.kafka.Producer;
import com.mycompany.kafka.service.EventService;
import com.mycompany.model.*;
import com.mycompany.repository.Customer;
import com.mycompany.repository.CustomerRepository;
import com.mycompany.transfomer.CustomerTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CustomerService {

    public static final String CUSTOMER_CREATE_EVENT = "CustomerCreateEvent";
    private final CustomerRepository repository;
    private final AccountService accountService;
    private final EngagedPartyService engagedPartyService;
    private final RelatedPartyService relatedPartyService;
    private final PaymentMethodService paymentMethodService;
    private final CharacteristicService characteristicService;
    private final AgreementService agreementService;
    private final CreditProfileService creditProfileService;
    private final CustomerTransformer customerTransformer;
    private final ContactMediumService contactMediumService;
    private final Producer producer;
    private final EventService eventService;

    public CustomerService(CustomerRepository repository
            , AccountService accountService, EngagedPartyService engagedPartyService
            , RelatedPartyService relatedPartyService, PaymentMethodService paymentMethodService
            , CharacteristicService characteristicService, AgreementService agreementService
            , CreditProfileService creditProfileService, CustomerTransformer customerTransformer
            , ContactMediumService contactMediumService, Producer producer, EventService eventService) {

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
        this.producer = producer;
        this.eventService = eventService;
    }

    @Transactional
    public List<CustomerDto> findAllCustomers() {
        log.debug("getAllCustomer");
        List<CustomerDto> CustomersDto = new ArrayList<>();
        List<Customer> customers = repository.findAll();
        for (Customer customer : customers) {
            CustomersDto.add(customerTransformer.transform(customer));
        }
        return CustomersDto;
    }

    @Transactional
    public CustomerDto findCustomerById(String id) {
        log.debug("findCustomerById{}", id);
        return customerTransformer.transform(repository.findCustomerById(id));
    }

    @Transactional
    public void delete(CustomerDto customerDto) {
        log.debug("delete");
        this.producer.sendMessage(eventService.createEvent(customerDto, "CustomerDeleteEvent")
                .toString());
        repository.delete(customerTransformer.transform(customerDto));
    }

    @Transactional
    public CustomerDto save(CustomerDto customerDto) {
        log.debug("save");
        if (customerDto.getStatus() == null) {
            customerDto.setStatus(Optional.empty());
        }
        if (customerDto.getName() == null) {
            customerDto.setName(Optional.empty());
        }
        if (customerDto.getStatusReason() == null) {
            customerDto.setStatusReason(Optional.empty());
        }
        Customer customer = customerTransformer.transform(customerDto);
        String id = UUID.randomUUID().toString();
        customer.setId(id);
        customer.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
        repository.save(customer);
        customerDto.setCustomer(customer);
        customerDto.setEngagedParty(engagedPartyService
                .save(customerDto.getEngagedParty(), customer));
        customerDto.setAccounts(accountService
                .save(customerDto.getAccounts(), customer));
        customerDto.setAgreements(agreementService
                .save(customerDto.getAgreements(), customer));
        customerDto.setCharacteristics(characteristicService
                .save(customerDto.getCharacteristics(), customer));
        customerDto.setCreditProfiles(creditProfileService
                .save(customerDto.getCreditProfiles(), customer));
        customerDto.setPaymentMethods(paymentMethodService
                .save(customerDto.getPaymentMethods(), customer));
        customerDto.setRelatedParties(relatedPartyService
                .save(customerDto.getRelatedParties(), customer));
        customerDto.setContactMediumDtoList(contactMediumService
                .save(customerDto.getContactMediumDtoList(), customer));
        this.producer.sendMessage(eventService.createEvent(customerDto, CUSTOMER_CREATE_EVENT)
                .toString());
        return customerDto;
    }

    @Transactional
    public CustomerDto update(String id, CustomerDto customerDtoUpdate) {
        log.debug("update");
        CustomerDto customerDto = findCustomerById(id);
        if (customerDto == null) {
            return null;
        }

        if (Optional.ofNullable(customerDtoUpdate.getName()).isPresent()) {
            customerDto.setName(customerDtoUpdate.getName());
        }

        if (Optional.ofNullable(customerDtoUpdate.getStatus()).isPresent()) {
            this.producer.sendMessage(eventService.createEvent(customerDto.getStatus(), customerDtoUpdate.getStatus()
                    , "CustomerStateChangeEvent").toString());
            customerDto.setStatus(customerDtoUpdate.getStatus());
        }

        if (Optional.ofNullable(customerDtoUpdate.getStatusReason()).isPresent()) {
            customerDto.setStatusReason(customerDtoUpdate.getStatusReason());
        }

        if (Optional.ofNullable(customerDtoUpdate.getValidFor()).isPresent()) {
            customerDto.setValidFor(customerDtoUpdate.getValidFor());
        }

        EngagedPartyDto engagedPartyUpdate = customerDtoUpdate.getEngagedParty();
        EngagedPartyDto engagedParty = customerDto.getEngagedParty();

        if (engagedPartyUpdate != null) {
            if (Optional.ofNullable(engagedPartyUpdate.getName()).isPresent()) {
                engagedParty.setName(engagedPartyUpdate.getName());
            }
            customerDto.setEngagedParty(engagedPartyService.update(engagedParty));
        }

        List<AccountDto> accountsUpdate = customerDtoUpdate.getAccounts();
        List<AccountDto> accounts = customerDto.getAccounts();

        if (accountsUpdate != null) {
            for (AccountDto accountUpdate : accountsUpdate) {
                if (accountUpdate != null)
                    for (AccountDto account : accounts) {
                        if (account.getId().equals(accountUpdate.getId())) {
                            if (Optional.ofNullable(accountUpdate.getName()).isPresent()) {
                                account.setName(accountUpdate.getName());
                            }
                            if (Optional.ofNullable(accountUpdate.getDescription()).isPresent()) {
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
                        if (Optional.ofNullable(agreementUpdate.getName()).isPresent()) {
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
                        if (Optional.ofNullable(characteristicUpdate.getName()).isPresent()) {
                            characteristic.setName(characteristicUpdate.getName());
                        }
                        if (Optional.ofNullable(characteristicUpdate.getValue()).isPresent()) {
                            characteristic.setValue(characteristicUpdate.getValue());
                        }
                        if (Optional.ofNullable(characteristicUpdate.getValueType()).isPresent()) {
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
                        if (Optional.ofNullable(creditProfileUpdate.getCreditProfileDate()).isPresent()) {
                            creditProfile.setCreditProfileDate(creditProfileUpdate.getCreditProfileDate());
                        }
                        if (Optional.ofNullable(creditProfileUpdate.getCreditRiskRating()).isPresent()) {
                            creditProfile.setCreditRiskRating(creditProfileUpdate.getCreditRiskRating());
                        }
                        if (Optional.ofNullable(creditProfileUpdate.getCreditScore()).isPresent()) {
                            creditProfile.setCreditScore(creditProfileUpdate.getCreditScore());
                        }
                        if (Optional.ofNullable(creditProfileUpdate.getValidFor()).isPresent()) {
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
                        if (Optional.ofNullable(paymentMethodUpdate.getName()).isPresent()) {
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
                        if (Optional.ofNullable(relatedPartyUpdate.getName()).isPresent()) {
                            relatedParty.setName(relatedPartyUpdate.getName());
                        }
                        if (Optional.ofNullable(relatedPartyUpdate.getRole()).isPresent()) {
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
                        if (Optional.ofNullable(contactMediumDtoUpdate.getPreferred()).isPresent()) {
                            contactMediumDto.setPreferred(contactMediumDtoUpdate.getPreferred());
                        }
                        if (Optional.ofNullable(contactMediumDtoUpdate.getMediumType()).isPresent()) {
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
                            if (Optional.ofNullable(mediumCharacteristicUpdate.getCity()).isPresent()) {
                                mediumCharacteristic.setCity(mediumCharacteristicUpdate.getCity());
                            }
                            if (Optional.ofNullable(mediumCharacteristicUpdate.getContactType()).isPresent()) {
                                mediumCharacteristic.setContactType(mediumCharacteristicUpdate.getContactType());
                            }
                            if (Optional.ofNullable(mediumCharacteristicUpdate.getCountry()).isPresent()) {
                                mediumCharacteristic.setCountry(mediumCharacteristicUpdate.getCountry());
                            }
                            if (Optional.ofNullable(mediumCharacteristicUpdate.getEmailAddress()).isPresent()) {
                                mediumCharacteristic.setEmailAddress(mediumCharacteristicUpdate.getEmailAddress());
                            }
                            if (Optional.ofNullable(mediumCharacteristicUpdate.getFaxNumber()).isPresent()) {
                                mediumCharacteristic.setFaxNumber(mediumCharacteristicUpdate.getFaxNumber());
                            }
                            if (Optional.ofNullable(mediumCharacteristicUpdate.getPhoneNumber()).isPresent()) {
                                mediumCharacteristic.setPhoneNumber(mediumCharacteristicUpdate.getPhoneNumber());
                            }
                            if (Optional.ofNullable(mediumCharacteristicUpdate.getPostCode()).isPresent()) {
                                mediumCharacteristic.setPostCode(mediumCharacteristicUpdate.getPostCode());
                            }
                            if (Optional.ofNullable(mediumCharacteristicUpdate.getSocialNetworkId()).isPresent()) {
                                mediumCharacteristic.setSocialNetworkId(mediumCharacteristicUpdate
                                        .getSocialNetworkId());
                            }
                            if (Optional.ofNullable(mediumCharacteristicUpdate.getStateOrProvince()).isPresent()) {
                                mediumCharacteristic.setStateOrProvince(mediumCharacteristicUpdate
                                        .getStateOrProvince());
                            }
                            if (Optional.ofNullable(mediumCharacteristicUpdate.getStreet1()).isPresent()) {
                                mediumCharacteristic.setStreet1(mediumCharacteristicUpdate.getStreet1());
                            }
                            if (Optional.ofNullable(mediumCharacteristicUpdate.getStreet2()).isPresent()) {
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
        repository.save(customerTransformer.transform(customerDto));
        customerDto.setCustomer(customerTransformer.transform(customerDto));
        this.producer.sendMessage(eventService.createEvent(customerDto, "CustomerAttributeValueChangeEvent")
                .toString());
        return customerDto;
    }
}
