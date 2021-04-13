package com.mycompany.service;

import com.mycompany.model.MediumCharacteristicDto;
import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.MediumCharacteristic;
import com.mycompany.repository.MediumCharacteristicRepository;
import com.mycompany.transfomer.MediumCharacteristicTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class MediumCharacteristicService {

    final MediumCharacteristicRepository repository;
    final MediumCharacteristicTransformer mediumCharacteristicTransformer;

    public MediumCharacteristicService(MediumCharacteristicRepository repository, MediumCharacteristicTransformer mediumCharacteristicTransformer) {
        this.repository = repository;
        this.mediumCharacteristicTransformer = mediumCharacteristicTransformer;
    }

    public MediumCharacteristicDto save(MediumCharacteristicDto mediumCharacteristicDto, ContactMedium contactMedium) {
        log.debug("save");
        if (mediumCharacteristicDto == null) {
            return null;
        }
        MediumCharacteristic mediumCharacteristic = mediumCharacteristicTransformer.transform(mediumCharacteristicDto);
        mediumCharacteristic.setContactMedium(contactMedium);
        String id = UUID.randomUUID().toString();
        mediumCharacteristic.setId(id);
        repository.save(mediumCharacteristic);
        return mediumCharacteristicTransformer.transform(mediumCharacteristic);
    }

    public void delete(MediumCharacteristicDto mediumCharacteristicDto) {
        log.debug("delete");
        if (mediumCharacteristicDto != null) {
            repository.delete(mediumCharacteristicTransformer.transform(mediumCharacteristicDto));
        }
    }

    public MediumCharacteristicDto update(MediumCharacteristicDto mediumCharacteristicDto) {
        log.debug("update");
        repository.save(mediumCharacteristicTransformer.transform(mediumCharacteristicDto));
        return mediumCharacteristicDto;
    }
}
