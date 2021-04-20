package com.mycompany.service;

import com.mycompany.model.CharacteristicDto;
import com.mycompany.repository.Characteristic;
import com.mycompany.repository.CharacteristicRepository;
import com.mycompany.repository.Customer;
import com.mycompany.transfomer.CharacteristicTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CharacteristicService {

    final CharacteristicRepository repository;
    final CharacteristicTransformer characteristicTransformer;

    public CharacteristicService(CharacteristicRepository repository, CharacteristicTransformer characteristicTransformer) {
        this.repository = repository;
        this.characteristicTransformer = characteristicTransformer;
    }

    public List<CharacteristicDto> save(List<CharacteristicDto> characteristicsDto, Customer customer) {
        log.debug("save");
        if (characteristicsDto == null) {
            return null;
        }
        int i = 0;
        for (CharacteristicDto characteristicDto : characteristicsDto) {
            Characteristic characteristic = characteristicTransformer.transform(characteristicDto);
            characteristic.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            characteristic.setId(id);
            repository.save(characteristic);
            characteristicsDto.set(i, characteristicTransformer.transform(characteristic));
            i++;
        }
        return characteristicsDto;
    }

    public void delete(CharacteristicDto characteristicDto) {
        log.debug("delete");
        repository.delete(characteristicTransformer.transform(characteristicDto));
    }

    public List<CharacteristicDto> update(List<CharacteristicDto> characteristicsDto) {
        log.debug("update");
        for (CharacteristicDto characteristicDto : characteristicsDto) {
            repository.save(characteristicTransformer.transform(characteristicDto));
        }
        return characteristicsDto;
    }
}

