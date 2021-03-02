package com.mycompany.transfomer;

import com.mycompany.model.ContactMediumDto;
import com.mycompany.repository.ContactMedium;
import org.springframework.stereotype.Service;

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
}
