package com.mycompany.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    String queryFindCustomers = "select * from customer\n" +
            "where id = :id ";

    String queryUpdateCustomer = "UPDATE customer\n" +
            "\tSET  name= :#{#customer.name} ," +
            " status= :#{#customer.status} ," +
            " status_reason= :#{#customer.statusReason} ," +
            " start_date_time= :#{#customer.validFor.startDateTime} ," +
            " end_date_time= :#{#customer.validFor.endDateTime}" +
            "\tWHERE id = :#{#customer.id}";


    @Transactional
    @Modifying
    @Query(value = queryUpdateCustomer, nativeQuery = true)
    void updateCustomerById(@Param("customer") Customer customer);

    @Query(value = queryFindCustomers, nativeQuery = true)
    Customer findCustomerById(@Param("id") String id);
}
