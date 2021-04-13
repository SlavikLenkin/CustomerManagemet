package com.mycompany.transfomer;

import com.mycompany.model.MediumCharacteristicDto;
import com.mycompany.repository.MediumCharacteristic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediumCharacteristicTransformer {

    public MediumCharacteristic transform(MediumCharacteristicDto mediumCharacteristicDto) {
        if (mediumCharacteristicDto == null) {
            return null;
        }
        MediumCharacteristic mediumCharacteristic = new MediumCharacteristic();
        mediumCharacteristic.setId(mediumCharacteristicDto.getId());
        mediumCharacteristic.setContactMedium(mediumCharacteristicDto.getContactMedium());
        mediumCharacteristic.setCity(mediumCharacteristicDto.getCity());
        mediumCharacteristic.setStreet2(mediumCharacteristicDto.getStreet2());
        mediumCharacteristic.setStreet1(mediumCharacteristicDto.getStreet1());
        mediumCharacteristic.setStateOrProvince(mediumCharacteristicDto.getStateOrProvince());
        mediumCharacteristic.setSocialNetworkId(mediumCharacteristicDto.getSocialNetworkId());
        mediumCharacteristic.setPostCode(mediumCharacteristicDto.getPostCode());
        mediumCharacteristic.setPhoneNumber(mediumCharacteristicDto.getPhoneNumber());
        mediumCharacteristic.setFaxNumber(mediumCharacteristicDto.getFaxNumber());
        mediumCharacteristic.setCountry(mediumCharacteristicDto.getCountry());
        mediumCharacteristic.setEmailAddress(mediumCharacteristicDto.getEmailAddress());
        mediumCharacteristic.setContactType(mediumCharacteristicDto.getContactType());
        return mediumCharacteristic;
    }

    public MediumCharacteristicDto transform(MediumCharacteristic mediumCharacteristic) {
        if (mediumCharacteristic == null) {
            return null;
        }
        MediumCharacteristicDto mediumCharacteristicDto = new MediumCharacteristicDto();
        mediumCharacteristicDto.setId(mediumCharacteristic.getId());
        mediumCharacteristicDto.setContactMedium(mediumCharacteristic.getContactMedium());
        mediumCharacteristicDto.setCity(mediumCharacteristic.getCity());
        mediumCharacteristicDto.setStreet2(mediumCharacteristic.getStreet2());
        mediumCharacteristicDto.setStreet1(mediumCharacteristic.getStreet1());
        mediumCharacteristicDto.setStateOrProvince(mediumCharacteristic.getStateOrProvince());
        mediumCharacteristicDto.setSocialNetworkId(mediumCharacteristic.getSocialNetworkId());
        mediumCharacteristicDto.setPostCode(mediumCharacteristic.getPostCode());
        mediumCharacteristicDto.setPhoneNumber(mediumCharacteristic.getPhoneNumber());
        mediumCharacteristicDto.setFaxNumber(mediumCharacteristic.getFaxNumber());
        mediumCharacteristicDto.setCountry(mediumCharacteristic.getCountry());
        mediumCharacteristicDto.setEmailAddress(mediumCharacteristic.getEmailAddress());
        mediumCharacteristicDto.setContactType(mediumCharacteristic.getContactType());
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
