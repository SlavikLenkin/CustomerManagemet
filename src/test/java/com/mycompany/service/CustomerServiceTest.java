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
    }
}