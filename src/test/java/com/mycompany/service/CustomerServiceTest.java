package com.mycompany.service;

import com.mycompany.model.*;
import com.mycompany.repository.Customer;
import com.mycompany.repository.CustomerRepository;
import com.mycompany.transfomer.CustomerTransformer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerTransformer customerTransformer;

    @Mock
    EngagedPartyService engagedPartyService;

    @Mock
    AccountService accountService;

    @Mock
    AgreementService agreementService;

    @Mock
    CharacteristicService characteristicService;

    @Mock
    CreditProfileService creditProfileService;

    @Mock
    PaymentMethodService paymentMethodService;

    @Mock
    RelatedPartyService relatedPartyService;

    @Mock
    ContactMediumService contactMediumService;

    @InjectMocks
    CustomerService customerService;

    @Test
    void save() {
        Customer customer = new Customer();
        EngagedPartyDto engagedPartyDto = new EngagedPartyDto();
        List<AccountDto> accountDto = new ArrayList<>();
        List<AgreementDto> agreementDtoList = new ArrayList<>();
        List<CharacteristicDto> characteristicDtoList = new ArrayList<>();
        List<CreditProfileDto> creditProfileDtoList = new ArrayList<>();
        List<PaymentMethodDto> paymentMethodDtoList = new ArrayList<>();
        List<RelatedPartyDto> relatedPartyDtoList = new ArrayList<>();
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEngagedParty(engagedPartyDto);
        customerDto.setAccounts(accountDto);
        customerDto.setAgreements(agreementDtoList);
        customerDto.setCharacteristics(characteristicDtoList);
        customerDto.setCreditProfiles(creditProfileDtoList);
        customerDto.setPaymentMethods(paymentMethodDtoList);
        customerDto.setRelatedParties(relatedPartyDtoList);
        customerDto.setContactMediumDtoList(contactMediumDtoList);

        Mockito.when(customerTransformer.transform(customerDto)).thenReturn(customer);
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Mockito.when(engagedPartyService.save(engagedPartyDto, customer)).thenReturn(engagedPartyDto);
        Mockito.when(accountService.save(accountDto, customer)).thenReturn(accountDto);
        Mockito.when(agreementService.save(agreementDtoList, customer)).thenReturn(agreementDtoList);
        Mockito.when(characteristicService.save(characteristicDtoList, customer)).thenReturn(characteristicDtoList);
        Mockito.when(creditProfileService.save(creditProfileDtoList, customer)).thenReturn(creditProfileDtoList);
        Mockito.when(paymentMethodService.save(paymentMethodDtoList, customer)).thenReturn(paymentMethodDtoList);
        Mockito.when(relatedPartyService.save(relatedPartyDtoList, customer)).thenReturn(relatedPartyDtoList);
        Mockito.when(contactMediumService.save(contactMediumDtoList, customer)).thenReturn(contactMediumDtoList);

        customerService.save(customerDto);

        Mockito.verify(customerTransformer).transform(customerDto);
        Mockito.verify(customerRepository).save(customer);
        Mockito.verify(engagedPartyService).save(engagedPartyDto, customer);
        Mockito.verify(accountService).save(accountDto, customer);
        Mockito.verify(agreementService).save(agreementDtoList, customer);
        Mockito.verify(characteristicService).save(characteristicDtoList, customer);
        Mockito.verify(creditProfileService).save(creditProfileDtoList, customer);
        Mockito.verify(paymentMethodService).save(paymentMethodDtoList, customer);
        Mockito.verify(relatedPartyService).save(relatedPartyDtoList, customer);
        Mockito.verify(contactMediumService).save(contactMediumDtoList, customer);
    }

    @Test
    void update() {
        Customer customer = new Customer();
        EngagedPartyDto engagedPartyDto = new EngagedPartyDto();
        List<AccountDto> accountDto = new ArrayList<>();
        List<AgreementDto> agreementDtoList = new ArrayList<>();
        List<CharacteristicDto> characteristicDtoList = new ArrayList<>();
        List<CreditProfileDto> creditProfileDtoList = new ArrayList<>();
        List<PaymentMethodDto> paymentMethodDtoList = new ArrayList<>();
        List<RelatedPartyDto> relatedPartyDtoList = new ArrayList<>();
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEngagedParty(engagedPartyDto);
        customerDto.setAccounts(accountDto);
        customerDto.setAgreements(agreementDtoList);
        customerDto.setCharacteristics(characteristicDtoList);
        customerDto.setCreditProfiles(creditProfileDtoList);
        customerDto.setPaymentMethods(paymentMethodDtoList);
        customerDto.setRelatedParties(relatedPartyDtoList);
        customerDto.setContactMediumDtoList(contactMediumDtoList);
        String id = "id";

        Mockito.when(customerTransformer.transform(customerDto)).thenReturn(customer);
        Mockito.when(engagedPartyService.update(engagedPartyDto)).thenReturn(engagedPartyDto);
        Mockito.when(accountService.update(accountDto)).thenReturn(accountDto);
        Mockito.when(agreementService.update(agreementDtoList)).thenReturn(agreementDtoList);
        Mockito.when(characteristicService.update(characteristicDtoList)).thenReturn(characteristicDtoList);
        Mockito.when(creditProfileService.update(creditProfileDtoList)).thenReturn(creditProfileDtoList);
        Mockito.when(paymentMethodService.update(paymentMethodDtoList)).thenReturn(paymentMethodDtoList);
        Mockito.when(relatedPartyService.update(relatedPartyDtoList)).thenReturn(relatedPartyDtoList);
        Mockito.when(contactMediumService.update(contactMediumDtoList, customer)).thenReturn(contactMediumDtoList);
        Mockito.when(customerService.findCustomerById(id)).thenReturn(customerDto);

        customerService.update(id, customerDto);

        Mockito.verify(customerTransformer, Mockito.times(3)).transform(customerDto);
        Mockito.verify(customerRepository).updateCustomerById(customer);
        Mockito.verify(engagedPartyService).update(engagedPartyDto);
        Mockito.verify(accountService).update(accountDto);
        Mockito.verify(agreementService).update(agreementDtoList);
        Mockito.verify(characteristicService).update(characteristicDtoList);
        Mockito.verify(creditProfileService).update(creditProfileDtoList);
        Mockito.verify(paymentMethodService).update(paymentMethodDtoList);
        Mockito.verify(relatedPartyService).update(relatedPartyDtoList);
        Mockito.verify(contactMediumService).update(contactMediumDtoList, customer);
    }
}