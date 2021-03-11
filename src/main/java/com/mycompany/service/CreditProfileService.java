package com.mycompany.service;

import com.mycompany.repository.CreditProfile;
import com.mycompany.repository.CreditProfileRepository;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CreditProfileService {

    private final CreditProfileRepository repository;

    public CreditProfileService(CreditProfileRepository repository) {
        this.repository = repository;
    }

    public List<CreditProfile> findAllCreditProfile(Customer customer) {
        List<CreditProfile> creditProfiles = new ArrayList<>();
        if (customer.getCreditProfileId() == null) {
            return creditProfiles;
        }
        creditProfiles = repository.findCreditProfileById(customer.getCreditProfileId());
        return creditProfiles;
    }

    public List<CreditProfile> save(List<CreditProfile> creditProfiles) {
        if (creditProfiles == null) {
            return null;
        }
        for (CreditProfile creditProfile : creditProfiles) {
            String id = UUID.randomUUID().toString();
            creditProfile.setId(id);
            repository.save(creditProfile);
        }
        return creditProfiles;
    }

    public void delete(CreditProfile creditProfile) {
        repository.delete(creditProfile);
    }

}
