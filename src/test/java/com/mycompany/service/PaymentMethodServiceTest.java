package com.mycompany.service;

import com.mycompany.repository.Customer;
import com.mycompany.repository.PaymentMethod;
import com.mycompany.repository.PaymentMethodRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName("paymentMethod");
        paymentMethods.add(paymentMethod);

        List<PaymentMethod> paymentMethods1 = paymentMethodService.save(paymentMethods, customer);

        for (PaymentMethod paymentMethodI : paymentMethods1) {
            Assert.assertNotNull(paymentMethodI.getId());
            Assert.assertNotNull(paymentMethodI.getCustomer());
            Assert.assertEquals("paymentMethod", paymentMethodI.getName());
            Mockito.verify(paymentMethodRepository).save(paymentMethodI);
        }
    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName("paymentMethod");
        paymentMethods.add(paymentMethod);
        paymentMethod.setCustomer(customer);
        paymentMethod.setId("id");
        paymentMethod.setHref("href");

        List<PaymentMethod> paymentMethodsUpdate = new ArrayList<>();
        PaymentMethod paymentMethodUpdate = new PaymentMethod();
        paymentMethodUpdate.setName("new paymentMethod");

        paymentMethodsUpdate.add(paymentMethodUpdate);

        paymentMethods.get(0).setName(paymentMethodsUpdate.get(0).getName());

        List<PaymentMethod> paymentMethods2 = paymentMethodService.update(paymentMethods);

        for (PaymentMethod paymentMethodI : paymentMethods2) {
            Assert.assertNotNull(paymentMethodI.getId());
            Assert.assertNotNull(paymentMethodI.getCustomer());
            Assert.assertEquals("new paymentMethod", paymentMethodI.getName());
            Mockito.verify(paymentMethodRepository).save(paymentMethodI);
        }
    }
}