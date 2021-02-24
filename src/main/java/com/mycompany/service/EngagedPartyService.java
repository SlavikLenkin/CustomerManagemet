package com.mycompany.service;

import com.mycompany.repository.Account;
import com.mycompany.repository.Customer;
import com.mycompany.repository.EngagedParty;
import com.mycompany.repository.EngagedPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EngagedPartyService {

    private final EngagedPartyRepository repository;


    public EngagedPartyService(EngagedPartyRepository repository) {
        this.repository = repository;
    }

    public EngagedParty findEngagedPartyById(String id) {
        return repository.findEngagedPartyById(id);
    }

    public EngagedParty findEngagedParty(Customer customer){
        EngagedParty engagedParty = new EngagedParty();
        if (customer.getEngagedPartyId() == null)
            return engagedParty;
        String str = customer.getEngagedPartyId();
        System.out.println(customer.getEngagedPartyId());
        return findEngagedPartyById(str);
    }



}
