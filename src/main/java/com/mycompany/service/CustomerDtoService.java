package com.mycompany.service;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.model.CustomerDto;
import com.mycompany.repository.*;
import com.mycompany.transfomer.CustomerTransformer;
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
                              CreditProfileService creditProfileService, CustomerTransformer customerTransformer) {
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
    }
    //////

    private void setData(CustomerDto customerDto) {
        engagedParty = customerDto.getEngagedParty();
        accounts = customerDto.getAccounts();
        relatedParties = customerDto.getRelatedParties();
        paymentMethods = customerDto.getPaymentMethods();
        characteristics = customerDto.getCharacteristics();
        agreements = customerDto.getAgreements();
        contactMediumDtoList = customerDto.getContactMediumDtoList();
        creditProfiles = customerDto.getCreditProfiles();
    }

    private CustomerDto getFullCustomer(Customer customer) {
        List<Account> accounts = accountService.findAllAccounts(customer);
        List<RelatedParty> relatedParties = relatedPartyService.findAllRelatedParties(customer);
        List<PaymentMethod> paymentMethods = paymentMethodService.findAllPaymentMethods(customer);
        List<Characteristic> characteristics = characteristicService.findAllCharacteristics(customer);
        List<Agreement> agreements = agreementService.findAllAgreements(customer);
        List<ContactMediumDto> contactMediumDtoList = contactMediumDtoService.getAllContactMediumDto(customer);
        List<CreditProfile> creditProfiles = creditProfileService.findAllCreditProfile(customer);


        EngagedParty engagedParty = engagedPartyService.findEngagedParty(customer);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomer(customer);
        customerDto.setEngagedParty(engagedParty);
        customerDto.setAccounts(accounts);
        customerDto.setRelatedParties(relatedParties);
        customerDto.setPaymentMethods(paymentMethods);
        customerDto.setCharacteristics(characteristics);
        customerDto.setAgreements(agreements);
        customerDto.setContactMediumDtoList(contactMediumDtoList);
        customerDto.setCreditProfiles(creditProfiles);

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

        if (engagedParty != null) {
            customer.setEngagedPartyId(engagedParty.getId());
        }



        List<Account> accountsUpdate = customerDtoUpdate.getAccounts();
        accounts = customerDto.getAccounts();

        if (accountsUpdate != null) {
            for (Account accountUpdate : accountsUpdate) {
                if (accountUpdate!= null)
                for (Account account : accounts) {
                    if (account.getId().equals(accountUpdate.getId())) {
                        if (account.getName()!=null){
                            account.setName(accountUpdate.getName());
                        }
                        if (account.getDescription()!=null){
                            account.setDescription(accountUpdate.getDescription());
                        }
                    }
                }

            }
            customerDto.setAccounts(accountService.update(accounts));

        }

        setAccountId(customer);


        List<RelatedParty> relatedPartiesUpdate = customerDtoUpdate.getRelatedParties();
        relatedParties = customerDto.getRelatedParties();

        if (relatedPartiesUpdate != null) {
            for (RelatedParty relatedPartyUpdate : relatedPartiesUpdate) {
                for (RelatedParty relatedParty : relatedParties) {
                    if (relatedParty.getId().equals(relatedPartyUpdate.getId())) {
                        if (relatedParty.getName()!=null){
                            relatedParty.setName(relatedPartyUpdate.getName());
                        }
                        if (relatedParty.getRole()!=null){
                            relatedParty.setRole(relatedPartyUpdate.getRole());
                        }
                    }
                }

            }
            customerDto.setRelatedParties(relatedPartyService.update(relatedParties));
        }


        int i = 0;
        if (relatedParties != null) {
            String[] idRelatedParties = new String[relatedParties.size()];
            for (RelatedParty relatedParty : relatedParties) {
                idRelatedParties[i] = relatedParty.getId();
                i++;
            }
            customer.setRelatedPartyId(idRelatedParties);
        }


        List<PaymentMethod> paymentMethodsUpdate = customerDtoUpdate.getPaymentMethods();
        paymentMethods = customerDto.getPaymentMethods();

        if (paymentMethodsUpdate != null) {
            for (PaymentMethod paymentMethodUpdate : paymentMethodsUpdate) {
                for (PaymentMethod paymentMethod : paymentMethods) {
                    if (paymentMethod.getId().equals(paymentMethodUpdate.getId())) {
                        if (paymentMethod.getName()!=null){
                            paymentMethod.setName(paymentMethodUpdate.getName());
                        }
                    }
                }

            }
            customerDto.setPaymentMethods(paymentMethodService.update(paymentMethods));
        }

        i = 0;
        System.out.println(paymentMethods.size());
        if (paymentMethods != null) {
            String[] idPaymentMethods = new String[paymentMethods.size()];
            for (PaymentMethod paymentMethod : paymentMethods) {
                idPaymentMethods[i] = paymentMethod.getId();
                i++;
                System.out.println(idPaymentMethods[i-1]);
            }
            customer.setPayMethodId(idPaymentMethods);
        }


        customerDto.setCustomer(customerService.update(customer));
        return customerDto;
    }

    public CustomerDto save(CustomerDto customerDto) {

        setData(customerDto);
        customerDto.setEngagedParty(engagedPartyService.save(engagedParty));
        customerDto.setAccounts(accountService.save(accounts));
        customerDto.setRelatedParties(relatedPartyService.save(relatedParties));
        customerDto.setPaymentMethods(paymentMethodService.save(paymentMethods));
        customerDto.setCharacteristics(characteristicService.save(characteristics));
        customerDto.setAgreements(agreementService.save(agreements));
        customerDto.setContactMediumDtoList(contactMediumDtoService.save(contactMediumDtoList));
        customerDto.setCreditProfiles(creditProfileService.save(creditProfiles));

        accounts = customerDto.getAccounts();
        relatedParties = customerDto.getRelatedParties();
        engagedParty = customerDto.getEngagedParty();
        paymentMethods = customerDto.getPaymentMethods();
        characteristics = customerDto.getCharacteristics();
        agreements = customerDto.getAgreements();
        contactMediumDtoList = customerDto.getContactMediumDtoList();
        creditProfiles = customerDto.getCreditProfiles();


        Customer customer = customerTransformer.transform(customerDto);
        if (engagedParty != null) {
            customer.setEngagedPartyId(engagedParty.getId());
        }

        setAccountId(customer);
        int i;

        if (relatedParties != null) {
            String[] idRelatedParties = new String[relatedParties.size()];
            i = 0;
            for (RelatedParty relatedParty : relatedParties) {
                idRelatedParties[i] = relatedParty.getId();
                i++;
            }
            customer.setRelatedPartyId(idRelatedParties);
        }

        if (paymentMethods != null) {
            String[] idPaymentMethods = new String[paymentMethods.size()];
            i = 0;
            for (PaymentMethod paymentMethod : paymentMethods) {
                idPaymentMethods[i] = paymentMethod.getId();
                i++;
            }
            customer.setPayMethodId(idPaymentMethods);
        }

        if (characteristics != null) {
            String[] idCharacteristics = new String[characteristics.size()];
            i = 0;
            for (Characteristic characteristic : characteristics) {
                idCharacteristics[i] = characteristic.getId();
                i++;
            }
            customer.setCharacteristicId(idCharacteristics);
        }

        if (agreements != null) {
            String[] idAgreements = new String[agreements.size()];
            i = 0;
            for (Agreement agreement : agreements) {
                idAgreements[i] = agreement.getId();
                i++;
            }
            customer.setAgreementId(idAgreements);
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

        if (creditProfiles != null) {
            String[] idCreditProfile = new String[creditProfiles.size()];
            i = 0;
            for (CreditProfile creditProfile : creditProfiles) {
                idCreditProfile[i] = creditProfile.getId();
                i++;
            }
            customer.setCreditProfileId(idCreditProfile);
        }

        customerDto.setCustomer(customerService.save(customer));
        return customerDto;
    }

    private void setAccountId(Customer customer) {
        int i = 0;
        if (accounts != null) {
            String[] idAccounts = new String[accounts.size()];
            for (Account account : accounts) {
                idAccounts[i] = account.getId();
                i++;
            }
            customer.setAccountId(idAccounts);
        }
    }

    public void delete(CustomerDto customerDto) {

        setData(customerDto);


        engagedPartyService.delete(engagedParty);

        for (Account account : accounts) {
            accountService.delete(account);
        }

        for (RelatedParty relatedParty : relatedParties) {
            relatedPartyService.delete(relatedParty);
        }

        for (PaymentMethod paymentMethod : paymentMethods) {
            paymentMethodService.delete(paymentMethod);
        }

        for (Characteristic characteristic : characteristics) {
            characteristicService.delete(characteristic);
        }

        for (Agreement agreement : agreements) {
            agreementService.delete(agreement);
        }


        for (ContactMediumDto contactMediumDto : contactMediumDtoList) {
            contactMediumDtoService.delete(contactMediumDto);
        }

        for (CreditProfile creditProfile : creditProfiles) {
            creditProfileService.delete(creditProfile);
        }

        customerService.delete(customerTransformer.transform(customerDto));
    }

}
