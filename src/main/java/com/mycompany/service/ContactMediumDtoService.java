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


    public ContactMediumDtoService(ContactMediumService contactMediumService, MediumCharacteristicService mediumCharacteristicService) {
        this.contactMediumService = contactMediumService;
        this.mediumCharacteristicService = mediumCharacteristicService;
    }

    private void setData(ContactMediumDto contactMediumDto) {
        transformer = new ContactMediumTransformer();
        mediumCharacteristic = contactMediumDto.getMediumCharacteristic();
    }

    private ContactMediumDto getContactMediumDto(ContactMedium contactMedium) {

        ContactMediumDto contactMediumDto = new ContactMediumDto();
        contactMediumDto.setContactMedium(contactMedium);
        contactMediumDto.setMediumCharacteristic(mediumCharacteristic);


        return contactMediumDto;
    }



    public List<ContactMediumDto> save(List<ContactMediumDto> contactsMediumDto, Customer customer) {


        List<ContactMedium> contactMediumList = new ArrayList<>();
        if (contactsMediumDto == null){
            return null;
        }
        for (ContactMediumDto contactMediumDto : contactsMediumDto) {
            contactMediumDto.setCustomer(customer);
            setData(contactMediumDto);
            contactMediumDto.setMediumCharacteristic(mediumCharacteristicService
                    .save(mediumCharacteristic));

            mediumCharacteristic = contactMediumDto.getMediumCharacteristic();
            ContactMedium contactMedium = transformer.transform(contactMediumDto);


            contactMediumDto.setContactMedium(contactMedium);
            contactMediumDto.setId(contactMedium.getId());
            contactMediumList.add(contactMedium);
        }

        contactMediumList = contactMediumService.save(contactMediumList,customer);

        return contactsMediumDto;

    }

    public void delete(ContactMediumDto contactMediumDto) {
        mediumCharacteristicService.delete(contactMediumDto.getMediumCharacteristic());
        contactMediumService.delete(transformer.transform(contactMediumDto));
    }


    public List<ContactMediumDto> update(List<ContactMediumDto> contactMediumDtoList) {


        List<ContactMedium> contactMediumList = new ArrayList<>();
        if (contactMediumDtoList == null) {
            return null;
        }
        for (ContactMediumDto contactMediumDto :contactMediumDtoList ) {
            setData(contactMediumDto);
            contactMediumDto.setMediumCharacteristic(mediumCharacteristicService.update(mediumCharacteristic));

            mediumCharacteristic = contactMediumDto.getMediumCharacteristic();
            ContactMedium contactMedium = transformer.transform(contactMediumDto);


            contactMediumDto.setContactMedium(contactMedium);
            contactMediumDto.setId(contactMedium.getId());
            contactMediumList.add(contactMedium);
        }

        contactMediumList = contactMediumService.update(contactMediumList);

        int i = 0;
        for (ContactMediumDto contactMediumDto : contactMediumDtoList) {


            contactMediumDto.setId(contactMediumList.get(i).getId());
            i++;

        }
        return contactMediumDtoList;


    }
}
