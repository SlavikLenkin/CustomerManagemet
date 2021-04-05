package com.mycompany.service;

import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.ContactMediumRepository;
import com.mycompany.repository.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ContactMediumServiceTest {

    @MockBean
    ContactMediumRepository contactMediumRepository;

    @Autowired
    private ContactMediumService contactMediumService;


    @Test
    void saveOne() {
        Customer customer = new Customer();
        ContactMedium contactMedium = new ContactMedium();
        contactMedium.setMediumType("type");
        contactMedium.setCustomer(customer);

        ContactMedium contactMedium1 = contactMediumService.saveOne(contactMedium, customer);

        Assert.assertNotNull(contactMedium1.getId());
        Assert.assertNotNull(contactMedium1.getCustomer());
        Assert.assertNotNull(contactMedium1.getMediumType());
        Assert.assertEquals("type", contactMedium1.getMediumType());
        Mockito.verify(contactMediumRepository, Mockito.times(1)).save(contactMedium1);

    }

    @Test
    void updateOne() {
        Customer customer = new Customer();
        ContactMedium contactMedium = new ContactMedium();
        contactMedium.setMediumType("type");
        contactMedium.setCustomer(customer);

        ContactMedium contactMedium1 = contactMediumService.saveOne(contactMedium, customer);

        ContactMedium contactMediumUpdate = new ContactMedium();
        contactMediumUpdate.setMediumType("new type");
        contactMedium1.setMediumType(contactMediumUpdate.getMediumType());

        ContactMedium contactMedium2 = contactMediumService.updateOne(contactMedium1);

        Assert.assertNotNull(contactMedium1.getId());
        Assert.assertNotNull(contactMedium1.getCustomer());
        Assert.assertNotNull(contactMedium1.getMediumType());
        Assert.assertEquals("new type", contactMedium1.getMediumType());
        Mockito.verify(contactMediumRepository, Mockito.times(2)).save(contactMedium2);

    }
}