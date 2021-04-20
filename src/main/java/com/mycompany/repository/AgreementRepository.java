package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgreementRepository extends JpaRepository<Agreement, Integer> {

    String queryFindAgreement = "select * from agreement\n" +
            "where id IN :id ";

    @Query(value = queryFindAgreement, nativeQuery = true)
    List<Agreement> findAgreementById(@Param("id") String[] id);
}

