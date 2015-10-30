package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 27/10/2015.
 * Repository for {@link Employee} entities
 * @author Robin D'Haese
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     Employee findByEmail(String email);
}
