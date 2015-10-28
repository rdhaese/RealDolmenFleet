package com.realdolmen.fleet.model;

import com.realdolmen.fleet.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

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
        Employee emp = new Employee("name", "email", "password", EmployeeType.ROLE_NORMAL, 2);
        em.persist(emp);
        assertNotNull(emp.getId());
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutName(){
        Employee emp = new Employee(null, "email", "password", EmployeeType.ROLE_NORMAL, 2);
        em.persist(emp);

    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutEmail(){
        Employee emp = new Employee("name", null, "password", EmployeeType.ROLE_NORMAL, 2);
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutPassword(){
        Employee emp = new Employee("name", "email", null, EmployeeType.ROLE_NORMAL, 2);
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithPasswordShorterThan6Characters(){
        Employee emp = new Employee("name", "email", "pass", EmployeeType.ROLE_NORMAL, 2);
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutRole(){
        Employee emp = new Employee("name", "email", "password", null, 2);
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithFunctionalLevelLowerThan0(){
        Employee emp = new Employee("name", "email", "password", EmployeeType.ROLE_NORMAL, -1);
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithFunctionalLevelHigherThan8(){
        Employee emp = new Employee("name", "email", "password", EmployeeType.ROLE_NORMAL, 9);
        em.persist(emp);
    }

}
