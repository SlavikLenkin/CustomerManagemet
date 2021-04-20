package com.mycompany.service;

import com.mycompany.model.PaymentMethodDto;
import com.mycompany.repository.Customer;
import com.mycompany.repository.PaymentMethod;
import com.mycompany.repository.PaymentMethodRepository;
import com.mycompany.transfomer.PaymentMethodTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PaymentMethodService {

    final PaymentMethodRepository repository;
    final PaymentMethodTransformer paymentMethodTransformer;

    public PaymentMethodService(PaymentMethodRepository repository
            , PaymentMethodTransformer paymentMethodTransformer) {
        this.repository = repository;
        this.paymentMethodTransformer = paymentMethodTransformer;
    }

    public List<PaymentMethodDto> save(List<PaymentMethodDto> paymentMethodsDto, Customer customer) {
        log.debug("save");
        if (paymentMethodsDto == null) {
            return null;
        }
        int i = 0;
        for (PaymentMethodDto paymentMethodDto : paymentMethodsDto) {
            PaymentMethod paymentMethod = paymentMethodTransformer.transform(paymentMethodDto);
            paymentMethod.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            paymentMethod.setId(id);
            paymentMethod.setHref("https://host:port/tmf-api/customerManagement/v4/paymentMethod/" + id);
            repository.save(paymentMethod);
            paymentMethodsDto.set(i, paymentMethodTransformer.transform(paymentMethod));
            i++;
        }
        return paymentMethodsDto;
    }

    public void delete(PaymentMethodDto paymentMethodDto) {
        log.debug("delete");
        repository.delete(paymentMethodTransformer.transform(paymentMethodDto));
    }

    public List<PaymentMethodDto> update(List<PaymentMethodDto> paymentMethodsDto) {
        log.debug("update");
        for (PaymentMethodDto paymentMethodDto : paymentMethodsDto) {
            repository.save(paymentMethodTransformer.transform(paymentMethodDto));
        }
        return paymentMethodsDto;
    }
}


