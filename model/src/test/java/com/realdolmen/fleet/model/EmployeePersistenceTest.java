package com.realdolmen.fleet.model;

import com.realdolmen.fleet.enums.EmployeeType;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 * Created on 28/10/2015.
 *
 * Persistence test for {@link Employee}
 * @author Robin D'Haese
 */
public class EmployeePersistenceTest extends AbstractPersistenceTest{

    @Test
    public void canEmployeeBePersisted(){
        Employee emp = new Employee("name", "email", "password", EmployeeType.ROLE_NORMAL, 2, new Date());
        em.persist(emp);
        assertNotNull(emp.getId());
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutName(){
        Employee emp = new Employee(null, "email", "password", EmployeeType.ROLE_NORMAL, 2, new Date());
        em.persist(emp);

    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutEmail(){
        Employee emp = new Employee("name", null, "password", EmployeeType.ROLE_NORMAL, 2, new Date());
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutPassword(){
        Employee emp = new Employee("name", "email", null, EmployeeType.ROLE_NORMAL, 2, new Date());
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithPasswordShorterThan6Characters(){
        Employee emp = new Employee("name", "email", "pass", EmployeeType.ROLE_NORMAL, 2, new Date());
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutRole(){
        Employee emp = new Employee("name", "email", "password", null, 2, new Date());
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithFunctionalLevelLowerThan0(){
        Employee emp = new Employee("name", "email", "password", EmployeeType.ROLE_NORMAL, -1, new Date());
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithFunctionalLevelHigherThan8(){
        Employee emp = new Employee("name", "email", "password", EmployeeType.ROLE_NORMAL, 9, new Date());
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutInServiceDate(){
        Employee emp = new Employee("name", "email", "password", EmployeeType.ROLE_NORMAL, 9, null);
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithEmptyName(){
        Employee emp = new Employee("", "email", "password", EmployeeType.ROLE_NORMAL, 2, new Date());
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithEmptyEmail(){
        Employee emp = new Employee("name", "", "password", EmployeeType.ROLE_NORMAL, 2, new Date());
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithNameLargerThan255Characters(){
        Employee emp = new Employee(getStringOfXCharacters(256), "email", "password", EmployeeType.ROLE_NORMAL, 2, new Date());
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithEmailLargerThan255Characters(){
        Employee emp = new Employee("name", getStringOfXCharacters(256), "password", EmployeeType.ROLE_NORMAL, 2, new Date());
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithPasswordLargerThan255Characters(){
        Employee emp = new Employee("name", "email", getStringOfXCharacters(256), EmployeeType.ROLE_NORMAL, 2, new Date());
        em.persist(emp);
    }
}
