package com.mycompany.service;


import com.mycompany.model.ContactMediumDto;
import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.ContactMediumRepository;
import com.mycompany.repository.Customer;
import com.mycompany.repository.MediumCharacteristic;
import com.mycompany.transfomer.ContactMediumTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ContactMediumService {

    final ContactMediumRepository repository;
    final ContactMediumTransformer contactMediumTransformer;
    final MediumCharacteristicService mediumCharacteristicService;

    public ContactMediumService(ContactMediumRepository repository, ContactMediumTransformer contactMediumTransformer, MediumCharacteristicService mediumCharacteristicService) {
        this.repository = repository;
        this.contactMediumTransformer = contactMediumTransformer;
        this.mediumCharacteristicService = mediumCharacteristicService;
    }

    public List<ContactMediumDto> save(List<ContactMediumDto> contactsMediumDto, Customer customer) {
        log.debug("saveOne");
        if (contactsMediumDto == null) {
            return null;
        }

        for (ContactMediumDto contactMediumDto : contactsMediumDto) {
            contactMediumDto.setCustomer(customer);
            MediumCharacteristic mediumCharacteristic;


            ContactMedium contactMedium = contactMediumTransformer.transform(contactMediumDto);

            contactMediumDto.setContactMedium(contactMedium);
            contactMediumDto.setId(contactMedium.getId());
            contactMedium.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            contactMedium.setId(id);
            repository.save(contactMedium);

            mediumCharacteristic = contactMediumDto.getMediumCharacteristic();
            contactMediumDto.setMediumCharacteristic(mediumCharacteristicService
                    .save(mediumCharacteristic, contactMedium));


            contactMediumDto.setContactMedium(contactMedium);
        }
        return contactsMediumDto;
    }

    public List<ContactMediumDto> update(List<ContactMediumDto> contactMediumDtoList, Customer customer) {

        log.debug("update");
        List<ContactMedium> contactMediumList = new ArrayList<>();
        if (contactMediumList == null) {
            return null;
        }

        for (ContactMediumDto contactMediumDto : contactMediumDtoList) {
            MediumCharacteristic mediumCharacteristic;

            ContactMedium contactMedium = contactMediumTransformer.transform(contactMediumDto);
            contactMedium.setCustomer(customer);
            contactMediumDto.setContactMedium(contactMedium);
            contactMediumDto.setId(contactMedium.getId());
            repository.save(contactMedium);

            mediumCharacteristic = contactMediumDto.getMediumCharacteristic();
            contactMediumDto.setMediumCharacteristic(mediumCharacteristicService
                    .update(mediumCharacteristic));


            contactMediumList.add(contactMedium);
        }

        return contactMediumDtoList;
    }

    public void delete(ContactMediumDto contactMediumDto) {
        log.debug("delete");
        mediumCharacteristicService.delete(contactMediumDto.getMediumCharacteristic());
        repository.delete(contactMediumTransformer.transform(contactMediumDto));
    }
}
