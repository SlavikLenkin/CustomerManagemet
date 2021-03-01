package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Integer> {

    String queryFindCharacteristic = "select * from characteristic\n" +
            "where id IN :id ";

    @Query(value = queryFindCharacteristic, nativeQuery = true)
    List<Characteristic> findCharacteristicById(@Param("id") String[] id);

}
