package com.mycompany.service;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.Customer;
import com.mycompany.repository.MediumCharacteristic;
import com.mycompany.transfomer.ContactMediumTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ContactMediumDtoService {

    private final ContactMediumService contactMediumService;
    private final MediumCharacteristicService mediumCharacteristicService;
    private final ContactMediumTransformer transformer;
    private MediumCharacteristic mediumCharacteristic;

    public ContactMediumDtoService(ContactMediumService contactMediumService
            , MediumCharacteristicService mediumCharacteristicService
            , ContactMediumTransformer transformer) {
        this.contactMediumService = contactMediumService;
        this.mediumCharacteristicService = mediumCharacteristicService;
        this.transformer = transformer;
    }

    private void setData(ContactMediumDto contactMediumDto) {
        log.debug("setData");
        mediumCharacteristic = contactMediumDto.getMediumCharacteristic();
    }

    public List<ContactMediumDto> getContactMediumDto(List<ContactMedium> contactMediumList) {
        log.debug("getContactMediumDto");
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        int i = 0;
        for (ContactMedium contactMedium : contactMediumList) {
            ContactMediumDto contactMediumDto = new ContactMediumDto();
            contactMediumDto.setContactMedium(contactMedium);
            contactMediumDto.setMediumCharacteristic(contactMedium.getMediumCharacteristic());
            contactMediumDtoList.add(contactMediumDto);

        }

        return contactMediumDtoList;
    }

    public List<ContactMediumDto> save(List<ContactMediumDto> contactsMediumDto, Customer customer) {
        log.debug("save");
        List<ContactMedium> contactMediumList = new ArrayList<>();
        if (contactsMediumDto == null) {
            return null;
        }

        for (ContactMediumDto contactMediumDto : contactsMediumDto) {
            contactMediumDto.setCustomer(customer);
            setData(contactMediumDto);


            ContactMedium contactMedium = transformer.transform(contactMediumDto);

            contactMediumDto.setContactMedium(contactMedium);
            contactMediumDto.setId(contactMedium.getId());
            contactMediumService.saveOne(contactMedium, customer);

            mediumCharacteristic = contactMediumDto.getMediumCharacteristic();
            contactMediumDto.setMediumCharacteristic(mediumCharacteristicService
                    .save(mediumCharacteristic, contactMedium));


            contactMediumDto.setContactMedium(contactMedium);
        }


        return contactsMediumDto;

    }

    public void delete(ContactMediumDto contactMediumDto) {
        log.debug("delete");
        mediumCharacteristicService.delete(contactMediumDto.getMediumCharacteristic());
        contactMediumService.delete(transformer.transform(contactMediumDto));
    }

    public List<ContactMediumDto> update(List<ContactMediumDto> contactMediumDtoList, Customer customer) {
        log.debug("update");
        List<ContactMedium> contactMediumList = new ArrayList<>();
        if (contactMediumList == null) {
            return null;
        }

        for (ContactMediumDto contactMediumDto : contactMediumDtoList) {
            setData(contactMediumDto);

            ContactMedium contactMedium = transformer.transform(contactMediumDto);
            contactMedium.setCustomer(customer);
            contactMediumDto.setContactMedium(contactMedium);
            contactMediumDto.setId(contactMedium.getId());
            contactMediumService.updateOne(contactMedium);

            mediumCharacteristic = contactMediumDto.getMediumCharacteristic();
            contactMediumDto.setMediumCharacteristic(mediumCharacteristicService
                    .update(mediumCharacteristic));


            contactMediumList.add(contactMedium);
        }

        return contactMediumDtoList;
    }
}
