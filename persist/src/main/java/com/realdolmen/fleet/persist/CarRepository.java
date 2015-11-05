package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.CarUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by JVDAX31 on 28/10/2015.
 * Repository for {@link Car} entities
 */
public interface CarRepository  extends JpaRepository<Car, Long> {

    @Query("select c from Car c WHERE c.category = ?1 - 1 OR c.category = ?1 OR c.category = ?1 + 1")
    List<Car> findFor(int functionalLevel);

    List<Car> findByIsdeletedNot(Boolean b);

}
