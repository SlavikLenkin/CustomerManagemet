package com.mycompany.service;

import com.mycompany.repository.Agreement;
import com.mycompany.repository.AgreementRepository;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AgreementService {

    final
    AgreementRepository repository;


    public AgreementService(AgreementRepository repository) {
        this.repository = repository;
    }


    public List<Agreement> findAllAgreements(Customer customer) {
        List<Agreement> agreements = new ArrayList<>();
        if (customer.getAgreementId() == null) {
            return agreements;
        }
        agreements = repository.findAgreementById(customer.getAgreementId());
        return agreements;
    }

    public List<Agreement> save(List<Agreement> agreements) {
        if (agreements == null) {
            return null;
        }
        for (Agreement agreement : agreements) {
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

}
