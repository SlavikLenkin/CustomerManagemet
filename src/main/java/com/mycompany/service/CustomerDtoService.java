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
    /*  private final ContactMediumService contactMediumService;*/
    private final ContactMediumDtoService contactMediumService;


    ////// Можно ли так????
    private CustomerTransformer transformer;
    private EngagedParty engagedParty;
    private List<Account> accounts;
    private List<RelatedParty> relatedParties;
    private List<PaymentMethod> paymentMethods;
    private List<Characteristic> characteristics;
    private List<Agreement> agreements;
    /* private List<ContactMedium> contactMediumList;*/
    private List<ContactMediumDto> contactMediumDtoList;


    private void setData(CustomerDto customerDto) {
        transformer = new CustomerTransformer();
        engagedParty = customerDto.getEngagedParty();
        accounts = customerDto.getAccounts();
        relatedParties = customerDto.getRelatedParties();
        paymentMethods = customerDto.getPaymentMethods();
        characteristics = customerDto.getCharacteristics();
        agreements = customerDto.getAgreements();
        contactMediumDtoList = customerDto.getContactMediumDtoList();
    }
    //////

    public CustomerDtoService(CustomerService customerService, AccountService accountService,
                              EngagedPartyService engagedPartyService, RelatedPartyService relatedPartyService,
                              PaymentMethodService paymentMethodService, CharacteristicService characteristicService, AgreementService agreementService/*, ContactMediumService contactMediumService*/, ContactMediumDtoService contactMediumService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.engagedPartyService = engagedPartyService;
        this.relatedPartyService = relatedPartyService;
        this.paymentMethodService = paymentMethodService;
        this.characteristicService = characteristicService;
        this.agreementService = agreementService;

        this.contactMediumService = contactMediumService;
    }

    private CustomerDto getFullCustomer(Customer customer) {
        List<Account> accounts = accountService.findAllAccounts(customer);
        List<RelatedParty> relatedParties = relatedPartyService.findAllRelatedParties(customer);
        List<PaymentMethod> paymentMethods = paymentMethodService.findAllPaymentMethods(customer);
        List<Characteristic> characteristics = characteristicService.findAllCharacteristics(customer);
        List<Agreement> agreements = agreementService.findAllAgreements(customer);
        List<ContactMediumDto> contactMediumDtoList = contactMediumService.getAllContactMediumDto(customer);


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
        /*CustomerTransformer transformer = new CustomerTransformer();

        EngagedParty engagedParty = customerDto.getEngagedParty();
        List<Account> accounts = customerDto.getAccounts();
        List<RelatedParty> relatedParties = customerDto.getRelatedParties();
        List<PaymentMethod> paymentMethods = customerDto.getPaymentMethods();
        List<Characteristic> characteristics = customerDto.getCharacteristics();*/

        setData(customerDto);

        customerDto.setEngagedParty(engagedPartyService.save(engagedParty));
        customerDto.setAccounts(accountService.save(accounts));
        customerDto.setRelatedParties(relatedPartyService.save(relatedParties));
        customerDto.setPaymentMethods(paymentMethodService.save(paymentMethods));
        customerDto.setCharacteristics(characteristicService.save(characteristics));
        customerDto.setAgreements(agreementService.save(agreements));
        customerDto.setContactMediumDtoList(contactMediumService.save(contactMediumDtoList));


        accounts = customerDto.getAccounts();
        relatedParties = customerDto.getRelatedParties();
        engagedParty = customerDto.getEngagedParty();
        paymentMethods = customerDto.getPaymentMethods();
        characteristics = customerDto.getCharacteristics();
        agreements = customerDto.getAgreements();
        contactMediumDtoList = customerDto.getContactMediumDtoList();

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
                System.out.println(contactMediumDto.getMediumType());
                System.out.println(contactMediumDto.getId());
                idContactMedium[i] = contactMediumDto.getId();
                i++;
            }
            customer.setContactMediumId(idContactMedium);
        }

        customerDto.setCustomer(customerService.save(customer));
        return customerDto;
    }

    public void delete(CustomerDto customerDto) {
        /*CustomerTransformer transformer = new CustomerTransformer();

        EngagedParty engagedParty = customerDto.getEngagedParty();
        List<Account> accounts = customerDto.getAccounts();
        List<RelatedParty> relatedParties = customerDto.getRelatedParties();
        List<PaymentMethod> paymentMethods = customerDto.getPaymentMethods();
        List<Characteristic> characteristics = customerDto.getCharacteristics();*/

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

      /*  for (ContactMedium contactMedium : contactMediumList){
            contactMediumService.delete(contactMedium);
        }*/

        customerService.delete(transformer.transform(customerDto));
    }

}
