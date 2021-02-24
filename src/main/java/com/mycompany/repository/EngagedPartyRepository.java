package com.mycompany.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EngagedPartyRepository extends JpaRepository<EngagedParty,Integer> {

    String queryFindEngagedParty = "select * from engaged_party  \n" +
            "where id = :id ";

    @Query(value = queryFindEngagedParty, nativeQuery = true)
    EngagedParty findEngagedPartyById(@Param("id") String id);




}
