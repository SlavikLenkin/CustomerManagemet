package com.mycompany.service;

import com.mycompany.repository.Customer;
import com.mycompany.repository.RelatedParty;
import com.mycompany.repository.RelatedPartyRepository;
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
class RelatedPartyServiceTest {

    @MockBean
    RelatedPartyRepository relatedPartyRepository;

    @Autowired
    RelatedPartyService relatedPartyService;

    @Test
    void save() {
        Customer customer = new Customer();
        List<RelatedParty> relatedPartyList = new ArrayList<>();
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setName("relatedParty");

        relatedPartyList.add(relatedParty);

        List<RelatedParty> relatedPartyList1 = relatedPartyService.save(relatedPartyList, customer);

        for (RelatedParty relatedPartyI : relatedPartyList1) {
            Assert.assertNotNull(relatedPartyI.getId());
            Assert.assertNotNull(relatedPartyI.getCustomer());
            Assert.assertEquals("relatedParty", relatedPartyI.getName());
            Mockito.verify(relatedPartyRepository, Mockito.times(1)).save(relatedPartyI);
        }

    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<RelatedParty> relatedPartyList = new ArrayList<>();
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setName("relatedParty");

        relatedPartyList.add(relatedParty);

        List<RelatedParty> relatedPartyList1 = relatedPartyService.save(relatedPartyList, customer);

        List<RelatedParty> relatedPartyListUpdate = new ArrayList<>();
        RelatedParty relatedPartyUpdate = new RelatedParty();
        relatedPartyUpdate.setName("new relatedParty");

        relatedPartyListUpdate.add(relatedPartyUpdate);
        relatedPartyList1.get(0).setName(relatedPartyListUpdate.get(0).getName());

        List<RelatedParty> relatedPartyList2 = relatedPartyService.update(relatedPartyList1);

        for (RelatedParty relatedPartyI : relatedPartyList2) {
            Assert.assertNotNull(relatedPartyI.getId());
            Assert.assertNotNull(relatedPartyI.getCustomer());
            Assert.assertEquals("new relatedParty", relatedPartyI.getName());
            Mockito.verify(relatedPartyRepository, Mockito.times(2)).save(relatedPartyI);
        }


    }
}