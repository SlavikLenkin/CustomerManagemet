package com.mycompany.service;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.Customer;
import com.mycompany.repository.MediumCharacteristic;
import com.mycompany.transfomer.ContactMediumTransformer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactMediumDtoService {

    private final ContactMediumService contactMediumService;
    private final MediumCharacteristicService mediumCharacteristicService;


    private ContactMediumTransformer transformer;
    private MediumCharacteristic mediumCharacteristic;

    private void setData(ContactMediumDto contactMediumDto) {
        transformer = new ContactMediumTransformer();
        mediumCharacteristic = contactMediumDto.getMediumCharacteristic();
    }


    public ContactMediumDtoService(ContactMediumService contactMediumService, MediumCharacteristicService mediumCharacteristicService) {
        this.contactMediumService = contactMediumService;
        this.mediumCharacteristicService = mediumCharacteristicService;
    }

    private ContactMediumDto getContactMediumDto(ContactMedium contactMedium) {
        MediumCharacteristic mediumCharacteristic = mediumCharacteristicService.findMediumCharacteristic(contactMedium);

        ContactMediumDto contactMediumDto = new ContactMediumDto();
        contactMediumDto.setContactMedium(contactMedium);
        contactMediumDto.setMediumCharacteristic(mediumCharacteristic);

        return contactMediumDto;
    }

    public List<ContactMediumDto> getAllContactMediumDto(Customer customer) {
        List<ContactMediumDto> allContactMediumDto = new ArrayList<>();
        List<ContactMedium> contactMediumList = contactMediumService.findAllContactsMedium(customer);
        for (ContactMedium contactMedium : contactMediumList) {
            allContactMediumDto.add(getContactMediumDto(contactMedium));
        }
        return allContactMediumDto;
    }

    public List<ContactMediumDto> save(List<ContactMediumDto> contactsMediumDto) {
        List<ContactMedium> contactMediumList = new ArrayList<>();
        if (contactsMediumDto == null) {
            return null;
        }
        for (ContactMediumDto contactMediumDto : contactsMediumDto) {
            setData(contactMediumDto);
            contactMediumDto.setMediumCharacteristic(mediumCharacteristicService.save(mediumCharacteristic));

            mediumCharacteristic = contactMediumDto.getMediumCharacteristic();
            ContactMedium contactMedium = transformer.transform(contactMediumDto);

            if (mediumCharacteristic != null) {
                System.out.println(mediumCharacteristic.getId());
                contactMedium.setMediumCharacteristicId(mediumCharacteristic.getId());
            }

            contactMediumDto.setContactMedium(contactMedium);
            contactMediumList.add(contactMedium);
        }
        contactMediumService.save(contactMediumList);
        return contactsMediumDto;

    }


}
