package com.mycompany.transfomer;

import com.mycompany.model.CharacteristicDto;
import com.mycompany.repository.Characteristic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacteristicTransformer {

    public Characteristic transform(CharacteristicDto characteristicDto) {
        if (characteristicDto == null) {
            return null;
        }
        Characteristic characteristic = new Characteristic();
        characteristic.setId(characteristicDto.getId());
        characteristic.setCustomer(characteristicDto.getCustomer());
        characteristic.setName(characteristicDto.getName());
        characteristic.setValueType(characteristicDto.getValueType());
        characteristic.setValue(characteristicDto.getValue());
        return characteristic;
    }

    public List<CharacteristicDto> transform(List<Characteristic> characteristicList) {
        if (characteristicList == null) {
            return null;
        }
        List<CharacteristicDto> characteristicDtoList = new ArrayList<>();
        for (Characteristic characteristic : characteristicList) {
            characteristicDtoList.add(transform(characteristic));
        }
        return characteristicDtoList;
    }

    public CharacteristicDto transform(Characteristic characteristic) {
        if (characteristic == null) {
            return null;
        }
        CharacteristicDto characteristicDto = new CharacteristicDto();
        characteristicDto.setId(characteristic.getId());
        characteristicDto.setCustomer(characteristic.getCustomer());
        characteristicDto.setName(characteristic.getName());
        characteristicDto.setValueType(characteristic.getValueType());
        characteristicDto.setValue(characteristic.getValue());
        return characteristicDto;
    }
}
