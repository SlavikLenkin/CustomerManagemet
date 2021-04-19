package com.mycompany.service;

import com.mycompany.model.RelatedPartyDto;
import com.mycompany.repository.Customer;
import com.mycompany.repository.RelatedPartyRepository;
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
class RelatedPartyServiceTest {

    @MockBean
    RelatedPartyRepository relatedPartyRepository;

    @Autowired
    RelatedPartyService relatedPartyService;

    @Test
    void save() {
        Customer customer = new Customer();
        List<RelatedPartyDto> relatedPartyDtoList = new ArrayList<>();
        RelatedPartyDto relatedPartyDto = new RelatedPartyDto();
        relatedPartyDto.setName("relatedPartyDto");
        relatedPartyDtoList.add(relatedPartyDto);
        List<RelatedPartyDto> relatedPartyDtoListTest = relatedPartyService.save(relatedPartyDtoList, customer);

        for (RelatedPartyDto relatedPartyI : relatedPartyDtoListTest) {
            Assert.assertNotNull(relatedPartyI.getId());
            Assert.assertNotNull(relatedPartyI.getCustomer());
            Assert.assertEquals("relatedPartyDto", relatedPartyI.getName());
        }
    }

    @Test
    void update() {
        Customer customer = new Customer();
        List<RelatedPartyDto> relatedPartyDtoList = new ArrayList<>();
        RelatedPartyDto relatedPartyDto = new RelatedPartyDto();
        relatedPartyDto.setName("relatedPartyDto");
        relatedPartyDto.setCustomer(customer);
        relatedPartyDto.setHref("href");
        relatedPartyDto.setId("id");

        relatedPartyDtoList.add(relatedPartyDto);

        List<RelatedPartyDto> relatedPartyListUpdate = new ArrayList<>();
        RelatedPartyDto relatedPartyUpdate = new RelatedPartyDto();
        relatedPartyUpdate.setName("new relatedPartyDto");
        relatedPartyListUpdate.add(relatedPartyUpdate);
        relatedPartyDtoList.get(0).setName(relatedPartyListUpdate.get(0).getName());

        List<RelatedPartyDto> relatedPartyDtoListTest = relatedPartyService.update(relatedPartyDtoList);

        for (RelatedPartyDto relatedPartyI : relatedPartyDtoListTest) {
            Assert.assertNotNull(relatedPartyI.getId());
            Assert.assertNotNull(relatedPartyI.getCustomer());
            Assert.assertEquals("new relatedPartyDto", relatedPartyI.getName());
        }
    }
}
