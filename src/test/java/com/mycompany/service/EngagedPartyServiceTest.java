package com.mycompany.service;

import com.mycompany.repository.Customer;
import com.mycompany.repository.EngagedParty;
import com.mycompany.repository.EngagedPartyRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
        EngagedParty engagedParty = new EngagedParty();
        engagedParty.setName("engagedParty");

        EngagedParty engagedParty1 = engagedPartyService.save(engagedParty, customer);

        EngagedParty engagedPartyUpdate = new EngagedParty();
        engagedPartyUpdate.setName("new engagedParty");

        engagedParty1.setName(engagedPartyUpdate.getName());

        EngagedParty engagedParty2 = engagedPartyService.update(engagedParty1);

        Assert.assertNotNull(engagedParty2.getId());
        Assert.assertNotNull(engagedParty2.getCustomer());
        Assert.assertNotNull(engagedParty2.getHref());
        Assert.assertEquals("new engagedParty", engagedParty2.getName());
        Mockito.verify(engagedPartyRepository, Mockito.times(1)).save(engagedParty2);
    }

    @Test
    void save() {
        Customer customer = new Customer();
        EngagedParty engagedParty = new EngagedParty();
        engagedParty.setName("engagedParty");

        EngagedParty engagedParty1 = engagedPartyService.save(engagedParty, customer);

        Assert.assertNotNull(engagedParty1.getId());
        Assert.assertNotNull(engagedParty1.getCustomer());
        Assert.assertNotNull(engagedParty1.getHref());
        Assert.assertEquals("engagedParty", engagedParty1.getName());
        Mockito.verify(engagedPartyRepository, Mockito.times(1)).save(engagedParty1);
    }

}