package com.mycompany.service;


import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.ContactMediumRepository;
import com.mycompany.repository.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ContactMediumService {

    final
    ContactMediumRepository repository;


    public ContactMediumService(ContactMediumRepository repository) {
        this.repository = repository;
    }


    public ContactMedium saveOne(ContactMedium contactMedium, Customer customer) {
        log.info("saveOne");
        contactMedium.setCustomer(customer);
        String id = UUID.randomUUID().toString();
        contactMedium.setId(id);
        repository.save(contactMedium);
        return contactMedium;
    }

    public ContactMedium updateOne(ContactMedium contactMedium) {
        log.info("updateOne");
        repository.save(contactMedium);
        return contactMedium;
    }

    public void delete(ContactMedium contactMedium) {
        log.info("delete");
        repository.delete(contactMedium);
    }

}
