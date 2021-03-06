package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 27/10/2015.
 * Repository for {@link Employee} entities
 * @author Robin D'Haese
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     Employee findByEmail(String email);

     @Query("select e from Employee e where e.permissionToOrderNewCar = false")
     List<Employee> findEmployeesWithoutPermissionTOrderNewCar();

     @Query("select e from Employee e where e.permissionToOrderNewCar = true")
     List<Employee> findEmployeesWithPermissionToOrderNewCar();
}
