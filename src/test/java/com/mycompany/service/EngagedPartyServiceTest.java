package com.mycompany.service;

import com.mycompany.model.EngagedPartyDto;
import com.mycompany.repository.Customer;
import com.mycompany.repository.EngagedParty;
import com.mycompany.repository.EngagedPartyRepository;
import com.mycompany.transfomer.EngagedPartyTransformer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class EngagedPartyServiceTest {

    @Mock
    EngagedPartyRepository engagedPartyRepository;

    @Mock
    EngagedPartyTransformer engagedPartyTransformer;

    @InjectMocks
    private EngagedPartyService engagedPartyService;

    @Test
    void update() {
        EngagedParty engagedParty = new EngagedParty();
        EngagedPartyDto engagedPartyDto = new EngagedPartyDto();

        Mockito.when(engagedPartyTransformer.transform(engagedPartyDto)).thenReturn(engagedParty);
        Mockito.when(engagedPartyRepository.save(engagedParty)).thenReturn(engagedParty);

        engagedPartyService.update(engagedPartyDto);

        Mockito.verify(engagedPartyTransformer).transform(engagedPartyDto);
        Mockito.verify(engagedPartyRepository).save(engagedParty);
    }

    @Test
    void save() {
        Customer customer = new Customer();
        EngagedParty engagedParty = new EngagedParty();
        EngagedPartyDto engagedPartyDto = new EngagedPartyDto();

        Mockito.when(engagedPartyTransformer.transform(engagedPartyDto)).thenReturn(engagedParty);
        Mockito.when(engagedPartyTransformer.transform(engagedParty)).thenReturn(engagedPartyDto);
        Mockito.when(engagedPartyRepository.save(engagedParty)).thenReturn(engagedParty);

        engagedPartyService.save(engagedPartyDto, customer);

        Mockito.verify(engagedPartyTransformer).transform(engagedPartyDto);
        Mockito.verify(engagedPartyTransformer).transform(engagedParty);
        Mockito.verify(engagedPartyRepository).save(engagedParty);
    }
}
