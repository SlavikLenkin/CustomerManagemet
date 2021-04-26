package com.mycompany.service;

import com.mycompany.model.AgreementDto;
import com.mycompany.repository.Agreement;
import com.mycompany.repository.AgreementRepository;
import com.mycompany.repository.Customer;
import com.mycompany.transfomer.AgreementTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AgreementService {

    final AgreementRepository repository;
    final AgreementTransformer agreementTransformer;

    public AgreementService(AgreementRepository repository, AgreementTransformer agreementTransformer) {
        this.repository = repository;
        this.agreementTransformer = agreementTransformer;
    }

    public List<AgreementDto> save(List<AgreementDto> agreementsDto, Customer customer) {
        log.debug("save");
        if (agreementsDto == null) {
            return null;
        }
        int i = 0;
        for (AgreementDto agreementDto : agreementsDto) {
            if (agreementDto.getName() == null) {
                agreementDto.setName(Optional.empty());
            }
            Agreement agreement = agreementTransformer.transform(agreementDto);
            agreement.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            agreement.setId(id);
            agreement.setHref("https://host:port/tmf-api/customerManagement/v4/agreement/" + id);
            repository.save(agreement);
            agreementsDto.set(i, agreementTransformer.transform(agreement));
            i++;
        }
        return agreementsDto;
    }

    public void delete(Agreement agreement) {
        log.debug("delete");
        repository.delete(agreement);
    }

    public List<AgreementDto> update(List<AgreementDto> agreements) {
        log.debug("update");
        for (AgreementDto agreementDto : agreements) {
            repository.save(agreementTransformer.transform(agreementDto));
        }
        return agreements;
    }
}
