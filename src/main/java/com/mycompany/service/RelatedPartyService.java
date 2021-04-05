package com.mycompany.service;


import com.mycompany.repository.Customer;
import com.mycompany.repository.RelatedParty;
import com.mycompany.repository.RelatedPartyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RelatedPartyService {

    final
    RelatedPartyRepository repository;

    public RelatedPartyService(RelatedPartyRepository repository) {
        this.repository = repository;
    }


    public List<RelatedParty> save(List<RelatedParty> relatedParties, Customer customer) {
        log.info("save");
        if (relatedParties == null) {
            return null;
        }
        for (RelatedParty relatedParty : relatedParties) {
            relatedParty.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            relatedParty.setId(id);
            relatedParty.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
            repository.save(relatedParty);
        }
        return relatedParties;
    }

    public List<RelatedParty> update(List<RelatedParty> relatedParties) {
        log.info("update");
        for (RelatedParty relatedParty : relatedParties) {
            repository.save(relatedParty);
        }
        return relatedParties;
    }

    public void delete(RelatedParty relatedParty) {
        log.info("delete");
        repository.delete(relatedParty);
    }

}
