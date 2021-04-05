package com.mycompany.service;

import com.mycompany.repository.CreditProfile;
import com.mycompany.repository.CreditProfileRepository;
import com.mycompany.repository.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CreditProfileService {

    private final CreditProfileRepository repository;

    public CreditProfileService(CreditProfileRepository repository) {
        this.repository = repository;
    }

    public List<CreditProfile> save(List<CreditProfile> creditProfiles, Customer customer) {
        log.debug("save");
        if (creditProfiles == null) {
            return null;
        }
        for (CreditProfile creditProfile : creditProfiles) {
            creditProfile.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            creditProfile.setId(id);
            repository.save(creditProfile);
        }
        return creditProfiles;
    }

    public void delete(CreditProfile creditProfile) {
        log.debug("delete");
        repository.delete(creditProfile);
    }

    public List<CreditProfile> update(List<CreditProfile> creditProfiles) {
        log.debug("update");
        for (CreditProfile creditProfile : creditProfiles) {
            repository.save(creditProfile);
        }
        return creditProfiles;
    }
}
