package com.mycompany.service;

import com.mycompany.model.RelatedPartyDto;
import com.mycompany.repository.Customer;
import com.mycompany.repository.RelatedParty;
import com.mycompany.repository.RelatedPartyRepository;
import com.mycompany.transfomer.RelatedPartyTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class RelatedPartyService {

    final RelatedPartyRepository repository;
    final RelatedPartyTransformer relatedPartyTransformer;

    public RelatedPartyService(RelatedPartyRepository repository
            , RelatedPartyTransformer relatedPartyTransformer) {
        this.repository = repository;
        this.relatedPartyTransformer = relatedPartyTransformer;
    }

    public List<RelatedPartyDto> save(List<RelatedPartyDto> relatedPartiesDto, Customer customer) {
        log.debug("save");
        if (relatedPartiesDto == null) {
            return null;
        }
        int i = 0;
        for (RelatedPartyDto relatedPartyDto : relatedPartiesDto) {
            if (relatedPartyDto.getName() == null) {
                relatedPartyDto.setName(Optional.empty());
            }
            if (relatedPartyDto.getRole() == null) {
                relatedPartyDto.setRole(Optional.empty());
            }
            RelatedParty relatedParty = relatedPartyTransformer.transform(relatedPartyDto);
            relatedParty.setCustomer(customer);
            String id = UUID.randomUUID().toString();
            relatedParty.setId(id);
            relatedParty.setHref("https://host:port/tmf-api/customerManagement/v4/relatedParty/" + id);
            repository.save(relatedParty);
            relatedPartiesDto.set(i, relatedPartyTransformer.transform(relatedParty));
            i++;
        }
        return relatedPartiesDto;
    }

    public List<RelatedPartyDto> update(List<RelatedPartyDto> relatedPartiesDto) {
        log.debug("update");
        for (RelatedPartyDto relatedPartyDto : relatedPartiesDto) {
            repository.save(relatedPartyTransformer.transform(relatedPartyDto));
        }
        return relatedPartiesDto;
    }

    public void delete(RelatedPartyDto relatedPartyDto) {
        log.debug("delete");
        repository.delete(relatedPartyTransformer.transform(relatedPartyDto));
    }
}
