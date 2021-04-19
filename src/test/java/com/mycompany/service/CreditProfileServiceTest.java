package com.mycompany.service;

import com.mycompany.model.CreditProfileDto;
import com.mycompany.repository.CreditProfile;
import com.mycompany.repository.CreditProfileRepository;
import com.mycompany.repository.Customer;
import com.mycompany.transfomer.CreditProfileTransformer;
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
class CreditProfileServiceTest {

    @Mock
    CreditProfileRepository creditProfileRepository;

    @Mock
    CreditProfileTransformer creditProfileTransformer;

    @InjectMocks
    private CreditProfileService creditProfileService;

    @Test
    void save() {
        Customer customer = new Customer();
        CreditProfile creditProfile = new CreditProfile();
        List<CreditProfileDto> creditProfilesDto = new ArrayList<>();
        CreditProfileDto creditProfileDto = new CreditProfileDto();
        creditProfilesDto.add(creditProfileDto);

        Mockito.when(creditProfileTransformer.transform(creditProfileDto)).thenReturn(creditProfile);
        Mockito.when(creditProfileTransformer.transform(creditProfile)).thenReturn(creditProfileDto);
        Mockito.when(creditProfileRepository.save(creditProfile)).thenReturn(creditProfile);

        creditProfileService.save(creditProfilesDto, customer);

        Mockito.verify(creditProfileTransformer).transform(creditProfileDto);
        Mockito.verify(creditProfileTransformer).transform(creditProfile);
        Mockito.verify(creditProfileRepository).save(creditProfile);

    }

    @Test
    void update() {
        CreditProfile creditProfile = new CreditProfile();
        List<CreditProfileDto> creditProfilesDto = new ArrayList<>();
        CreditProfileDto creditProfileDto = new CreditProfileDto();
        creditProfilesDto.add(creditProfileDto);

        Mockito.when(creditProfileTransformer.transform(creditProfileDto)).thenReturn(creditProfile);
        Mockito.when(creditProfileRepository.save(creditProfile)).thenReturn(creditProfile);

        creditProfileService.update(creditProfilesDto);

        Mockito.verify(creditProfileTransformer).transform(creditProfileDto);
        Mockito.verify(creditProfileRepository).save(creditProfile);
    }
}
