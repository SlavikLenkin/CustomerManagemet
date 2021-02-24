package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    String queryFindAccount = "select * from account\n" +
            "where id IN :id ";

    @Query(value = queryFindAccount, nativeQuery = true)
    List<Account> findAccountById(@Param("id") String[] id);


}
