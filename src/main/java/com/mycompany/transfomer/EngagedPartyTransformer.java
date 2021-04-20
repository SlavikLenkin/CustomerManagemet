package com.mycompany.transfomer;

import com.mycompany.model.EngagedPartyDto;
import com.mycompany.repository.EngagedParty;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EngagedPartyTransformer {

    public EngagedParty transform(EngagedPartyDto engagedPartyDto) {
        if (engagedPartyDto == null) {
            return null;
        }
        EngagedParty engagedParty = new EngagedParty();
        engagedParty.setHref(engagedPartyDto.getHref());
        engagedParty.setId(engagedPartyDto.getId());
        engagedParty.setName(engagedPartyDto.getName().orElse(null));
        engagedParty.setCustomer(engagedPartyDto.getCustomer());
        return engagedParty;
    }

    public EngagedPartyDto transform(EngagedParty engagedParty) {
        if (engagedParty == null) {
            return null;
        }
        EngagedPartyDto engagedPartyDto = new EngagedPartyDto();
        engagedPartyDto.setHref(engagedParty.getHref());
        engagedPartyDto.setId(engagedParty.getId());
        engagedPartyDto.setName(Optional.ofNullable(engagedParty.getName()));
        engagedPartyDto.setCustomer(engagedParty.getCustomer());
        return engagedPartyDto;
    }
}
