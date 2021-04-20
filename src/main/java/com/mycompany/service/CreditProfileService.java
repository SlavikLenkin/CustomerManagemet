package com.mycompany.service;

import com.mycompany.model.CreditProfileDto;
import com.mycompany.repository.CreditProfile;
import com.mycompany.repository.CreditProfileRepository;
import com.mycompany.repository.Customer;
import com.mycompany.transfomer.CreditProfileTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CreditProfileService {

    private final CreditProfileRepository repository;
    private final CreditProfileTransformer creditProfileTransformer;

    public CreditProfileService(CreditProfileRepository repository
            , CreditProfileTransformer creditProfileTransformer) {
        this.repository = repository;
        this.creditProfileTransformer = creditProfileTransformer;
    }

    public List<CreditProfileDto> save(List<CreditProfileDto> creditProfilesDto, Customer customer) {
        log.debug("save");
        if (creditProfilesDto == null) {
            return null;
        }
        int i = 0;
        for (CreditProfileDto creditProfileDto : creditProfilesDto) {
            CreditProfile creditProfile = creditProfileTransformer.transform(creditProfileDto);
            creditProfile.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            creditProfile.setId(id);
            repository.save(creditProfile);
            creditProfilesDto.set(i, creditProfileTransformer.transform(creditProfile));
            i++;
        }
        return creditProfilesDto;
    }

    public void delete(CreditProfileDto creditProfileDto) {
        log.debug("delete");
        repository.delete(creditProfileTransformer.transform(creditProfileDto));
    }

    public List<CreditProfileDto> update(List<CreditProfileDto> creditProfilesDto) {
        log.debug("update");
        for (CreditProfileDto creditProfileDto : creditProfilesDto) {
            repository.save(creditProfileTransformer.transform(creditProfileDto));
        }
        return creditProfilesDto;
    }
}
