package com.realdolmen.fleet.service;



import com.realdolmen.fleet.enums.CarType;
import com.realdolmen.fleet.enums.FuelType;
import com.realdolmen.fleet.model.*;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.persist.CarRepository;
import com.realdolmen.fleet.persist.CarUsageRepository;
import com.realdolmen.fleet.persist.PeriodicUsageUpdateRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created on 5/11/2015.
 * Test class for {@link CarService}
 * @author Robin D'Haese
 */
@SpringApplicationConfiguration(classes = CarService.class)
public class CarServiceTest extends AbstractServiceTest {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarUsageRepository carUsageRepository;

    @Autowired
    private CarOptionsRepository carOptionsRepository;

    @Autowired
    private PeriodicUsageUpdateRepository periodicUsageUpdateRepository;

    @Before
    public void setUp(){
        reset(carRepository, carUsageRepository, carOptionsRepository, periodicUsageUpdateRepository);
    }

    @Test
    public void findAllPerformedCorrectly(){
        carService.findAll();
        verify(carRepository, times(1)).findByIsdeletedNot(true);
    }

    @Test
    public void findAllOnFunctionalLevelPerformedCorrectly(){
        carService.findAll(3);
        verify(carRepository, times(1)).findFor(3);
    }


    @Test
    public void filterOnBrandPerformedCorrectly(){
       assertEquals(5, carService.filterOnBrand(dummyCarList(), "audi").size());
        assertEquals(1, carService.filterOnBrand(dummyCarList(), "VOLKSWAGEN").size());
        assertEquals(1, carService.filterOnBrand(dummyCarList(), "Seat").size());
    }

    @Test
    public void filterOnModelPerformedCorrectly(){
        assertEquals(2, carService.filterOnModel(dummyCarList(), "a3").size());
        assertEquals(2, carService.filterOnModel(dummyCarList(), "a2").size());
        assertEquals(1, carService.filterOnModel(dummyCarList(), "a1").size());
        assertEquals(1, carService.filterOnModel(dummyCarList(), "golf").size());
        assertEquals(1, carService.filterOnModel(dummyCarList(), "IBIZA").size());
    }

    @Test
    public void filterDowngradePerformedCorrectly(){
        assertEquals(1, carService.filterDowngrade(dummyCarList(), 3).size());
        assertEquals(4, carService.filterDowngrade(dummyCarList(), 4).size());
    }

    @Test
    public void filterUpgradePerformedCorrectly(){
        assertEquals(1, carService.filterUpgrade(dummyCarList(), 3).size());
        assertEquals(4, carService.filterUpgrade(dummyCarList(), 2).size());
    }

    @Test
    public void filterSpecificLevelPerformedCorrectly(){
        assertEquals(1, carService.filterSpecificLevel(dummyCarList(), 1).size());
        assertEquals(1, carService.filterSpecificLevel(dummyCarList(), 2).size());
       assertEquals(4, carService.filterSpecificLevel(dummyCarList(), 3).size());
        assertEquals(1, carService.filterSpecificLevel(dummyCarList(), 4).size());
        assertEquals(0, carService.filterSpecificLevel(dummyCarList(), 5).size());
    }

    public List<Car> dummyCarList(){
        List<Car> cars = new ArrayList<>();
        cars.add(createCar("Audi", "A3", 3));
        cars.add(createCar("Audi", "A3", 4));
        cars.add(createCar("Audi", "A2", 3));
        cars.add(createCar("Audi", "A2", 2));
        cars.add(createCar("Audi", "A1", 1));
        cars.add(createCar("Volkswagen", "Golf", 3));
        cars.add(createCar("Seat", "Ibiza", 3));
        return cars;
    }

    private Car createCar(String brand, String model, int functionalLevel) {
        Pack basePack = new Pack("basePack", 500, new ArrayList<>());
        Car car = new Car(brand, model, functionalLevel, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, new ArrayList<>(), new ArrayList<>());
        return car;
    }

    @Test
    public void findByIdPerformedCorrectly(){
        carService.findById(5L);
        verify(carRepository, times(1)).findOne(5L);
    }

    @Test
    public void findAllFromFreePoolPerformedCorrectly(){
        carService.findAllFromFreePool();
        verify(carUsageRepository, times(1)).findAllFromFreePool();
    }

    @Test
    public void findCarUsageByIdPerformedCorrectly(){
        carService.findCarUsageById(5L);
        verify(carUsageRepository, times(1)).findOne(5L);
    }

