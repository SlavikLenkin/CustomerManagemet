package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MediumCharacteristicRepository extends JpaRepository<MediumCharacteristic, Integer> {

    String queryFindMediumCharacteristic = "select * from medium_characteristic\n" +
            "where id = :id ";

    @Query(value = queryFindMediumCharacteristic, nativeQuery = true)
    MediumCharacteristic findMediumCharacteristicById(@Param("id") String id);

}
