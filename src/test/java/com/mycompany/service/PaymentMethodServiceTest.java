package com.mycompany.service;

import com.mycompany.model.PaymentMethodDto;
import com.mycompany.repository.Customer;
import com.mycompany.repository.PaymentMethodRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PaymentMethodServiceTest {

    @MockBean
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Test
    void save() {

        Customer customer = new Customer();
        List<PaymentMethodDto> paymentMethodsDto = new ArrayList<>();
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setName("paymentMethodDto");
        paymentMethodsDto.add(paymentMethodDto);
        paymentMethodDto.setCustomer(customer);

        List<PaymentMethodDto> paymentMethodsTest = paymentMethodService.save(paymentMethodsDto, customer);

        for (PaymentMethodDto paymentMethodI : paymentMethodsTest) {
            Assert.assertNotNull(paymentMethodI.getId());
            Assert.assertNotNull(paymentMethodI.getCustomer());
            Assert.assertEquals("paymentMethodDto", paymentMethodI.getName());
        }

    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<PaymentMethodDto> paymentMethodsDto = new ArrayList<>();
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setName("paymentMethodDto");
        paymentMethodsDto.add(paymentMethodDto);
        paymentMethodDto.setCustomer(customer);
        paymentMethodDto.setId("id");
        paymentMethodDto.setHref("href");

        List<PaymentMethodDto> paymentMethodsUpdate = new ArrayList<>();
        PaymentMethodDto paymentMethodUpdate = new PaymentMethodDto();
        paymentMethodUpdate.setName("new paymentMethodDto");

        paymentMethodsUpdate.add(paymentMethodUpdate);

        paymentMethodsDto.get(0).setName(paymentMethodsUpdate.get(0).getName());

        List<PaymentMethodDto> paymentMethodsTest = paymentMethodService.update(paymentMethodsDto);

        for (PaymentMethodDto paymentMethodI : paymentMethodsTest) {
            Assert.assertNotNull(paymentMethodI.getId());
            Assert.assertNotNull(paymentMethodI.getCustomer());
            Assert.assertEquals("new paymentMethodDto", paymentMethodI.getName());
        }
    }
}

