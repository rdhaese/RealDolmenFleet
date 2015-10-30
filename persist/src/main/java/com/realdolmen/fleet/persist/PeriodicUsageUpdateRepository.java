package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.model.PeriodicUsageUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
public interface PeriodicUsageUpdateRepository extends JpaRepository<PeriodicUsageUpdate, Long> {
}
