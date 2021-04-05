package com.mycompany.service;

import com.mycompany.repository.Customer;
import com.mycompany.repository.PaymentMethod;
import com.mycompany.repository.PaymentMethodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PaymentMethodService {

    final
    PaymentMethodRepository repository;

    public PaymentMethodService(PaymentMethodRepository repository) {
        this.repository = repository;
    }

    public List<PaymentMethod> save(List<PaymentMethod> paymentMethods, Customer customer) {
        log.debug("save");
        if (paymentMethods == null) {
            return null;
        }
        for (PaymentMethod paymentMethod : paymentMethods) {
            paymentMethod.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            paymentMethod.setId(id);
            paymentMethod.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
            repository.save(paymentMethod);
        }
        return paymentMethods;
    }

    public void delete(PaymentMethod paymentMethod) {
        log.debug("delete");
        repository.delete(paymentMethod);
    }

    public List<PaymentMethod> update(List<PaymentMethod> paymentMethods) {
        log.debug("update");
        for (PaymentMethod paymentMethod : paymentMethods) {
            repository.save(paymentMethod);
        }
        return paymentMethods;
    }
}


