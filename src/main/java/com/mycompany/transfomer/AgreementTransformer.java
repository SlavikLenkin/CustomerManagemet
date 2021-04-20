package com.mycompany.transfomer;

import com.mycompany.model.AgreementDto;
import com.mycompany.repository.Agreement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgreementTransformer {

    public Agreement transform(AgreementDto agreementDto) {
        if (agreementDto == null) {
            return null;
        }
        Agreement agreement = new Agreement();
        agreement.setHref(agreementDto.getHref());
        agreement.setName(agreementDto.getName().orElse(null));
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
        agreementDto.setName(Optional.ofNullable(agreement.getName()));
        agreementDto.setHref(agreement.getHref());
        agreementDto.setCustomer(agreement.getCustomer());
        return agreementDto;
    }
}
