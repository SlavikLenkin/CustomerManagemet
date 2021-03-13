package com.mycompany.service;


import com.mycompany.repository.Customer;
import com.mycompany.repository.RelatedParty;
import com.mycompany.repository.RelatedPartyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RelatedPartyService {

    final
    RelatedPartyRepository repository;

    public RelatedPartyService(RelatedPartyRepository repository) {
        this.repository = repository;
    }

    public List<RelatedParty> findAllRelatedParties(Customer customer) {
        List<RelatedParty> relatedParties = new ArrayList<>();
        if (customer.getRelatedPartyId() == null)
            return relatedParties;
        relatedParties = repository.findRelatedPartyById(customer.getRelatedPartyId());
        return relatedParties;
    }

    public List<RelatedParty> save(List<RelatedParty> relatedParties) {
        if (relatedParties == null) {
            return null;
        }
        for (RelatedParty relatedParty : relatedParties) {
            String id = UUID.randomUUID().toString();
            relatedParty.setId(id);
            relatedParty.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
            repository.save(relatedParty);
        }
        return relatedParties;
    }

    public List<RelatedParty> update(List<RelatedParty> relatedParties){
        for (RelatedParty relatedParty : relatedParties){
            repository.save(relatedParty);
        }
        return relatedParties;
    }

    public void delete(RelatedParty relatedParty) {
        repository.delete(relatedParty);
    }

}