    @Test
    public void saveCarUsagePerformedCorrectly(){
        carService.save(new CarUsage());
        verify(carUsageRepository, times(1)).save(any(CarUsage.class));
    }

    @Test
    public void findOpenOrdersForEmployeePerformedCorrectly(){
        Employee emp = new Employee();
        emp.setId(1L);
        carService.findOpenOrdersFor(emp);
        verify(carUsageRepository, times(1)).findOpenOrdersFor(1L);
    }

    @Test
    public void removeOpenOrderPerfomedCorrectly(){
        carService.removeOpenOrder(new CarUsage());
        verify(carUsageRepository, times(1)).delete(any(CarUsage.class));
    }

    @Test
    public void backToFreePoolPerformedCorrectly(){
        CarUsage carUsage = new CarUsage();
        carUsage.setEmployee(new Employee());
        carUsage.setLicensePlate("licensePlate");
        carService.backToFreePool(carUsage);
        assertNull(carUsage.getEmployee());
        assertNull(carUsage.getLicensePlate());
        verify(carUsageRepository, times(1)).save(any(CarUsage.class));
    }

   @Test
   public void saveCarPerformedCorrectly(){
       carService.saveCar(new Car());
       verify(carRepository, times(1)).save(any(Car.class));
   }

    @Test
    public void findAllOurCarsInUsePerformedCorrectly(){
        carService.findAllOurCarsInUse();
        verify(carUsageRepository, times(1)).findAllWithLicensePlateSet();
    }

    @Test
    public void removeFromFreePoolPerformedCorrectly(){
        carService.removeFromFreePool(new CarUsage());
        verify(carUsageRepository, times(1)).delete(any(CarUsage.class));
    }

    @Test
    public void removeCarUsagePerformedCorrectly(){
        carService.removeCarUsage(new CarUsage());
        verify(carUsageRepository, times(1)).delete(any(CarUsage.class));
    }

    @Test
    public void findCarUsageForEmployeeEmailPerformedCorrectly(){
       assertNull(carService.findCarUsageForEmployee("email"));
        verify(carUsageRepository, times(1)).findByEmployee("email");

        when(carUsageRepository.findByEmployee("email")).thenReturn(dummyCarUsageList());
        assertNotNull(carService.findCarUsageForEmployee("email"));
        verify(carUsageRepository, times(2)).findByEmployee("email");
    }

    private List<CarUsage> dummyCarUsageList() {
        List<CarUsage> carUsages = new ArrayList<>();
        carUsages.add(new CarUsage());
        carUsages.add(new CarUsage());
        carUsages.add(new CarUsage());
        carUsages.add(new CarUsage());
        return carUsages;
    }


    @Test
    public void deleteCarSoftPerformedCorrectly(){
        when(carRepository.findOne(1L)).thenReturn(new Car());
        carService.deleteCarSoft(1L);
        verify(carRepository, times(1)).findOne(1L);
        verify(carRepository, times(1)).save(any(Car.class));
    }


    @Test
    public void addPeriodicUsageUpdateToCarPerformedCorrectly(){
        String result = carService.addPeriodUsageUpdateToCar(new PeriodicUsageUpdate(), "unknownLicensePlate");
        verify(carUsageRepository, times(1)).findCurrentUsageWithLicencePlate("unknownLicensePlate");
        String message = "This is not a valid licenseplate of RealDolmen employee, please check";
        assertEquals(message, result);

        CarUsage carUsage = new CarUsage();
        PeriodicUsageUpdate p = new PeriodicUsageUpdate(new Date(), -1, -1, -1);
        when(carUsageRepository.findCurrentUsageWithLicencePlate("knownLicensePlate")).thenReturn(carUsage);
        result = carService.addPeriodUsageUpdateToCar(p, "knownLicensePlate");
        verify(carUsageRepository, times(1)).findCurrentUsageWithLicencePlate("knownLicensePlate");
        message = "Refuel is not correct, please check";
        assertEquals(message, result);

        p = new PeriodicUsageUpdate(new Date(), 10, 10D, 10D);
        result = carService.addPeriodUsageUpdateToCar(p, "knownLicensePlate");
        verify(carUsageRepository, times(2)).findCurrentUsageWithLicencePlate("knownLicensePlate");
        message = "PeriodUsageUpdate stored successfully!";
        assertEquals(message, result);
        verify(carUsageRepository, times(1)).save(any(CarUsage.class));
    }

}
