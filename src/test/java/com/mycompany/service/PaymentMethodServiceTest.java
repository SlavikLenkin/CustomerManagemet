package com.mycompany.service;

import com.mycompany.model.PaymentMethodDto;
import com.mycompany.repository.Customer;
import com.mycompany.repository.PaymentMethod;
import com.mycompany.repository.PaymentMethodRepository;
import com.mycompany.transfomer.PaymentMethodTransformer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PaymentMethodServiceTest {

    @Mock
    PaymentMethodRepository paymentMethodRepository;

    @Mock
    PaymentMethodTransformer paymentMethodTransformer;

    @InjectMocks
    private PaymentMethodService paymentMethodService;

    @Test
    void save() {
        Customer customer = new Customer();
        PaymentMethod paymentMethod = new PaymentMethod();
        List<PaymentMethodDto> paymentMethodsDto = new ArrayList<>();
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodsDto.add(paymentMethodDto);

        Mockito.when(paymentMethodTransformer.transform(paymentMethodDto)).thenReturn(paymentMethod);
        Mockito.when(paymentMethodTransformer.transform(paymentMethod)).thenReturn(paymentMethodDto);
        Mockito.when(paymentMethodRepository.save(paymentMethod)).thenReturn(paymentMethod);

        paymentMethodService.save(paymentMethodsDto, customer);

        Mockito.verify(paymentMethodTransformer).transform(paymentMethodDto);
        Mockito.verify(paymentMethodTransformer).transform(paymentMethod);
        Mockito.verify(paymentMethodRepository).save(paymentMethod);
    }

    @Test
    void update() {
        PaymentMethod paymentMethod = new PaymentMethod();
        List<PaymentMethodDto> paymentMethodsDto = new ArrayList<>();
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodsDto.add(paymentMethodDto);

        Mockito.when(paymentMethodTransformer.transform(paymentMethodDto)).thenReturn(paymentMethod);
        Mockito.when(paymentMethodRepository.save(paymentMethod)).thenReturn(paymentMethod);

        paymentMethodService.update(paymentMethodsDto);

        Mockito.verify(paymentMethodTransformer).transform(paymentMethodDto);
        Mockito.verify(paymentMethodRepository).save(paymentMethod);
    }
}

