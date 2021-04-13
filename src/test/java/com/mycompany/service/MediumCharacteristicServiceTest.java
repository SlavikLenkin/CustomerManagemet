package com.mycompany.service;

import com.mycompany.model.MediumCharacteristicDto;
import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.MediumCharacteristicRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class MediumCharacteristicServiceTest {

    @MockBean
    MediumCharacteristicRepository mediumCharacteristicRepository;
    @MockBean
    ContactMediumService contactMediumService;

    @Autowired
    private MediumCharacteristicService mediumCharacteristicService;

    @Test
    void save() {
        ContactMedium contactMedium = new ContactMedium();
        MediumCharacteristicDto mediumCharacteristic = new MediumCharacteristicDto();
        mediumCharacteristic.setCity("city");

        MediumCharacteristicDto mediumCharacteristicDto = mediumCharacteristicService.save(mediumCharacteristic
                , contactMedium);

        Assert.assertNotNull(mediumCharacteristicDto.getId());
        Assert.assertNotNull(mediumCharacteristicDto.getContactMedium());
        Assert.assertNotNull(mediumCharacteristicDto.getCity());
        Assert.assertEquals("city", mediumCharacteristicDto.getCity());
    }

    @Test
    void update() {
        ContactMedium contactMedium = new ContactMedium();
        MediumCharacteristicDto mediumCharacteristic = new MediumCharacteristicDto();
        mediumCharacteristic.setCity("city");
        mediumCharacteristic.setContactMedium(contactMedium);
        mediumCharacteristic.setId("id");

        MediumCharacteristicDto mediumCharacteristicUpdate = new MediumCharacteristicDto();
        mediumCharacteristicUpdate.setCity("new city");
        mediumCharacteristic.setCity(mediumCharacteristicUpdate.getCity());

        MediumCharacteristicDto mediumCharacteristicDto = mediumCharacteristicService.update(mediumCharacteristic);

        Assert.assertNotNull(mediumCharacteristicDto.getId());
        Assert.assertNotNull(mediumCharacteristicDto.getContactMedium());
        Assert.assertNotNull(mediumCharacteristicDto.getCity());
        Assert.assertEquals("new city", mediumCharacteristicDto.getCity());
    }
}

