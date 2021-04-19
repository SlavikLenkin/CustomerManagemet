package com.mycompany.service;

import com.mycompany.model.CharacteristicDto;
import com.mycompany.repository.Characteristic;
import com.mycompany.repository.CharacteristicRepository;
import com.mycompany.repository.Customer;
import com.mycompany.transfomer.CharacteristicTransformer;
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
class CharacteristicServiceTest {

    @Mock
    CharacteristicRepository characteristicRepository;

    @Mock
    CharacteristicTransformer characteristicTransformer;

    @InjectMocks
    private CharacteristicService characteristicService;

    @Test
    void save() {
        Customer customer = new Customer();
        Characteristic characteristic = new Characteristic();
        List<CharacteristicDto> characteristicsDto = new ArrayList<>();
        CharacteristicDto characteristicDto = new CharacteristicDto();
        characteristicsDto.add(characteristicDto);

        Mockito.when(characteristicTransformer.transform(characteristicDto)).thenReturn(characteristic);
        Mockito.when(characteristicTransformer.transform(characteristic)).thenReturn(characteristicDto);
        Mockito.when(characteristicRepository.save(characteristic)).thenReturn(characteristic);

        characteristicService.save(characteristicsDto, customer);

        Mockito.verify(characteristicTransformer).transform(characteristicDto);
        Mockito.verify(characteristicTransformer).transform(characteristic);
        Mockito.verify(characteristicRepository).save(characteristic);
    }

    @Test
    void update() {
        Characteristic characteristic = new Characteristic();
        List<CharacteristicDto> characteristicsDto = new ArrayList<>();
        CharacteristicDto characteristicDto = new CharacteristicDto();
        characteristicsDto.add(characteristicDto);

        Mockito.when(characteristicTransformer.transform(characteristicDto)).thenReturn(characteristic);
        Mockito.when(characteristicRepository.save(characteristic)).thenReturn(characteristic);

        characteristicService.update(characteristicsDto);

        Mockito.verify(characteristicTransformer).transform(characteristicDto);
        Mockito.verify(characteristicRepository).save(characteristic);
    }
}
