package com.mycompany.service;

import com.mycompany.model.AgreementDto;
import com.mycompany.repository.Agreement;
import com.mycompany.repository.AgreementRepository;
import com.mycompany.repository.Customer;
import com.mycompany.transfomer.AgreementTransformer;
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
class AgreementServiceTest {

    @Mock
    AgreementRepository agreementRepository;

    @Mock
    AgreementTransformer agreementTransformer;

    @InjectMocks
    private AgreementService agreementService;

    @Test
    void save() {
        Customer customer = new Customer();
        Agreement agreement = new Agreement();
        List<AgreementDto> agreementsDto = new ArrayList<>();
        AgreementDto agreementDto = new AgreementDto();

        agreementsDto.add(agreementDto);

        Mockito.when(agreementTransformer.transform(agreementDto)).thenReturn(agreement);
        Mockito.when(agreementTransformer.transform(agreement)).thenReturn(agreementDto);
        Mockito.when(agreementRepository.save(agreement)).thenReturn(agreement);

        agreementService.save(agreementsDto, customer);

        Mockito.verify(agreementTransformer).transform(agreementDto);
        Mockito.verify(agreementTransformer).transform(agreement);
        Mockito.verify(agreementRepository).save(agreement);
    }

    @Test
    void update() {
        Agreement agreement = new Agreement();
        List<AgreementDto> agreementsDto = new ArrayList<>();
        AgreementDto agreementDto = new AgreementDto();
        agreementsDto.add(agreementDto);

        Mockito.when(agreementTransformer.transform(agreementDto)).thenReturn(agreement);
        Mockito.when(agreementRepository.save(agreement)).thenReturn(agreement);

        agreementService.update(agreementsDto);

        Mockito.verify(agreementTransformer).transform(agreementDto);
        Mockito.verify(agreementRepository).save(agreement);
    }
}
