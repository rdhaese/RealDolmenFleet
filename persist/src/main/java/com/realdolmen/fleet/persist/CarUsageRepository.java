package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
public interface CarUsageRepository extends JpaRepository<CarUsage, Long> {

    @Query(value = "select c from CarUsage c where c.employee.email = ?1")
    List<CarUsage> findByEmployee(String employeeEmail);

    @Query("select c from CarUsage c where c.licensePlate is null and c.employee is not null")
    List<CarUsage> findCarUsagesWithoutLicensePlate();

    @Query("select c from CarUsage c where c.employee is null")
    List<CarUsage> findAllFromFreePool();

    @Query("select c from CarUsage c where c.licensePlate is null and c.employee.id = ?1")
    List<CarUsage> findOpenOrdersFor(long employeeId);

    @Query("select c from CarUsage c where c.licensePlate is not null")
    List<CarUsage> findAllWithLicensePlateSet();
}
