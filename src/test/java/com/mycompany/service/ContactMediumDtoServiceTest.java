package com.mycompany.service;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.repository.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

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
        contactMedium.setId("id");
        contactMedium.setCustomer(customer);

        MediumCharacteristic mediumCharacteristic = new MediumCharacteristic();
        mediumCharacteristic.setCity("city");
        mediumCharacteristic.setId("id");

        ContactMediumDto contactMediumDto = new ContactMediumDto();
        contactMediumDto.setCustomer(customer);
        contactMediumDto.setContactMedium(contactMedium);
        contactMediumDto.setMediumCharacteristic(mediumCharacteristic);
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        contactMediumDtoList.add(contactMediumDto);

        ContactMedium contactMediumUpdate = new ContactMedium();
        contactMediumUpdate.setMediumType("new type");

        ContactMediumDto contactMediumDtoUpdate = new ContactMediumDto();
        contactMediumDtoUpdate.setMediumType(contactMediumUpdate.getMediumType());
        contactMediumDtoList.get(0).setMediumType(contactMediumDtoUpdate.getMediumType());
        List<ContactMediumDto> contactMediumDtoList2 = contactMediumDtoService.update(contactMediumDtoList, customer);

        for (ContactMediumDto contactMediumDtoI : contactMediumDtoList2) {
            Assert.assertNotNull(contactMediumDtoI.getId());
            Assert.assertNotNull(contactMediumDtoI.getCustomer());
            Assert.assertNotNull(contactMediumDtoI.getMediumCharacteristic());
            Assert.assertEquals("new type", contactMediumDtoI.getMediumType());
        }
    }
}