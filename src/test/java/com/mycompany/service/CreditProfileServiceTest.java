package com.mycompany.service;

import com.mycompany.repository.CreditProfile;
import com.mycompany.repository.CreditProfileRepository;
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
class CreditProfileServiceTest {

    @MockBean
    CreditProfileRepository creditProfileRepository;

    @Autowired
    private CreditProfileService creditProfileService;

    @Test
    void save() {
        Customer customer = new Customer();
        List<CreditProfile> creditProfiles = new ArrayList<>();
        CreditProfile creditProfile = new CreditProfile();
        creditProfile.setCreditScore(6);

        creditProfiles.add(creditProfile);

        List<CreditProfile> creditProfiles1 = creditProfileService.save(creditProfiles, customer);

        for (CreditProfile creditProfileI : creditProfiles1) {
            Assert.assertNotNull(creditProfileI.getId());
            Assert.assertNotNull(creditProfileI.getCustomer());
            Assert.assertNotNull(creditProfileI.getCreditScore());
            Assert.assertEquals(6, creditProfile.getCreditScore());
            Mockito.verify(creditProfileRepository, Mockito.times(1)).save(creditProfileI);
        }

    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<CreditProfile> creditProfiles = new ArrayList<>();
        CreditProfile creditProfile = new CreditProfile();
        creditProfile.setCreditScore(6);

        creditProfiles.add(creditProfile);

        List<CreditProfile> creditProfiles1 = creditProfileService.save(creditProfiles, customer);

        List<CreditProfile> creditProfilesUpdate = new ArrayList<>();
        CreditProfile creditProfileUpdate = new CreditProfile();
        creditProfileUpdate.setCreditScore(7);

        creditProfilesUpdate.add(creditProfileUpdate);
        creditProfiles1.get(0).setCreditScore(creditProfilesUpdate.get(0).getCreditScore());

        List<CreditProfile> creditProfiles2 = creditProfileService.update(creditProfiles1);

        for (CreditProfile creditProfileI : creditProfiles2) {
            Assert.assertNotNull(creditProfileI.getId());
            Assert.assertNotNull(creditProfileI.getCustomer());
            Assert.assertNotNull(creditProfileI.getCreditScore());
            Assert.assertEquals(7, creditProfile.getCreditScore());
            Mockito.verify(creditProfileRepository, Mockito.times(2)).save(creditProfileI);
        }


    }
}