package com.mycompany.service;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.repository.*;
import com.mycompany.transfomer.ContactMediumTransformer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ContactMediumDtoServiceTest {

    @MockBean
    ContactMediumRepository contactMediumRepository;
    @MockBean
    MediumCharacteristicRepository mediumCharacteristicRepository;

    @Autowired
    private ContactMediumDtoService contactMediumDtoService;


    @Test
    void save() {
        Customer customer = new Customer();
        ContactMedium contactMedium = new ContactMedium();
        contactMedium.setMediumType("type");

        MediumCharacteristic mediumCharacteristic = new MediumCharacteristic();
        mediumCharacteristic.setCity("city");


        ContactMediumDto contactMediumDto = new ContactMediumDto();
        contactMediumDto.setContactMedium(contactMedium);
        contactMediumDto.setMediumCharacteristic(mediumCharacteristic);
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        contactMediumDtoList.add(contactMediumDto);

        List<ContactMediumDto> contactMediumDtoList1 = contactMediumDtoService.save(contactMediumDtoList, customer);

        for (ContactMediumDto contactMediumDtoI : contactMediumDtoList1) {
            Assert.assertNotNull(contactMediumDtoI.getId());
            Assert.assertNotNull(contactMediumDtoI.getCustomer());
            Assert.assertNotNull(contactMediumDtoI.getMediumCharacteristic());
            Assert.assertEquals("type", contactMediumDtoI.getMediumType());
        }


    }

    @Test
    void update() {
        Customer customer = new Customer();
        ContactMedium contactMedium = new ContactMedium();
        contactMedium.setMediumType("type");

        MediumCharacteristic mediumCharacteristic = new MediumCharacteristic();
        mediumCharacteristic.setCity("city");


        ContactMediumDto contactMediumDto = new ContactMediumDto();
        contactMediumDto.setContactMedium(contactMedium);
        contactMediumDto.setMediumCharacteristic(mediumCharacteristic);
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        contactMediumDtoList.add(contactMediumDto);

        List<ContactMediumDto> contactMediumDtoList1 = contactMediumDtoService.save(contactMediumDtoList, customer);


        ContactMedium contactMediumUpdate = new ContactMedium();
        contactMediumUpdate.setMediumType("new type");


        ContactMediumDto contactMediumDtoUpdate = new ContactMediumDto();
        contactMediumDtoUpdate.setMediumType(contactMediumUpdate.getMediumType());
        contactMediumDtoList.get(0).setMediumType(contactMediumDtoUpdate.getMediumType());
        List<ContactMediumDto> contactMediumDtoList2 = contactMediumDtoService.update(contactMediumDtoList1, customer);

        for (ContactMediumDto contactMediumDtoI : contactMediumDtoList2) {
            Assert.assertNotNull(contactMediumDtoI.getId());
            Assert.assertNotNull(contactMediumDtoI.getCustomer());
            Assert.assertNotNull(contactMediumDtoI.getMediumCharacteristic());
            Assert.assertEquals("new type", contactMediumDtoI.getMediumType());
        }

    }
}