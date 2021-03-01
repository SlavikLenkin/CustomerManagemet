package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

    String queryFindPaymentMethod = "select * from payment_method\n" +
            "where id IN :id ";

    @Query(value = queryFindPaymentMethod, nativeQuery = true)
    List<PaymentMethod> findPaymentMethodById(@Param("id") String[] id);
}
