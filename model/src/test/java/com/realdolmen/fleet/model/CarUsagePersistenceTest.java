package com.realdolmen.fleet.model;

import org.junit.Test;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created on 4/11/2015.
 * Persistence test for {@link CarUsage}
 * @author Robin D'Haese
 */
public class CarUsagePersistenceTest extends AbstractPersistenceTest{
    //TODO

    @Test
    public void canCarUsageBePersisted(){
        fail();
    }

    @Test
    public void canCarUsageBePersistedWithoutLicensePlate(){
        fail();
    }

    @Test
    public void canCarUsageBePersistedWithoutEmployee(){
        fail();
    }

    @Test
    public void canCarUsageBePersistedWithoutEndDate(){
        fail();
    }

    @Test
    public void arePeriodicUsageUpdatesSortedOnNewTotalKm(){
        fail();
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithEmptyLicensePlate(){
        fail();
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithLicensePlateLargerThan255Characters(){
        fail();
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithoutOrderedCar(){
        fail();
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithoutOrderDate(){
        fail();
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithoutStartDate(){
        fail();
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithoutInitialEndDate(){
        fail();
    }
}
