package com.mycompany.transfomer;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.repository.ContactMedium;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactMediumTransformer {

    final MediumCharacteristicTransformer mediumCharacteristicTransformer;

    public ContactMediumTransformer(MediumCharacteristicTransformer mediumCharacteristicTransformer) {
        this.mediumCharacteristicTransformer = mediumCharacteristicTransformer;
    }

    public ContactMedium transform(ContactMediumDto contactMediumDto) {
        if (contactMediumDto == null) {
            return null;
        }
        ContactMedium target = new ContactMedium();
        target.setId(contactMediumDto.getId());
        target.setMediumType(contactMediumDto.getMediumType());
        target.setPreferred(contactMediumDto.isPreferred());
        target.setValidFor(contactMediumDto.getValidFor());
        return target;
    }

    public List<ContactMediumDto> transform(List<ContactMedium> contactMediumList) {
        if (contactMediumList == null) {
            return null;
        }
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        for (ContactMedium contactMedium : contactMediumList) {
            ContactMediumDto contactMediumDto = new ContactMediumDto();
            contactMediumDto.setContactMedium(contactMedium);
            contactMediumDto.setMediumCharacteristic(mediumCharacteristicTransformer
                    .transform(contactMedium.getMediumCharacteristic()));
            contactMediumDtoList.add(contactMediumDto);
        }
        return contactMediumDtoList;
    }
}
