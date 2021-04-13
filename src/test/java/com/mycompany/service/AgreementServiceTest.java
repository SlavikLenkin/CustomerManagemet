package com.mycompany.service;

import com.mycompany.model.AgreementDto;
import com.mycompany.repository.AgreementRepository;
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
class AgreementServiceTest {

    @MockBean
    AgreementRepository agreementRepository;

    @Autowired
    private AgreementService agreementService;

    @Test
    void save() {
        Customer customer = new Customer();
        List<AgreementDto> agreementsDto = new ArrayList<>();
        AgreementDto agreementDto = new AgreementDto();
        agreementDto.setName("agreement");
        agreementDto.setCustomer(customer);

        agreementsDto.add(agreementDto);

        List<AgreementDto> agreementsDtoTest = agreementService.save(agreementsDto, customer);

        for (AgreementDto agreementI : agreementsDtoTest) {
            Assert.assertNotNull(agreementI.getId());
            Assert.assertNotNull(agreementI.getHref());
            Assert.assertNotNull(agreementI.getName());
            Assert.assertEquals("agreement", agreementI.getName());
        }

    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<AgreementDto> agreementsDto = new ArrayList<>();
        AgreementDto agreementDto = new AgreementDto();
        agreementDto.setName("agreement");
        agreementDto.setId("id");
        agreementDto.setHref("href");
        agreementDto.setCustomer(customer);
        agreementsDto.add(agreementDto);

        List<AgreementDto> agreementsUpdate = new ArrayList<>();
        AgreementDto agreementUpdate = new AgreementDto();
        agreementUpdate.setName("new agreement");
        agreementsUpdate.add(agreementUpdate);
        agreementsDto.get(0).setName(agreementsUpdate.get(0).getName());

        List<AgreementDto> agreementsTest = agreementService.update(agreementsDto);

        for (AgreementDto agreementI : agreementsTest) {
            Assert.assertNotNull(agreementI.getId());
            Assert.assertNotNull(agreementI.getHref());
            Assert.assertNotNull(agreementI.getName());
            Assert.assertEquals("new agreement", agreementI.getName());
        }

    }
}
