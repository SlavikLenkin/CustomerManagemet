package com.mycompany.service;

import com.mycompany.model.MediumCharacteristicDto;
import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.MediumCharacteristic;
import com.mycompany.repository.MediumCharacteristicRepository;
import com.mycompany.transfomer.MediumCharacteristicTransformer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class MediumCharacteristicServiceTest {

    @Mock
    MediumCharacteristicRepository mediumCharacteristicRepository;
    @Mock
    MediumCharacteristicTransformer mediumCharacteristicTransformer;
    @Mock
    ContactMediumService contactMediumService;

    @InjectMocks
    private MediumCharacteristicService mediumCharacteristicService;

    @Test
    void save() {
        ContactMedium contactMedium = new ContactMedium();
        MediumCharacteristic mediumCharacteristic = new MediumCharacteristic();
        MediumCharacteristicDto mediumCharacteristicDto = new MediumCharacteristicDto();

        Mockito.when(mediumCharacteristicTransformer.transform(mediumCharacteristicDto))
                .thenReturn(mediumCharacteristic);
        Mockito.when(mediumCharacteristicTransformer.transform(mediumCharacteristic))
                .thenReturn(mediumCharacteristicDto);
        Mockito.when(mediumCharacteristicRepository.save(mediumCharacteristic))
                .thenReturn(mediumCharacteristic);

        mediumCharacteristicService.save(mediumCharacteristicDto
                , contactMedium);

        Mockito.verify(mediumCharacteristicTransformer).transform(mediumCharacteristicDto);
        Mockito.verify(mediumCharacteristicTransformer).transform(mediumCharacteristic);
        Mockito.verify(mediumCharacteristicRepository).save(mediumCharacteristic);
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

