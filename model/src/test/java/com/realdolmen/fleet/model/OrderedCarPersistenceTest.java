package com.realdolmen.fleet.model;

import com.realdolmen.fleet.enums.CarType;
import com.realdolmen.fleet.enums.FuelType;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created on 4/11/2015.
 * Persistence test for {@link OrderedCar}
 * @author Robin D'Haese
 */
public class OrderedCarPersistenceTest extends AbstractPersistenceTest{

    private List<Pack> extraPacks;
    private List<CarOption> extraOptions;
    private Pack basePack;
    private Car car;

    @Before
    public void setUp(){
        basePack = new Pack("basePack", 500, createBaseOptionsList());
        initExtraPacks();
        initExtraOptions();
        car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        em.persist(car);
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
    public void canOrderedCarBePersisted(){
        OrderedCar orderedCar = new OrderedCar(car, "black", new ArrayList<>(), new ArrayList<>(), 50.5);
        em.persist(orderedCar);
        assertNotNull(orderedCar.getId());
    }

    @Test (expected = ConstraintViolationException.class)
    public void orderCarCannotBePersistedWithoutCar(){
        OrderedCar orderedCar = new OrderedCar(null, "black", new ArrayList<>(), new ArrayList<>(), 50.5);
        em.persist(orderedCar);
    }

    @Test (expected = ConstraintViolationException.class)
    public void orderedCarCannotBePersistedWithoutColor(){
        OrderedCar orderedCar = new OrderedCar(car, null, new ArrayList<>(), new ArrayList<>(), 50.5);
        em.persist(orderedCar);
    }

    @Test (expected = ConstraintViolationException.class)
    public void orderedCarCannotBePersistedWithEmptyColor(){
        OrderedCar orderedCar = new OrderedCar(car, "", new ArrayList<>(), new ArrayList<>(), 50.5);
        em.persist(orderedCar);
    }

    @Test (expected = ConstraintViolationException.class)
    public void orderedCarCannotBePersistedWithColorLargerThan255Characters(){
        OrderedCar orderedCar = new OrderedCar(car, getStringOfXCharacters(256), new ArrayList<>(), new ArrayList<>(), 50.5);
        em.persist(orderedCar);
    }

    @Test (expected = ConstraintViolationException.class)
    public void orderedCarCannotBePersistedWithPriceLowerThan0(){
        OrderedCar orderedCar = new OrderedCar(car, "black", new ArrayList<>(), new ArrayList<>(), -0.01);
        em.persist(orderedCar);
    }
}
