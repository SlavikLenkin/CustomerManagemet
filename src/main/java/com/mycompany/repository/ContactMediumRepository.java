package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactMediumRepository extends JpaRepository<ContactMedium, Integer> {

    String queryFindContactMedium = "select * from contact_medium\n" +
            "where id IN :id ";

    @Query(value = queryFindContactMedium, nativeQuery = true)
    List<ContactMedium> findContactMediumById(@Param("id") String[] id);
}
