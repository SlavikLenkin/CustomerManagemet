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


    public MediumCharacteristic findMediumCharacteristic(ContactMedium contactMedium) {
        MediumCharacteristic mediumCharacteristic = new MediumCharacteristic();
        if (contactMedium.getMediumCharacteristicId() == null) {
            return mediumCharacteristic;
        }
        mediumCharacteristic = repository.findMediumCharacteristicById(contactMedium.getMediumCharacteristicId());
        return mediumCharacteristic;
    }

    public MediumCharacteristic save(MediumCharacteristic mediumCharacteristic) {
        if (mediumCharacteristic == null) {
            System.out.println("null");
            return null;
        }
        String id = UUID.randomUUID().toString();
        mediumCharacteristic.setId(id);
        System.out.println(mediumCharacteristic.getId());
        repository.save(mediumCharacteristic);
        return mediumCharacteristic;
    }

    public void delete(MediumCharacteristic mediumCharacteristic) {
        repository.delete(mediumCharacteristic);
    }
}
