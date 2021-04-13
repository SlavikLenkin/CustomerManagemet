package com.mycompany.transfomer;

import com.mycompany.model.RelatedPartyDto;
import com.mycompany.repository.RelatedParty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelatedPartyTransformer {

    public RelatedParty transform(RelatedPartyDto relatedPartyDto) {
        if (relatedPartyDto == null) {
            return null;
        }
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setId(relatedPartyDto.getId());
        relatedParty.setHref(relatedPartyDto.getHref());
        relatedParty.setName(relatedPartyDto.getName());
        relatedParty.setRole(relatedPartyDto.getRole());
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
        relatedPartyDto.setName(relatedParty.getName());
        relatedPartyDto.setRole(relatedParty.getRole());
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
