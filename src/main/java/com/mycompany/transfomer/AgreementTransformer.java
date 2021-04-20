package com.mycompany.transfomer;

import com.mycompany.model.AgreementDto;
import com.mycompany.repository.Agreement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgreementTransformer {

    public Agreement transform(AgreementDto agreementDto) {
        if (agreementDto == null) {
            return null;
        }
        Agreement agreement = new Agreement();
        agreement.setHref(agreementDto.getHref());
        agreement.setName(agreementDto.getName());
        agreement.setId(agreementDto.getId());
        agreement.setCustomer(agreementDto.getCustomer());
        return agreement;
    }

    public List<AgreementDto> transform(List<Agreement> agreementList) {
        if (agreementList == null) {
            return null;
        }
        List<AgreementDto> agreementDtoList = new ArrayList<>();
        for (Agreement agreement : agreementList) {
            agreementDtoList.add(transform(agreement));
        }
        return agreementDtoList;
    }

    public AgreementDto transform(Agreement agreement) {
        if (agreement == null) {
            return null;
        }
        AgreementDto agreementDto = new AgreementDto();
        agreementDto.setId(agreement.getId());
        agreementDto.setName(agreement.getName());
        agreementDto.setHref(agreement.getHref());
        agreementDto.setCustomer(agreement.getCustomer());
        return agreementDto;
    }
}
