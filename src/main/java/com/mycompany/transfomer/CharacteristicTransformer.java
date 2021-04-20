package com.mycompany.transfomer;

import com.mycompany.model.CharacteristicDto;
import com.mycompany.repository.Characteristic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacteristicTransformer {

    public Characteristic transform(CharacteristicDto characteristicDto) {
        if (characteristicDto == null) {
            return null;
        }
        Characteristic characteristic = new Characteristic();
        characteristic.setId(characteristicDto.getId());
        characteristic.setCustomer(characteristicDto.getCustomer());
        characteristic.setName(characteristicDto.getName().orElse(null));
        characteristic.setValueType(characteristicDto.getValueType().orElse(null));
        characteristic.setValue(characteristicDto.getValue().orElse(null));
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
        characteristicDto.setName(Optional.ofNullable(characteristic.getName()));
        characteristicDto.setValueType(Optional.ofNullable(characteristic.getValueType()));
        characteristicDto.setValue(Optional.ofNullable(characteristic.getValue()));
        return characteristicDto;
    }
}
