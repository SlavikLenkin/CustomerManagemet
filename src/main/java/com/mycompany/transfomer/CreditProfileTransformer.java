package com.mycompany.transfomer;

import com.mycompany.model.CreditProfileDto;
import com.mycompany.repository.CreditProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditProfileTransformer {

    public CreditProfile transform(CreditProfileDto creditProfileDto) {
        if (creditProfileDto == null) {
            return null;
        }
        CreditProfile creditProfile = new CreditProfile();
        creditProfile.setId(creditProfileDto.getId());
        creditProfile.setCreditProfileDate(creditProfileDto.getCreditProfileDate());
        creditProfile.setCreditScore(creditProfileDto.getCreditScore());
        creditProfile.setValidFor(creditProfileDto.getValidFor());
        creditProfile.setCreditRiskRating(creditProfileDto.getCreditRiskRating());
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
        creditProfileDto.setCreditProfileDate(creditProfile.getCreditProfileDate());
        creditProfileDto.setCreditScore(creditProfile.getCreditScore());
        creditProfileDto.setValidFor(creditProfile.getValidFor());
        creditProfileDto.setCreditRiskRating(creditProfile.getCreditRiskRating());
        creditProfileDto.setCustomer(creditProfile.getCustomer());
        return creditProfileDto;
    }
}
