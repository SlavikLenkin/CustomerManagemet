package com.mycompany.service;

import com.mycompany.model.EngagedPartyDto;
import com.mycompany.repository.Customer;
import com.mycompany.repository.EngagedParty;
import com.mycompany.repository.EngagedPartyRepository;
import com.mycompany.transfomer.EngagedPartyTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class EngagedPartyService {

    private final EngagedPartyRepository repository;
    private final EngagedPartyTransformer engagedPartyTransformer;

    public EngagedPartyService(EngagedPartyRepository repository, EngagedPartyTransformer engagedPartyTransformer) {
        this.repository = repository;
        this.engagedPartyTransformer = engagedPartyTransformer;
    }

    public EngagedPartyDto update(EngagedPartyDto engagedPartyDto) {
        log.debug("update");
        repository.save(engagedPartyTransformer.transform(engagedPartyDto));
        return engagedPartyDto;
    }

    public EngagedPartyDto save(EngagedPartyDto engagedPartyDto, Customer customer) {
        log.debug("save");
        if (engagedPartyDto == null) {
            return null;
        }
        EngagedParty engagedParty = engagedPartyTransformer.transform(engagedPartyDto);
        engagedParty.setCustomer(customer);
        String id = UUID.randomUUID().toString();
        engagedParty.setId(id);
        engagedParty.setHref("https://host:port/tmf-api/customerManagement/v4/engagedParty/" + id);
        repository.save(engagedParty);
        return engagedPartyTransformer.transform(engagedParty);
    }

    public void delete(EngagedPartyDto engagedPartyDto) {
        log.debug("delete");
        repository.delete(engagedPartyTransformer.transform(engagedPartyDto));
    }
}
