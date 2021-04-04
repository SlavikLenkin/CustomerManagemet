package com.mycompany.service;


import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.ContactMediumRepository;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactMediumService {

    final
    ContactMediumRepository repository;


    public ContactMediumService(ContactMediumRepository repository) {
        this.repository = repository;
    }


   /* public List<ContactMedium> save(List<ContactMedium> contactsMedium, Customer customer) {
        if (contactsMedium == null) {
            return contactsMedium;
        }
        for (ContactMedium contactMedium : contactsMedium) {
            contactMedium.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            contactMedium.setId(id);
            repository.save(contactMedium);
        }
        return contactsMedium;
    }*/

    public ContactMedium saveOne(ContactMedium contactMedium, Customer customer) {
        contactMedium.setCustomer(customer);
        String id = UUID.randomUUID().toString();
        contactMedium.setId(id);
        repository.save(contactMedium);
        return contactMedium;
    }

    public ContactMedium updateOne(ContactMedium contactMedium) {
        repository.save(contactMedium);
        return contactMedium;
    }

    public void delete(ContactMedium contactMedium) {
        repository.delete(contactMedium);
    }

   /* public List<ContactMedium> update(List<ContactMedium> contactMediumList) {
        for (ContactMedium contactMedium : contactMediumList) {
            repository.save(contactMedium);
        }
        return contactMediumList;
    }*/
}
