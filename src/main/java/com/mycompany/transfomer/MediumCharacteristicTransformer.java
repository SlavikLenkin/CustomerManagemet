package com.mycompany.transfomer;

import com.mycompany.model.MediumCharacteristicDto;
import com.mycompany.repository.MediumCharacteristic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediumCharacteristicTransformer {

    public MediumCharacteristic transform(MediumCharacteristicDto mediumCharacteristicDto) {
        if (mediumCharacteristicDto == null) {
            return null;
        }
        MediumCharacteristic mediumCharacteristic = new MediumCharacteristic();
        mediumCharacteristic.setId(mediumCharacteristicDto.getId());
        mediumCharacteristic.setContactMedium(mediumCharacteristicDto.getContactMedium());
        mediumCharacteristic.setCity(mediumCharacteristicDto.getCity().orElse(null));
        mediumCharacteristic.setStreet2(mediumCharacteristicDto.getStreet2().orElse(null));
        mediumCharacteristic.setStreet1(mediumCharacteristicDto.getStreet1().orElse(null));
        mediumCharacteristic.setStateOrProvince(mediumCharacteristicDto.getStateOrProvince().orElse(null));
        mediumCharacteristic.setSocialNetworkId(mediumCharacteristicDto.getSocialNetworkId().orElse(null));
        mediumCharacteristic.setPostCode(mediumCharacteristicDto.getPostCode().orElse(null));
        mediumCharacteristic.setPhoneNumber(mediumCharacteristicDto.getPhoneNumber().orElse(null));
        mediumCharacteristic.setFaxNumber(mediumCharacteristicDto.getFaxNumber().orElse(null));
        mediumCharacteristic.setCountry(mediumCharacteristicDto.getCountry().orElse(null));
        mediumCharacteristic.setEmailAddress(mediumCharacteristicDto.getEmailAddress().orElse(null));
        mediumCharacteristic.setContactType(mediumCharacteristicDto.getContactType().orElse(null));
        return mediumCharacteristic;
    }

    public MediumCharacteristicDto transform(MediumCharacteristic mediumCharacteristic) {
        if (mediumCharacteristic == null) {
            return null;
        }
        MediumCharacteristicDto mediumCharacteristicDto = new MediumCharacteristicDto();
        mediumCharacteristicDto.setId(mediumCharacteristic.getId());
        mediumCharacteristicDto.setContactMedium(mediumCharacteristic.getContactMedium());
        mediumCharacteristicDto.setCity(Optional.ofNullable(mediumCharacteristic.getCity()));
        mediumCharacteristicDto.setStreet2(Optional.ofNullable(mediumCharacteristic.getStreet2()));
        mediumCharacteristicDto.setStreet1(Optional.ofNullable(mediumCharacteristic.getStreet1()));
        mediumCharacteristicDto.setStateOrProvince(Optional.ofNullable(mediumCharacteristic.getStateOrProvince()));
        mediumCharacteristicDto.setSocialNetworkId(Optional.ofNullable(mediumCharacteristic.getSocialNetworkId()));
        mediumCharacteristicDto.setPostCode(Optional.ofNullable(mediumCharacteristic.getPostCode()));
        mediumCharacteristicDto.setPhoneNumber(Optional.ofNullable(mediumCharacteristic.getPhoneNumber()));
        mediumCharacteristicDto.setFaxNumber(Optional.ofNullable(mediumCharacteristic.getFaxNumber()));
        mediumCharacteristicDto.setCountry(Optional.ofNullable(mediumCharacteristic.getCountry()));
        mediumCharacteristicDto.setEmailAddress(Optional.ofNullable(mediumCharacteristic.getEmailAddress()));
        mediumCharacteristicDto.setContactType(Optional.ofNullable(mediumCharacteristic.getContactType()));
        return mediumCharacteristicDto;
    }

    public List<MediumCharacteristicDto> transform(List<MediumCharacteristic> mediumCharacteristicList) {
        if (mediumCharacteristicList == null) {
            return null;
        }
        List<MediumCharacteristicDto> mediumCharacteristicDtoList = new ArrayList<>();
        for (MediumCharacteristic mediumCharacteristic : mediumCharacteristicList) {
            mediumCharacteristicDtoList.add(transform(mediumCharacteristic));
        }
        return mediumCharacteristicDtoList;
    }
}
