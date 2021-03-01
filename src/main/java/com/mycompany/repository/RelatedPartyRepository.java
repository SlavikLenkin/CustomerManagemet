package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatedPartyRepository extends JpaRepository<RelatedParty, Integer> {

    String queryFindRelatedParty = "select * from related_party\n" +
            "where id IN :id ";

    @Query(value = queryFindRelatedParty, nativeQuery = true)
    List<RelatedParty> findRelatedPartyById(@Param("id") String[] id);
}
