package com.mycompany.service;

import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.MediumCharacteristic;
import com.mycompany.repository.MediumCharacteristicRepository;
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
class MediumCharacteristicServiceTest {

    @MockBean
    MediumCharacteristicRepository mediumCharacteristicRepository;

    @Autowired
    private MediumCharacteristicService mediumCharacteristicService;

    @Test
    void save() {
        ContactMedium contactMedium = new ContactMedium();
        MediumCharacteristic mediumCharacteristic = new MediumCharacteristic();
        mediumCharacteristic.setCity("city");
        mediumCharacteristic.setContactMedium(contactMedium);

        MediumCharacteristic mediumCharacteristic1 = mediumCharacteristicService.save(mediumCharacteristic
                , contactMedium);

        Assert.assertNotNull(mediumCharacteristic1.getId());
        Assert.assertNotNull(mediumCharacteristic1.getContactMedium());
        Assert.assertNotNull(mediumCharacteristic1.getCity());
        Assert.assertEquals("city", mediumCharacteristic1.getCity());
        Mockito.verify(mediumCharacteristicRepository).save(mediumCharacteristic1);

    }

    @Test
    void update() {
        ContactMedium contactMedium = new ContactMedium();
        MediumCharacteristic mediumCharacteristic = new MediumCharacteristic();
        mediumCharacteristic.setCity("city");
        mediumCharacteristic.setContactMedium(contactMedium);
        mediumCharacteristic.setId("id");

        MediumCharacteristic mediumCharacteristicUpdate = new MediumCharacteristic();
        mediumCharacteristicUpdate.setCity("new city");
        mediumCharacteristic.setCity(mediumCharacteristicUpdate.getCity());

        MediumCharacteristic mediumCharacteristic2 = mediumCharacteristicService.update(mediumCharacteristic);

        Assert.assertNotNull(mediumCharacteristic2.getId());
        Assert.assertNotNull(mediumCharacteristic2.getContactMedium());
        Assert.assertNotNull(mediumCharacteristic2.getCity());
        Assert.assertEquals("new city", mediumCharacteristic2.getCity());
        Mockito.verify(mediumCharacteristicRepository).save(mediumCharacteristic2);
    }
}