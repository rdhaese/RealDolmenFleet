package com.realdolmen.fleet.model;

import com.realdolmen.fleet.config.TestConfig;
import com.realdolmen.fleet.util.EntityFactory;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
@Transactional
public class EmployeePersistenceTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void canEmployeeBePersisted(){
        Employee emp = EntityFactory.createEmployee("name", "email", "password", EmployeeType.NORMAL, 2);
        em.persist(emp);
        assertNotNull(emp.getId());
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutName(){
        Employee emp = EntityFactory.createEmployee(null, "email", "password", EmployeeType.NORMAL, 2);
        em.persist(emp);

    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutEmail(){
        Employee emp = EntityFactory.createEmployee("name", null, "password", EmployeeType.NORMAL, 2);
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutPassword(){
        Employee emp = EntityFactory.createEmployee("name", "email", null, EmployeeType.NORMAL, 2);
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithPasswordShorterThan6Characters(){
        Employee emp = EntityFactory.createEmployee("name", "email", "pass", EmployeeType.NORMAL, 2);
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithoutRole(){
        Employee emp = EntityFactory.createEmployee("name", "email", "password", null, 2);
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithFunctionalLevelLowerThan0(){
        Employee emp = EntityFactory.createEmployee("name", "email", "password", EmployeeType.NORMAL, -1);
        em.persist(emp);
    }

    @Test (expected = ConstraintViolationException.class)
    public void employeeCannotBePersistedWithFunctionalLevelHigherThan8(){
        Employee emp = EntityFactory.createEmployee("name", "email", "password", EmployeeType.NORMAL, 9);
        em.persist(emp);
    }

}
