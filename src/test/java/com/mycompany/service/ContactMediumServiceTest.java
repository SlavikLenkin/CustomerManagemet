package com.mycompany.service;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.ContactMediumRepository;
import com.mycompany.repository.Customer;
import com.mycompany.transfomer.ContactMediumTransformer;
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
class ContactMediumServiceTest {

    @Mock
    ContactMediumRepository contactMediumRepository;
    @Mock
    ContactMediumTransformer contactMediumTransformer;
    @Mock
    MediumCharacteristicService mediumCharacteristicService;

    @InjectMocks
    private ContactMediumService contactMediumService;

    @Test
    void save() {
        Customer customer = new Customer();
        ContactMedium contactMedium = new ContactMedium();
        ContactMediumDto contactMediumDto = new ContactMediumDto();
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        contactMediumDtoList.add(contactMediumDto);

        Mockito.when(contactMediumTransformer.transform(contactMediumDto)).thenReturn(contactMedium);
        Mockito.when(contactMediumRepository.save(contactMedium)).thenReturn(contactMedium);

        contactMediumService.save(contactMediumDtoList, customer);

        Mockito.verify(contactMediumTransformer).transform(contactMediumDto);
        Mockito.verify(contactMediumRepository).save(contactMedium);
    }

    @Test
    void update() {
        Customer customer = new Customer();
        ContactMedium contactMedium = new ContactMedium();
        ContactMediumDto contactMediumDto = new ContactMediumDto();
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        contactMediumDtoList.add(contactMediumDto);

        Mockito.when(contactMediumTransformer.transform(contactMediumDto)).thenReturn(contactMedium);
        Mockito.when(contactMediumRepository.save(contactMedium)).thenReturn(contactMedium);

        contactMediumService.update(contactMediumDtoList, customer);

        Mockito.verify(contactMediumTransformer).transform(contactMediumDto);
        Mockito.verify(contactMediumRepository).save(contactMedium);
    }
}