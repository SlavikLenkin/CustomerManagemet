package com.mycompany.service;

import com.mycompany.repository.Characteristic;
import com.mycompany.repository.CharacteristicRepository;
import com.mycompany.repository.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
        List<Characteristic> characteristics = new ArrayList<>();
        Characteristic characteristic = new Characteristic();
        characteristic.setName("characteristic");
        characteristic.setCustomer(customer);

        characteristics.add(characteristic);

        List<Characteristic> characteristics1 = characteristicService.save(characteristics, customer);

        for (Characteristic characteristicI : characteristics1) {
            Assert.assertNotNull(characteristicI.getId());
            Assert.assertNotNull(characteristicI.getName());
            Assert.assertEquals("characteristic", characteristicI.getName());
            Mockito.verify(characteristicRepository).save(characteristicI);
        }
    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<Characteristic> characteristics = new ArrayList<>();
        Characteristic characteristic = new Characteristic();
        characteristic.setName("characteristic");
        characteristic.setId("id");
        characteristic.setCustomer(customer);

        characteristics.add(characteristic);

        List<Characteristic> characteristicsUpdate = new ArrayList<>();
        Characteristic characteristicUpdate = new Characteristic();
        characteristicUpdate.setName("new characteristic");
        characteristicsUpdate.add(characteristicUpdate);
        characteristics.get(0).setName(characteristicsUpdate.get(0).getName());

        List<Characteristic> characteristics2 = characteristicService.update(characteristics);

        for (Characteristic characteristicI : characteristics2) {
            Assert.assertNotNull(characteristicI.getId());
            Assert.assertNotNull(characteristicI.getName());
            Assert.assertEquals("new characteristic", characteristicI.getName());
            Mockito.verify(characteristicRepository).save(characteristicI);
        }

    }

}