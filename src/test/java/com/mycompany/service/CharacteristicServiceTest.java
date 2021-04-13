package com.mycompany.service;

import com.mycompany.model.CharacteristicDto;
import com.mycompany.repository.CharacteristicRepository;
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
class CharacteristicServiceTest {

    @MockBean
    CharacteristicRepository characteristicRepository;

    @Autowired
    private CharacteristicService characteristicService;

    @Test
    void save() {
        Customer customer = new Customer();
        List<CharacteristicDto> characteristicsDto = new ArrayList<>();
        CharacteristicDto characteristicDto = new CharacteristicDto();
        characteristicDto.setName("characteristic");
        characteristicDto.setCustomer(customer);

        characteristicsDto.add(characteristicDto);

        List<CharacteristicDto> characteristicsTest = characteristicService.save(characteristicsDto, customer);

        for (CharacteristicDto characteristicI : characteristicsTest) {
            Assert.assertNotNull(characteristicI.getId());
            Assert.assertNotNull(characteristicI.getName());
            Assert.assertEquals("characteristic", characteristicI.getName());
        }
    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<CharacteristicDto> characteristicsDto = new ArrayList<>();
        CharacteristicDto characteristicDto = new CharacteristicDto();
        characteristicDto.setName("characteristic");
        characteristicDto.setId("id");
        characteristicDto.setCustomer(customer);

        characteristicsDto.add(characteristicDto);

        List<CharacteristicDto> characteristicsUpdate = new ArrayList<>();
        CharacteristicDto characteristicUpdate = new CharacteristicDto();
        characteristicUpdate.setName("new characteristic");
        characteristicsUpdate.add(characteristicUpdate);
        characteristicsDto.get(0).setName(characteristicsUpdate.get(0).getName());

        List<CharacteristicDto> characteristics2 = characteristicService.update(characteristicsDto);

        for (CharacteristicDto characteristicI : characteristics2) {
            Assert.assertNotNull(characteristicI.getId());
            Assert.assertNotNull(characteristicI.getName());
            Assert.assertEquals("new characteristic", characteristicI.getName());
        }

    }

}
