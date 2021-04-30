package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    String queryFindCustomers = "select * from customer\n" +
            "where id = :id ";

    @Query(value = queryFindCustomers, nativeQuery = true)
    Customer findCustomerById(@Param("id") String id);
}
