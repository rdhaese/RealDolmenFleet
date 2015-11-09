package com.realdolmen.fleet.persist;


import com.realdolmen.fleet.enums.CarType;
import com.realdolmen.fleet.enums.FuelType;
import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;

/**
 * Created on 5/11/2015.
 * Test class for {@link CarRepository}
 * @author Robin D'Haese
 */
public class CarRepositoryTest extends AbstractRepositoryTest {


    @Before
    public void setUp(){
        deleteAll();
        carRepository.save(createCar(5, true));
        carRepository.save(createCar(5, true));
        carRepository.save(createCar(5, true));
        carRepository.save(createCar(5, true));
        carRepository.save(createCar(4, true));
        carRepository.save(createCar(4, true));
        carRepository.save(createCar(4, true));
        carRepository.save(createCar(4, true));
        carRepository.save(createCar(3, true));
        carRepository.save(createCar(3, true));
        carRepository.save(createCar(3, true));
        carRepository.save(createCar(3, true));
        carRepository.save(createCar(5, false));
        carRepository.save(createCar(5, false));
        carRepository.save(createCar(5, false));
        carRepository.save(createCar(5, false));
        carRepository.save(createCar(4, false));
        carRepository.save(createCar(4, false));
        carRepository.save(createCar(4, false));
        carRepository.save(createCar(4, false));
        carRepository.save(createCar(3, false));
        carRepository.save(createCar(3, false));
        carRepository.save(createCar(3, false));
        carRepository.save(createCar(3, false));

    }

    private Car createCar(int functionalLevel, boolean softDeleted) {
        Pack basePack = new Pack("basePack", 500, createBaseOptionsList());
        packRepository.save(basePack);
        Car car = new Car("Audi", "A1", functionalLevel, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, new ArrayList<>(), new ArrayList<>());
        car.setIsdeleted(softDeleted);
        return car;
    }

    private List<CarOption> createBaseOptionsList() {
        List<CarOption> baseOptions = new ArrayList<>();
        baseOptions.add(new CarOption("des5", "name5"));
        baseOptions.add(new CarOption(null, "name6"));
        for (CarOption baseOption : baseOptions){
            carOptionsRepository.save(baseOption);
        }
        return baseOptions;
    }

    @Test
    public void canAllCarsForGivenFunctionalLevelBeFound(){
        List<Car> cars = carRepository.findFor(4);
        assertEquals(12, cars.size());
        for (Car car : cars){
            assertFalse(car.getIsdeleted());
        }
        assertEquals(8, carRepository.findFor(3).size());
        assertEquals(8, carRepository.findFor(5).size());
        assertEquals(4, carRepository.findFor(2).size());
        assertEquals(4, carRepository.findFor(6).size());
        assertEquals(0, carRepository.findFor(1).size());
        assertEquals(0, carRepository.findFor(7).size());
    }

    @Test
    public void canAllCarsBeFoundExceptSoftDeletedOnes(){
        List<Car> cars = carRepository.findByIsdeletedNot(true);
        assertEquals(12, cars.size());
        for (Car car : cars){
            assertFalse(car.getIsdeleted());
        }
    }

    @Test
    public void canAllSoftDeletedCarsBeFound(){
        List<Car> cars = carRepository.findByIsdeletedNot(false);
        assertEquals(12, cars.size());
        for (Car car : cars){
            assertTrue(car.getIsdeleted());
        }
    }
}
