package com.mycompany.service;

import com.mycompany.repository.Agreement;
import com.mycompany.repository.AgreementRepository;
import com.mycompany.repository.Customer;
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
class AgreementServiceTest {

    @MockBean
    AgreementRepository agreementRepository;

    @Autowired
    private AgreementService agreementService;

    @Test
    void save() {
        Customer customer = new Customer();
        List<Agreement> agreements = new ArrayList<>();
        Agreement agreement = new Agreement();
        agreement.setName("agreement");
        agreement.setCustomer(customer);

        agreements.add(agreement);

        List<Agreement> agreements1 = agreementService.save(agreements, customer);

        for (Agreement agreementI : agreements1) {
            Assert.assertNotNull(agreementI.getId());
            Assert.assertNotNull(agreementI.getHref());
            Assert.assertNotNull(agreementI.getName());
            Assert.assertEquals("agreement", agreementI.getName());
            Mockito.verify(agreementRepository).save(agreementI);
        }

    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<Agreement> agreements = new ArrayList<>();
        Agreement agreement = new Agreement();
        agreement.setName("agreement");
        agreement.setId("id");
        agreement.setHref("href");
        agreement.setCustomer(customer);
        agreements.add(agreement);

        List<Agreement> agreementsUpdate = new ArrayList<>();
        Agreement agreementUpdate = new Agreement();
        agreementUpdate.setName("new agreement");
        agreementsUpdate.add(agreementUpdate);
        agreements.get(0).setName(agreementsUpdate.get(0).getName());

        List<Agreement> agreements2 = agreementService.update(agreements);

        for (Agreement agreementI : agreements2) {
            Assert.assertNotNull(agreementI.getId());
            Assert.assertNotNull(agreementI.getHref());
            Assert.assertNotNull(agreementI.getName());
            Assert.assertEquals("new agreement", agreementI.getName());
            Mockito.verify(agreementRepository).save(agreementI);
        }

    }
}