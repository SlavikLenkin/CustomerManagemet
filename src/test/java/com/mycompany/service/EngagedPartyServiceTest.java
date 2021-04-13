package com.mycompany.service;

import com.mycompany.model.EngagedPartyDto;
import com.mycompany.repository.Customer;
import com.mycompany.repository.EngagedPartyRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class EngagedPartyServiceTest {

    @MockBean
    EngagedPartyRepository engagedPartyRepository;

    @Autowired
    private EngagedPartyService engagedPartyService;

    @Test
    void update() {
        Customer customer = new Customer();
        EngagedPartyDto engagedPartyDto = new EngagedPartyDto();
        engagedPartyDto.setName("engagedPartyDto");
        engagedPartyDto.setCustomer(customer);
        engagedPartyDto.setId("id");
        engagedPartyDto.setHref("href");

        EngagedPartyDto engagedPartyUpdate = new EngagedPartyDto();
        engagedPartyUpdate.setName("new engagedPartyDto");

        engagedPartyDto.setName(engagedPartyUpdate.getName());

        EngagedPartyDto engagedPartyTest = engagedPartyService.update(engagedPartyDto);

        Assert.assertNotNull(engagedPartyTest.getId());
        Assert.assertNotNull(engagedPartyTest.getCustomer());
        Assert.assertNotNull(engagedPartyTest.getHref());
        Assert.assertEquals("new engagedPartyDto", engagedPartyTest.getName());
    }

    @Test
    void save() {
        Customer customer = new Customer();
        EngagedPartyDto engagedPartyDto = new EngagedPartyDto();
        engagedPartyDto.setName("engagedPartyDto");

        EngagedPartyDto engagedPartyTest = engagedPartyService.save(engagedPartyDto, customer);
        Assert.assertNotNull(engagedPartyTest.getId());
        Assert.assertNotNull(engagedPartyTest.getCustomer());
        Assert.assertNotNull(engagedPartyTest.getHref());
        Assert.assertEquals("engagedPartyDto", engagedPartyTest.getName());
    }

}
