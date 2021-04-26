package com.mycompany.service;

import com.mycompany.model.MediumCharacteristicDto;
import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.MediumCharacteristic;
import com.mycompany.repository.MediumCharacteristicRepository;
import com.mycompany.transfomer.MediumCharacteristicTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MediumCharacteristicService {

    final MediumCharacteristicRepository repository;
    final MediumCharacteristicTransformer mediumCharacteristicTransformer;

    public MediumCharacteristicService(MediumCharacteristicRepository repository
            , MediumCharacteristicTransformer mediumCharacteristicTransformer) {
        this.repository = repository;
        this.mediumCharacteristicTransformer = mediumCharacteristicTransformer;
    }

    public MediumCharacteristicDto save(MediumCharacteristicDto mediumCharacteristicDto
            , ContactMedium contactMedium) {
        log.debug("save");
        if (mediumCharacteristicDto == null) {
            return null;
        }
        if (mediumCharacteristicDto.getCity() == null) {
            mediumCharacteristicDto.setCity(Optional.empty());
        }
        if (mediumCharacteristicDto.getContactType() == null) {
            mediumCharacteristicDto.setContactType(Optional.empty());
        }
        if (mediumCharacteristicDto.getCountry() == null) {
            mediumCharacteristicDto.setCountry(Optional.empty());
        }
        if (mediumCharacteristicDto.getEmailAddress() == null) {
            mediumCharacteristicDto.setEmailAddress(Optional.empty());
        }
        if (mediumCharacteristicDto.getFaxNumber() == null) {
            mediumCharacteristicDto.setFaxNumber(Optional.empty());
        }
        if (mediumCharacteristicDto.getPhoneNumber() == null) {
            mediumCharacteristicDto.setPhoneNumber(Optional.empty());
        }
        if (mediumCharacteristicDto.getPostCode() == null) {
            mediumCharacteristicDto.setPostCode(Optional.empty());
        }
        if (mediumCharacteristicDto.getSocialNetworkId() == null) {
            mediumCharacteristicDto.setSocialNetworkId(Optional.empty());
        }
        if (mediumCharacteristicDto.getStateOrProvince() == null) {
            mediumCharacteristicDto.setStateOrProvince(Optional.empty());
        }
        if (mediumCharacteristicDto.getStreet1() == null) {
            mediumCharacteristicDto.setStreet1(Optional.empty());
        }
        if (mediumCharacteristicDto.getStreet2() == null) {
            mediumCharacteristicDto.setStreet2(Optional.empty());
        }
        MediumCharacteristic mediumCharacteristic = mediumCharacteristicTransformer
                .transform(mediumCharacteristicDto);
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
