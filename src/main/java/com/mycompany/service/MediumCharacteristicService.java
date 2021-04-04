package com.mycompany.service;

import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.MediumCharacteristic;
import com.mycompany.repository.MediumCharacteristicRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MediumCharacteristicService {

    final
    MediumCharacteristicRepository repository;


    public MediumCharacteristicService(MediumCharacteristicRepository repository) {
        this.repository = repository;
    }



    public MediumCharacteristic save(MediumCharacteristic mediumCharacteristic, ContactMedium contactMedium) {
        if (mediumCharacteristic == null) {
            return null;
        }
        mediumCharacteristic.setContactMedium(contactMedium);
        String id = UUID.randomUUID().toString();
        mediumCharacteristic.setId(id);
        repository.save(mediumCharacteristic);
        return mediumCharacteristic;
    }

    public void delete(MediumCharacteristic mediumCharacteristic) {
        if (mediumCharacteristic != null) {
            repository.delete(mediumCharacteristic);
        }
    }

    public MediumCharacteristic update(MediumCharacteristic mediumCharacteristic) {
        repository.save(mediumCharacteristic);
        return mediumCharacteristic;
    }
}
