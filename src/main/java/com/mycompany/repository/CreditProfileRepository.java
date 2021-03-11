package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditProfileRepository extends JpaRepository<CreditProfile, Integer> {

    String queryFindCreditProfile = "select * from credit_profile\n" +
            "where id IN :id ";

    @Query(value = queryFindCreditProfile, nativeQuery = true)
    List<CreditProfile> findCreditProfileById(@Param("id") String[] id);
}
