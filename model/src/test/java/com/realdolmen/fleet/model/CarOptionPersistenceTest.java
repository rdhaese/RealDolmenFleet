package com.realdolmen.fleet.model;

import com.realdolmen.fleet.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static junit.framework.TestCase.assertNotNull;


/**
 * Created on 28/10/2015.
 * Persistence test for {@link CarOption}
 * @author Robin D'Haese
 */

public class CarOptionPersistenceTest extends AbstractPersistenceTest{



    @Test
    public void canCarOptionBePersisted(){
        CarOption carOption= new CarOption("description", "name");
        em.persist(carOption);
        assertNotNull(carOption.getId());
    }

    @Test
    public void canCarOptionBePersistedWithoutDescription(){
        CarOption carOption= new CarOption(null, "name");
        em.persist(carOption);
        assertNotNull(carOption.getId());
    }

    @Test (expected = ConstraintViolationException.class)
    public void carOptionCannotBePersistedWithoutName(){
        CarOption carOption= new CarOption("description", null);
        em.persist(carOption);
    }
}
