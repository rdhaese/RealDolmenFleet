package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.enums.EmployeeType;
import com.realdolmen.fleet.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created on 5/11/2015.
 *
 * @author Robin D'Haese
 */
public class EmployeeRepositoryTest extends AbstractRepositoryTest{

    @Before
    public void setUp(){
        Employee emp = new Employee("name", "email", "password", EmployeeType.ROLE_NORMAL, 2, new Date());
        emp.setPermissionToOrderNewCar(true);
        employeeRepository.saveAndFlush(emp);
        Employee emp1 = new Employee("name1", "email1", "password1", EmployeeType.ROLE_NORMAL, 2, new Date());
        Employee emp2 = new Employee("name2", "email2", "password2", EmployeeType.ROLE_FLEET, 2, new Date());
        employeeRepository.saveAndFlush(emp1);
        employeeRepository.saveAndFlush(emp2);
    }

    @Test
    public void canEmployeeBeFoundOnEmail(){
        Employee emp = employeeRepository.findByEmail("email");
        assertNotNull(emp);
        assertEquals("name", emp.getName());
    }

    @Test
    public void canEmployeesWithoutPermissionTOrderNewCarBeFound(){
        assertEquals(2, employeeRepository.findEmployeesWithoutPermissionTOrderNewCar().size());
    }
}
