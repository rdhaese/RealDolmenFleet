package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.CarOption;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by JVDAX31 on 28/10/2015.
 * Repository for {@link Car} entities
 */
public interface CarRepository  extends JpaRepository<Car, Long> {
}
