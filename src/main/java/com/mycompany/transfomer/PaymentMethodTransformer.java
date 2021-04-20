package com.mycompany.transfomer;

import com.mycompany.model.PaymentMethodDto;
import com.mycompany.repository.PaymentMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodTransformer {

    public PaymentMethod transform(PaymentMethodDto paymentMethodDto) {
        if (paymentMethodDto == null) {
            return null;
        }
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(paymentMethodDto.getId());
        paymentMethod.setHref(paymentMethodDto.getHref());
        paymentMethod.setName(paymentMethodDto.getName().orElse(null));
        paymentMethod.setCustomer(paymentMethodDto.getCustomer());
        return paymentMethod;
    }

    public PaymentMethodDto transform(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setId(paymentMethod.getId());
        paymentMethodDto.setHref(paymentMethod.getHref());
        paymentMethodDto.setName(Optional.ofNullable(paymentMethod.getName()));
        paymentMethodDto.setCustomer(paymentMethod.getCustomer());
        return paymentMethodDto;
    }

    public List<PaymentMethodDto> transform(List<PaymentMethod> paymentMethodList) {
        if (paymentMethodList == null) {
            return null;
        }
        List<PaymentMethodDto> paymentMethodDtoList = new ArrayList<>();
        for (PaymentMethod paymentMethod : paymentMethodList) {
            paymentMethodDtoList.add(transform(paymentMethod));
        }
        return paymentMethodDtoList;
    }
}
