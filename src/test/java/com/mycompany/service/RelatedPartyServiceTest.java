package com.mycompany.service;

import com.mycompany.model.RelatedPartyDto;
import com.mycompany.repository.Customer;
import com.mycompany.repository.RelatedParty;
import com.mycompany.repository.RelatedPartyRepository;
import com.mycompany.transfomer.RelatedPartyTransformer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RelatedPartyServiceTest {

    @Mock
    RelatedPartyRepository relatedPartyRepository;

    @Mock
    RelatedPartyTransformer relatedPartyTransformer;

    @InjectMocks
    RelatedPartyService relatedPartyService;

    @Test
    void save() {
        Customer customer = new Customer();
        RelatedParty relatedParty = new RelatedParty();
        List<RelatedPartyDto> relatedPartyDtoList = new ArrayList<>();
        RelatedPartyDto relatedPartyDto = new RelatedPartyDto();
        relatedPartyDtoList.add(relatedPartyDto);

        Mockito.when(relatedPartyTransformer.transform(relatedPartyDto)).thenReturn(relatedParty);
        Mockito.when(relatedPartyTransformer.transform(relatedParty)).thenReturn(relatedPartyDto);
        Mockito.when(relatedPartyRepository.save(relatedParty)).thenReturn(relatedParty);

        relatedPartyService.save(relatedPartyDtoList, customer);

        Mockito.verify(relatedPartyTransformer).transform(relatedPartyDto);
        Mockito.verify(relatedPartyTransformer).transform(relatedParty);
        Mockito.verify(relatedPartyRepository).save(relatedParty);
    }

    @Test
    void update() {
        RelatedParty relatedParty = new RelatedParty();
        List<RelatedPartyDto> relatedPartyDtoList = new ArrayList<>();
        RelatedPartyDto relatedPartyDto = new RelatedPartyDto();
        relatedPartyDtoList.add(relatedPartyDto);

        Mockito.when(relatedPartyTransformer.transform(relatedPartyDto)).thenReturn(relatedParty);
        Mockito.when(relatedPartyRepository.save(relatedParty)).thenReturn(relatedParty);

        relatedPartyService.update(relatedPartyDtoList);

        Mockito.verify(relatedPartyTransformer).transform(relatedPartyDto);
        Mockito.verify(relatedPartyRepository).save(relatedParty);
    }
}
