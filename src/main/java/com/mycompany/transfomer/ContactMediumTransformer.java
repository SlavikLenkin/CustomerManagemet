package com.mycompany.transfomer;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.repository.ContactMedium;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactMediumTransformer {

    public ContactMedium transform(ContactMediumDto contactMediumDto) {
        ContactMedium target = new ContactMedium();
        target.setId(contactMediumDto.getId());
        target.setMediumType(contactMediumDto.getMediumType());
        target.setPreferred(contactMediumDto.isPreferred());
        target.setValidFor(contactMediumDto.getValidFor());
        return target;
    }

    public List<ContactMediumDto> getContactMediumDto(List<ContactMedium> contactMediumList) {
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
}
