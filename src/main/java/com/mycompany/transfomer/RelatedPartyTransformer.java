package com.mycompany.transfomer;

import com.mycompany.model.RelatedPartyDto;
import com.mycompany.repository.RelatedParty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RelatedPartyTransformer {

    public RelatedParty transform(RelatedPartyDto relatedPartyDto) {
        if (relatedPartyDto == null) {
            return null;
        }
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setId(relatedPartyDto.getId());
        relatedParty.setHref(relatedPartyDto.getHref());
        relatedParty.setName(relatedPartyDto.getName().orElse(null));
        relatedParty.setRole(relatedPartyDto.getRole().orElse(null));
        relatedParty.setCustomer(relatedPartyDto.getCustomer());
        return relatedParty;
    }

    public RelatedPartyDto transform(RelatedParty relatedParty) {
        if (relatedParty == null) {
            return null;
        }
        RelatedPartyDto relatedPartyDto = new RelatedPartyDto();
        relatedPartyDto.setId(relatedParty.getId());
        relatedPartyDto.setHref(relatedParty.getHref());
        relatedPartyDto.setName(Optional.ofNullable(relatedParty.getName()));
        relatedPartyDto.setRole(Optional.ofNullable(relatedParty.getRole()));
        relatedPartyDto.setCustomer(relatedParty.getCustomer());
        return relatedPartyDto;
    }

    public List<RelatedPartyDto> transform(List<RelatedParty> relatedPartyList) {
        if (relatedPartyList == null) {
            return null;
        }
        List<RelatedPartyDto> relatedPartyDtoList = new ArrayList<>();
        for (RelatedParty relatedParty : relatedPartyList) {
            relatedPartyDtoList.add(transform(relatedParty));
        }
        return relatedPartyDtoList;
    }
}
