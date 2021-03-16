package com.mycompany.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    String queryFindCustomers = "select * from customer\n" +
            "where id = :id ";

    String queryDeleteAccountsById = "delete\n" +
            "from account\n" +
            "where id in\n" +
            "(\n" +
            "    select a.id\n" +
            "    from account a\n" +
            "    inner join customer_account ca\n" +
            "    on a.id = ca.account_id\n" +
            "    inner join Customer c\n" +
            "    on c.id = ca.customer_id\n" +
            "    where c.id = :id\n" +
            ")";

    @Transactional
    @Modifying
    @Query(value = queryDeleteAccountsById,nativeQuery = true)
    void deleteAccountsById(@Param("id") String id);

    @Query(value = queryFindCustomers, nativeQuery = true)
    Customer findCustomerById(@Param("id") String id);
}
