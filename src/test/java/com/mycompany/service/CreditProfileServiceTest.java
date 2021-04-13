package com.mycompany.service;

import com.mycompany.model.CreditProfileDto;
import com.mycompany.repository.CreditProfileRepository;
import com.mycompany.repository.Customer;
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
class CreditProfileServiceTest {

    @MockBean
    CreditProfileRepository creditProfileRepository;

    @Autowired
    private CreditProfileService creditProfileService;

    @Test
    void save() {
        Customer customer = new Customer();
        List<CreditProfileDto> creditProfilesDto = new ArrayList<>();
        CreditProfileDto creditProfileDto = new CreditProfileDto();
        creditProfileDto.setCreditScore(6);

        creditProfilesDto.add(creditProfileDto);

        List<CreditProfileDto> creditProfilesTest = creditProfileService.save(creditProfilesDto, customer);

        for (CreditProfileDto creditProfileI : creditProfilesTest) {
            Assert.assertNotNull(creditProfileI.getId());
            Assert.assertNotNull(creditProfileI.getCustomer());
            Assert.assertNotNull(creditProfileI.getCreditScore());
            Assert.assertEquals(6, creditProfileDto.getCreditScore());
        }

    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<CreditProfileDto> creditProfilesDto = new ArrayList<>();
        CreditProfileDto creditProfileDto = new CreditProfileDto();
        creditProfileDto.setCreditScore(6);
        creditProfileDto.setId("id");
        creditProfileDto.setCustomer(customer);

        creditProfilesDto.add(creditProfileDto);

        List<CreditProfileDto> creditProfilesUpdate = new ArrayList<>();
        CreditProfileDto creditProfileUpdate = new CreditProfileDto();
        creditProfileUpdate.setCreditScore(7);
        creditProfilesUpdate.add(creditProfileUpdate);
        creditProfilesDto.get(0).setCreditScore(creditProfilesUpdate.get(0).getCreditScore());

        List<CreditProfileDto> creditProfilesTest = creditProfileService.update(creditProfilesDto);

        for (CreditProfileDto creditProfileI : creditProfilesTest) {
            Assert.assertNotNull(creditProfileI.getId());
            Assert.assertNotNull(creditProfileI.getCustomer());
            Assert.assertNotNull(creditProfileI.getCreditScore());
            Assert.assertEquals(7, creditProfileDto.getCreditScore());
        }
    }
}
