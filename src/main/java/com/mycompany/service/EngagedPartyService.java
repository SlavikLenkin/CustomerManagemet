package com.mycompany.service;

import com.mycompany.repository.Customer;
import com.mycompany.repository.EngagedParty;
import com.mycompany.repository.EngagedPartyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EngagedPartyService {

    private final EngagedPartyRepository repository;


    public EngagedPartyService(EngagedPartyRepository repository) {
        this.repository = repository;
    }

    public EngagedParty findEngagedPartyById(String id) {
        return repository.findEngagedPartyById(id);
    }


    public EngagedParty update(EngagedParty engagedParty) {
        repository.save(engagedParty);
        return engagedParty;
    }

    public EngagedParty save(EngagedParty engagedParty, Customer customer) {
        if (engagedParty == null) {
            return null;
        }
        engagedParty.setCustomer(customer);
        String id = UUID.randomUUID().toString();
        engagedParty.setId(id);
        engagedParty.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
        repository.save(engagedParty);
        return engagedParty;
    }

    public void delete(EngagedParty engagedParty) {
        repository.delete(engagedParty);
    }


}
