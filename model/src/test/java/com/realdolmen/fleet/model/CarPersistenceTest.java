package com.realdolmen.fleet.model;

import com.realdolmen.fleet.enums.CarType;
import com.realdolmen.fleet.enums.FuelType;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created on 28/10/2015.
 * Persistence test for {@link Car}
 * @author Robin D'Haese
 */
public class CarPersistenceTest extends AbstractPersistenceTest {


    private List<Pack> extraPacks;
    private List<CarOption> extraOptions;
    private Pack basePack;

    @Before
    public void setUp(){
        basePack = new Pack("basePack", 500, createBaseOptionsList());
        initExtraPacks();
        initExtraOptions();
    }

    private List<CarOption> createBaseOptionsList() {
        List<CarOption> baseOptions = new ArrayList<>();
        baseOptions.add(new CarOption("des5", "name5"));
        baseOptions.add(new CarOption(null, "name6"));
        return baseOptions;
    }

    private void initExtraOptions() {
        extraOptions = new ArrayList<>();
        extraOptions.add(new CarOption("des1", "name1"));
        extraOptions.add(new CarOption("des2", "name2"));
    }

    private void initExtraPacks() {
       extraPacks = new ArrayList<>();
        extraPacks.add(new Pack("pack1", 200.50, new ArrayList<CarOption>()));
        extraPacks.add(new Pack("pack2", 250, createCarOptionList()));
    }

    private List<CarOption> createCarOptionList() {
        List<CarOption> carOptions = new ArrayList<>();
        carOptions.add(new CarOption("des3", "name3"));
        carOptions.add(new CarOption(null, "name4"));
        return carOptions;
    }

    @Test
    public void canCarBePersisted(){
        Car car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
        assertNotNull(car.getId());
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWithoutBrand(){
        Car car = new Car(null, "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWithEmptyBrand(){
        Car car = new Car("", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWithBrandLargerThan255Characters(){
        Car car = new Car(getStringOfXCharacters(256), "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWithoutModel(){
        Car car = new Car("Audi", null, 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWithEmptyModel(){
        Car car = new Car("Audi", "", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWithModelLargerThan255Characters(){
        Car car = new Car("Audi", getStringOfXCharacters(256), 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenCategoryIsLowerThan0(){
        Car car = new Car("Audi", "A1", -1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenCategoryIsHigherThan8(){
        Car car = new Car("Audi", "A1", 9, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenPkIsLowerThan1(){
        Car car = new Car("Audi", "A1", 1, 0, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenEmissionIsLowerThan1(){
        Car car = new Car("Audi", "A1", 1, 95, 0, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhithoutFuelType(){
        Car car = new Car("Audi", "A1", 1, 95, 90, null, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWithoutCarType(){
        Car car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, null, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenFiscalHpIsLowerThan1(){
        Car car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 0, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenDeliveryTimeIsLowerThan0(){
        Car car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, -1,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenIdealKmIsLowerThan1(){
        Car car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,0,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenMaxKmIsLowerThan1(){
        Car car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,0,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenListPriceIsLowerThan1(){
        Car car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,0,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenBenefitIsLowerThan1(){
        Car car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,0,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenAmountUpgradeIsLowerThan0(){
        Car car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,-1,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
    }

    @Test (expected = ConstraintViolationException.class)
    public void carCannotBePersistedWhenAmountDowngradeIsLowerThan0(){
        Car car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,-1, basePack, extraPacks, extraOptions);
        em.persist(car);
    }
}
