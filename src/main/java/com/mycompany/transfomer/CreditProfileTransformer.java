package com.mycompany.transfomer;

import com.mycompany.model.CreditProfileDto;
import com.mycompany.repository.CreditProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditProfileTransformer {

    public CreditProfile transform(CreditProfileDto creditProfileDto) {
        if (creditProfileDto == null) {
            return null;
        }
        CreditProfile creditProfile = new CreditProfile();
        creditProfile.setId(creditProfileDto.getId());
        creditProfile.setCreditProfileDate(creditProfileDto.getCreditProfileDate().orElse(null));
        creditProfile.setCreditScore(creditProfileDto.getCreditScore().orElse(null));
        creditProfile.setValidFor(creditProfileDto.getValidFor());
        creditProfile.setCreditRiskRating(creditProfileDto.getCreditRiskRating().orElse(null));
        creditProfile.setCustomer(creditProfileDto.getCustomer());
        return creditProfile;
    }

    public List<CreditProfileDto> transform(List<CreditProfile> creditProfileList) {
        if (creditProfileList == null) {
            return null;
        }
        List<CreditProfileDto> creditProfileDtoList = new ArrayList<>();
        for (CreditProfile creditProfile : creditProfileList) {
            creditProfileDtoList.add(transform(creditProfile));
        }
        return creditProfileDtoList;
    }

    public CreditProfileDto transform(CreditProfile creditProfile) {
        if (creditProfile == null) {
            return null;
        }
        CreditProfileDto creditProfileDto = new CreditProfileDto();
        creditProfileDto.setId(creditProfile.getId());
        creditProfileDto.setCreditProfileDate(Optional.ofNullable(creditProfile.getCreditProfileDate()));
        creditProfileDto.setCreditScore(Optional.of(creditProfile.getCreditScore()));
        creditProfileDto.setValidFor(creditProfile.getValidFor());
        creditProfileDto.setCreditRiskRating(Optional.of(creditProfile.getCreditRiskRating()));
        creditProfileDto.setCustomer(creditProfile.getCustomer());
        return creditProfileDto;
    }
}
