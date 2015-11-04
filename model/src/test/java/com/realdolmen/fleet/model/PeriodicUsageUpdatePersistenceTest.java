package com.realdolmen.fleet.model;

import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 * Created on 4/11/2015.
 * Persistence test for {@link PeriodicUsageUpdate}
 * @author Robin D'Haese
 */
public class PeriodicUsageUpdatePersistenceTest extends AbstractPersistenceTest{

    @Test
    public void canPeriodicUsageUpdateBePersisted(){
        PeriodicUsageUpdate puu = new PeriodicUsageUpdate(new Date(), 50, 50, 50);
        em.persist(puu);
        assertNotNull(puu.getId());
    }

    @Test (expected = ConstraintViolationException.class)
    public void periodicUsageUpdateCannotBePersistedWithoutUpdateDate(){
        PeriodicUsageUpdate puu = new PeriodicUsageUpdate(null, 50, 50, 50);
        em.persist(puu);
    }

    @Test (expected = ConstraintViolationException.class)
    public void periodicUsageUpdateCannotBePersistedWithNewTotalKmLowerThan0(){
        PeriodicUsageUpdate puu = new PeriodicUsageUpdate(new Date(), -1, 50, 50);
        em.persist(puu);
    }

    @Test (expected = ConstraintViolationException.class)
    public void periodicUsageUpdateCannotBePersistedWithTotalFuelledForPeriodLowerThan0(){
        PeriodicUsageUpdate puu = new PeriodicUsageUpdate(new Date(), 50, -1, 50);
        em.persist(puu);
    }

    @Test (expected = ConstraintViolationException.class)
    public void periodicUsageUpdateCannotBePersistedWithTotalFuelPriceLowerThan0(){
        PeriodicUsageUpdate puu = new PeriodicUsageUpdate(new Date(), 50, 50, -1);
        em.persist(puu);
    }
}