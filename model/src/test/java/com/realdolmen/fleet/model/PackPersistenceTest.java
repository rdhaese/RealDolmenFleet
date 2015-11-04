package com.realdolmen.fleet.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created on 28/10/2015.
 * Persistence test for {@link Pack}
 * @author Robin D'Haese
 */
public class PackPersistenceTest extends AbstractPersistenceTest {

    private List<CarOption> options;

    @Before
    public void setUp(){
        options = new ArrayList<>();
        options.add(new CarOption("description", "name"));
        options.add(new CarOption(null, "name2"));
    }

    @Test
    public void canPackBePersisted(){
        Pack pack = new Pack("packname", 500, options);
        em.persist(pack);
        assertNotNull(pack.getId());
    }

    @Test (expected = ConstraintViolationException.class)
    public void packCannotBePersistedWithoutName(){
        Pack pack = new Pack(null, 500, options);
        em.persist(pack);
    }

    @Test (expected = ConstraintViolationException.class)
    public void packCannotBePersistedWithPriceLowerThan0(){
        Pack pack = new Pack("packname", -1, options);
        em.persist(pack);
    }

    @Test (expected = ConstraintViolationException.class)
    public void packCannotBePersistedWithEmptyName(){
        Pack pack = new Pack("", 500, options);
        em.persist(pack);
    }
    @Test (expected = ConstraintViolationException.class)
    public void packCannotBePersistedWithNameLargerThan255Characters(){
        Pack pack = new Pack(getStringOfXCharacters(256), 500, options);
        em.persist(pack);
    }
}
