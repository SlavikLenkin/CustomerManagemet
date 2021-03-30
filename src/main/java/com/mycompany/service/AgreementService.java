package com.mycompany.service;

import com.mycompany.repository.Agreement;
import com.mycompany.repository.AgreementRepository;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgreementService {

    final
    AgreementRepository repository;


    public AgreementService(AgreementRepository repository) {
        this.repository = repository;
    }


    public List<Agreement> save(List<Agreement> agreements, Customer customer) {
        if (agreements == null) {
            return null;
        }
        for (Agreement agreement : agreements) {
            agreement.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            agreement.setId(id);
            agreement.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
            repository.save(agreement);
        }
        return agreements;
    }

    public void delete(Agreement agreement) {
        repository.delete(agreement);
    }

    public List<Agreement> update(List<Agreement> agreements) {
        for (Agreement agreement : agreements) {
            repository.save(agreement);
        }
        return agreements;
    }
}
