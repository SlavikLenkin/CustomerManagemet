package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    String queryFindCustomers = "select * from account\n" +
            "where id = :id ";

    @Query(value = queryFindCustomers, nativeQuery = true)
    Account findByIdAccount(@Param("id") String id);


}
