package com.realdolmen.fleet.model;

import com.realdolmen.fleet.enums.CarType;
import com.realdolmen.fleet.enums.EmployeeType;
import com.realdolmen.fleet.enums.FuelType;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created on 4/11/2015.
 * Persistence test for {@link CarUsage}
 * @author Robin D'Haese
 */
public class CarUsagePersistenceTest extends AbstractPersistenceTest{

    private List<Pack> extraPacks;
    private List<CarOption> extraOptions;
    private Pack basePack;
    private Car car;
    private Employee employee;
    private List<PeriodicUsageUpdate> usageUpdates;
    private OrderedCar orderedCar;

    @Before
    public void setUp(){
        basePack = new Pack("basePack", 500, createBaseOptionsList());
        em.persist(basePack);
        initExtraPacks();
        initExtraOptions();
        car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
        employee = new Employee("name", "email", "password", EmployeeType.ROLE_NORMAL, 2, new Date());
        em.persist(employee);
        orderedCar = new OrderedCar(car, "black", new ArrayList<>(), new ArrayList<>(), 50.5);
        em.persist(orderedCar);
        initUsageUpdates();
    }

    private void initUsageUpdates() {
        usageUpdates = new ArrayList<>();
        usageUpdates.add(new PeriodicUsageUpdate(new Date(), 0, 0D, 0D));
        usageUpdates.add(new PeriodicUsageUpdate(new Date(), 1000, 40.5, 46.90));
        usageUpdates.add(new PeriodicUsageUpdate(new Date(), 500, 43D, 50D));
        for (PeriodicUsageUpdate puu : usageUpdates){
            em.persist(puu);
        }
    }

    private List<CarOption> createBaseOptionsList() {
        List<CarOption> baseOptions = new ArrayList<>();
        baseOptions.add(new CarOption("des5", "name5"));
        baseOptions.add(new CarOption(null, "name6"));
        for (CarOption co : baseOptions){
            em.persist(co);
        }
        return baseOptions;
    }

    private void initExtraOptions() {
        extraOptions = new ArrayList<>();
        extraOptions.add(new CarOption("des1", "name1"));
        extraOptions.add(new CarOption("des2", "name2"));
        for (CarOption co : extraOptions){
            em.persist(co);
        }
    }

    private void initExtraPacks() {
        extraPacks = new ArrayList<>();
        extraPacks.add(new Pack("pack1", 200.50, new ArrayList<CarOption>()));
        extraPacks.add(new Pack("pack2", 250, createCarOptionList()));
        for (Pack pack : extraPacks){
            em.persist(pack);
        }
    }

    private List<CarOption> createCarOptionList() {
        List<CarOption> carOptions = new ArrayList<>();
        carOptions.add(new CarOption("des3", "name3"));
        carOptions.add(new CarOption(null, "name4"));
        for (CarOption co : carOptions){
            em.persist(co);
        }
        return carOptions;
    }

    private CarUsage getNewCarUsage(String licensePlate, Employee employee, OrderedCar orderedCar, Date date, Date date1, Date date2, Date date3, List<PeriodicUsageUpdate> usageUpdates) {
        CarUsage carUsage = new CarUsage(licensePlate, employee, orderedCar, date, date1, date2, date3);
        carUsage.setUsageUpdates(usageUpdates);
        return carUsage;
    }

    @Test
    public void canCarUsageBePersisted(){
        CarUsage carUsage = getNewCarUsage("licensePlate", employee, orderedCar, new Date(), new Date(), new Date(), new Date(), usageUpdates);
        em.persist(carUsage);
        assertNotNull(carUsage.getId());
    }

    @Test
    public void canCarUsageBePersistedWithoutLicensePlate(){
        CarUsage carUsage = getNewCarUsage(null, employee, orderedCar, new Date(), new Date(), new Date(), new Date(), usageUpdates);
        em.persist(carUsage);
        assertNotNull(carUsage.getId());
    }

    @Test
    public void canCarUsageBePersistedWithoutEmployee(){
        CarUsage carUsage = getNewCarUsage("licensePlate", null, orderedCar, new Date(), new Date(), new Date(), new Date(), usageUpdates);
        em.persist(carUsage);
        assertNotNull(carUsage.getId());
    }

    @Test
    public void canCarUsageBePersistedWithoutEndDate(){
        CarUsage carUsage = getNewCarUsage("licensePlate", employee, orderedCar, new Date(), new Date(), new Date(), null, usageUpdates);
        em.persist(carUsage);
        assertNotNull(carUsage.getId());
    }

    @Test
    public void arePeriodicUsageUpdatesSortedOnNewTotalKm(){
        CarUsage carUsage = getNewCarUsage("licensePlate", employee, orderedCar, new Date(), new Date(), new Date(), new Date(), usageUpdates);
        em.persist(carUsage);
        em.flush();
        em.clear();
        List<PeriodicUsageUpdate> uUpdates = em.find(CarUsage.class, carUsage.getId()).getUsageUpdates();
        assertEquals(1000, uUpdates.get(0).getNewTotalKm());
        assertEquals(0, uUpdates.get(uUpdates.size() - 1).getNewTotalKm());
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithEmptyLicensePlate(){
        CarUsage carUsage = getNewCarUsage("", employee, orderedCar, new Date(), new Date(), new Date(), null, usageUpdates);
        em.persist(carUsage);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithLicensePlateLargerThan255Characters(){
        CarUsage carUsage = getNewCarUsage(getStringOfXCharacters(256), employee, orderedCar, new Date(), new Date(), new Date(), null, usageUpdates);
        em.persist(carUsage);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithoutOrderedCar(){
        CarUsage carUsage = getNewCarUsage("licensePlate", employee, null, new Date(), new Date(), new Date(), null, usageUpdates);
        em.persist(carUsage);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithoutOrderDate(){
        CarUsage carUsage = getNewCarUsage("licensePlate", employee, orderedCar, null, new Date(), new Date(), null, usageUpdates);
        em.persist(carUsage);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithoutStartDate(){
        CarUsage carUsage = getNewCarUsage("licensePlate", employee, orderedCar, new Date(), null, new Date(), null, usageUpdates);
        em.persist(carUsage);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carUsageCannotBePersistedWithoutInitialEndDate(){
        CarUsage carUsage = getNewCarUsage("licensePlate", employee, orderedCar, new Date(), new Date(), null, null, usageUpdates);
        em.persist(carUsage);
    }
}
