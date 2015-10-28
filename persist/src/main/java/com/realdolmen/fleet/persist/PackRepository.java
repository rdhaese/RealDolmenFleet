package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by JVDAX31 on 28/10/2015.
 * Repository for {@link Pack} entities
 */
public interface PackRepository  extends JpaRepository<Pack, Long> {
}
