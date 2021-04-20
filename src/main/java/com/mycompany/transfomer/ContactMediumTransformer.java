package com.mycompany.transfomer;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.repository.ContactMedium;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        target.setMediumType(contactMediumDto.getMediumType().orElse(null));
        target.setPreferred(contactMediumDto.isPreferred());
        target.setValidFor(contactMediumDto.getValidFor());
        return target;
    }

    public ContactMediumDto transform(ContactMedium contactMedium) {
        if (contactMedium == null) {
            return null;
        }
        ContactMediumDto contactMediumDto = new ContactMediumDto();
        contactMediumDto.setId(contactMedium.getId());
        contactMediumDto.setPreferred(contactMedium.isPreferred());
        contactMediumDto.setMediumType(Optional.ofNullable(contactMedium.getMediumType()));
        contactMediumDto.setValidFor(contactMedium.getValidFor());
        contactMediumDto.setMediumCharacteristic(mediumCharacteristicTransformer
                .transform(contactMedium.getMediumCharacteristic()));
        return contactMediumDto;
    }

    public List<ContactMediumDto> transform(List<ContactMedium> contactMediumList) {
        if (contactMediumList == null) {
            return null;
        }
        List<ContactMediumDto> contactMediumDtoList = new ArrayList<>();
        for (ContactMedium contactMedium : contactMediumList) {
            contactMediumDtoList.add(transform(contactMedium));
        }
        return contactMediumDtoList;
    }
}
