package com.mycompany.service;

import com.mycompany.repository.CreditProfile;
import com.mycompany.repository.CreditProfileRepository;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CreditProfileService {

    private final CreditProfileRepository repository;

    public CreditProfileService(CreditProfileRepository repository) {
        this.repository = repository;
    }


    public List<CreditProfile> save(List<CreditProfile> creditProfiles, Customer customer) {
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
        repository.delete(creditProfile);
    }

    public List<CreditProfile> update(List<CreditProfile> creditProfiles) {
        for (CreditProfile creditProfile : creditProfiles) {
            repository.save(creditProfile);
        }
        return creditProfiles;
    }
}
