package com.mycompany.service;

import com.mycompany.repository.Customer;
import com.mycompany.repository.PaymentMethod;
import com.mycompany.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentMethodService {

    final
    PaymentMethodRepository repository;

    public PaymentMethodService(PaymentMethodRepository repository) {
        this.repository = repository;
    }

//    public List<PaymentMethod> findAllPaymentMethods(Customer customer) {
//        List<PaymentMethod> paymentMethods = new ArrayList<>();
//        if (customer.getPayMethodId() == null)
//            return paymentMethods;
//        paymentMethods = repository.findPaymentMethodById(customer.getPayMethodId());
//        return paymentMethods;
//    }

    public List<PaymentMethod> save(List<PaymentMethod> paymentMethods) {
        if (paymentMethods == null) {
            return null;
        }
        for (PaymentMethod paymentMethod : paymentMethods) {
            String id = UUID.randomUUID().toString();
            paymentMethod.setId(id);
            paymentMethod.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
            repository.save(paymentMethod);
        }
        return paymentMethods;
    }

    public void delete(PaymentMethod paymentMethod) {
        repository.delete(paymentMethod);
    }

    public List<PaymentMethod> update(List<PaymentMethod> paymentMethods) {
        for (PaymentMethod paymentMethod : paymentMethods){
            repository.save(paymentMethod);
        }
        return paymentMethods;
    }
}


