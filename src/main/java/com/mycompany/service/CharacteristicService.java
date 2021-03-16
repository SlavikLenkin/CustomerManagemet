package com.mycompany.service;


import com.mycompany.repository.Characteristic;
import com.mycompany.repository.CharacteristicRepository;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CharacteristicService {

    final
    CharacteristicRepository repository;


    public CharacteristicService(CharacteristicRepository repository) {
        this.repository = repository;
    }
//
//    public List<Characteristic> findAllCharacteristics(Customer customer) {
//        List<Characteristic> characteristics = new ArrayList<>();
//        if (customer.getCharacteristicId() == null) {
//            return characteristics;
//        }
//        characteristics = repository.findCharacteristicById(customer.getCharacteristicId());
//        return characteristics;
//    }

    public List<Characteristic> save(List<Characteristic> characteristics) {
        if (characteristics == null) {
            return characteristics;
        }
        for (Characteristic characteristic : characteristics) {
            String id = UUID.randomUUID().toString();
            characteristic.setId(id);
            repository.save(characteristic);
        }
        return characteristics;
    }

    public void delete(Characteristic characteristic) {
        repository.delete(characteristic);
    }

    public List<Characteristic> update(List<Characteristic> characteristics) {
        for (Characteristic characteristic : characteristics){
            repository.save(characteristic);
        }
        return characteristics;
    }
}

