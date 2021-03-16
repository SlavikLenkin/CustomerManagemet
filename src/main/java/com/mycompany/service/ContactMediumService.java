package com.mycompany.service;


import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.ContactMediumRepository;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContactMediumService {

    final
    ContactMediumRepository repository;


    public ContactMediumService(ContactMediumRepository repository) {
        this.repository = repository;
    }

//    public List<ContactMedium> findAllContactsMedium(Customer customer) {
//        List<ContactMedium> contactsMedium = new ArrayList<>();
//        if (customer.getContactMediumId() == null) {
//            return contactsMedium;
//        }
//        contactsMedium = repository.findContactMediumById(customer.getContactMediumId());
//        return contactsMedium;
//    }

    public List<ContactMedium> save(List<ContactMedium> contactsMedium) {
        if (contactsMedium == null) {
            return contactsMedium;
        }
        for (ContactMedium contactMedium : contactsMedium) {
            String id = UUID.randomUUID().toString();
            contactMedium.setId(id);
            repository.save(contactMedium);
        }
        return contactsMedium;
    }

    public void delete(ContactMedium contactMedium) {
        repository.delete(contactMedium);
    }

    public List<ContactMedium> update(List<ContactMedium> contactMediumList) {
        for (ContactMedium contactMedium : contactMediumList){
            repository.save(contactMedium);
        }
        return contactMediumList;
    }
}
