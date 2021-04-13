package com.mycompany.service;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.repository.ContactMediumRepository;
import com.mycompany.repository.Customer;
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
class ContactMediumServiceTest {

    @MockBean
    ContactMediumRepository contactMediumRepository;
    @MockBean
    MediumCharacteristicService mediumCharacteristicService;

    @Autowired
    private ContactMediumService contactMediumService;


    @Test
    void save() {
        Customer customer = new Customer();
        ContactMediumDto contactMediumDto = new ContactMediumDto();
        contactMediumDto.setMediumType("type");
        contactMediumDto.setCustomer(customer);
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        contactMediumDtoList.add(contactMediumDto);

        List<ContactMediumDto> contactMediumTest = contactMediumService.save(contactMediumDtoList, customer);

        Assert.assertNotNull(contactMediumTest.get(0).getId());
        Assert.assertNotNull(contactMediumTest.get(0).getCustomer());
        Assert.assertNotNull(contactMediumTest.get(0).getMediumType());
        Assert.assertEquals("type", contactMediumTest.get(0).getMediumType());
    }

    @Test
    void update() {
        Customer customer = new Customer();
        ContactMediumDto contactMediumDto = new ContactMediumDto();
        contactMediumDto.setId("id");
        contactMediumDto.setMediumType("type");
        contactMediumDto.setCustomer(customer);
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        contactMediumDtoList.add(contactMediumDto);

        ContactMediumDto contactMediumUpdate = new ContactMediumDto();
        contactMediumUpdate.setMediumType("new type");
        contactMediumDto.setMediumType(contactMediumUpdate.getMediumType());
        List<ContactMediumDto> contactMediumDtoListUpdate = new ArrayList<>();
        contactMediumDtoListUpdate.add(contactMediumUpdate);
        contactMediumDtoList.get(0).setMediumType(contactMediumDtoListUpdate.get(0).getMediumType());

        List<ContactMediumDto> contactMediumTest = contactMediumService.update(contactMediumDtoList, customer);

        Assert.assertNotNull(contactMediumTest.get(0).getId());
        Assert.assertNotNull(contactMediumTest.get(0).getCustomer());
        Assert.assertNotNull(contactMediumTest.get(0).getMediumType());
        Assert.assertEquals("new type", contactMediumTest.get(0).getMediumType());
    }
}