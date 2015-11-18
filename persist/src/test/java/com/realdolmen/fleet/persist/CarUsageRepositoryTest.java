package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.enums.CarType;
import com.realdolmen.fleet.enums.EmployeeType;
import com.realdolmen.fleet.enums.FuelType;
import com.realdolmen.fleet.model.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 5/11/2015.
 * Test class for {@link CarUsageRepository}
 * @author Robin D'Haese
 */
public class CarUsageRepositoryTest extends AbstractRepositoryTest {

    @Before
    public void setUp() {
        deleteAll();
        setUpData();
        carUsageRepository.flush();
    }

    private void setUpData() {
        carUsageRepository.save(createCarUsage("licensePlate1", createEmployee("name1", "email1", "password1", EmployeeType.ROLE_NORMAL, 3, new Date())));
        carUsageRepository.save(createCarUsage("licensePlate2", createEmployee("name2", "email2", "password2", EmployeeType.ROLE_NORMAL, 3, new Date())));
        carUsageRepository.save(createCarUsage("licensePlate3", createEmployee("name3", "email3", "password3", EmployeeType.ROLE_NORMAL, 3, new Date())));
        carUsageRepository.save(createCarUsage(null, createEmployee("name4", "email4", "password4", EmployeeType.ROLE_NORMAL, 3, new Date())));
        carUsageRepository.save(createCarUsage(null, createEmployee("name5", "email5", "password5", EmployeeType.ROLE_NORMAL, 3, new Date())));
        carUsageRepository.save(createCarUsage(null, createEmployee("name6", "email6", "password6", EmployeeType.ROLE_NORMAL, 3, new Date())));
        carUsageRepository.save(createCarUsage(null, null));
        carUsageRepository.save(createCarUsage(null, null));
        carUsageRepository.save(createCarUsage(null, null));
    }

    private Employee createEmployee(String name, String email, String password, EmployeeType role, int functionalLevel, Date inFunctionDate) {
        Employee employee = new Employee(name, email, password, role, functionalLevel, inFunctionDate);
        employeeRepository.save(employee);
        return employee;
    }

    private CarUsage createCarUsage(String licensePlate, Employee employee) {
        CarUsage carUsage = new CarUsage(licensePlate, employee, createOrderedCar(), new Date(), new Date(), new Date());
        return carUsage;
    }

    private OrderedCar createOrderedCar() {
        OrderedCar orderedCar = new OrderedCar(createCar(), "testColor", new ArrayList<>(), new ArrayList<>(), 500D);
        orderedCarRepository.save(orderedCar);
        return orderedCar;
    }

    private Car createCar() {
        Pack basePack = new Pack("basePack", 500, createBaseOptionsList());
        packRepository.save(basePack);
        Car car = new Car("Audi", "A1", 3, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5, 80000, 120000, 15000, 120, 2000, 2000, basePack, new ArrayList<>(), new ArrayList<>());
        carRepository.save(car);
        return car;
    }

    private List<CarOption> createBaseOptionsList() {
        List<CarOption> baseOptions = new ArrayList<>();
        baseOptions.add(new CarOption("des5", "name5"));
        baseOptions.add(new CarOption(null, "name6"));
        for (CarOption baseOption : baseOptions) {
            carOptionsRepository.save(baseOption);
        }
        return baseOptions;
    }

    @Test
    public void canCarUsageBeFoundOnEmployeeEmail() {
        assertEquals(1, carUsageRepository.findByEmployee("email1").size());
        assertEquals(1, carUsageRepository.findByEmployee("email2").size());
        assertEquals(1, carUsageRepository.findByEmployee("email3").size());
        assertEquals(1, carUsageRepository.findByEmployee("email4").size());
        assertEquals(1, carUsageRepository.findByEmployee("email5").size());
        assertEquals(1, carUsageRepository.findByEmployee("email6").size());
        assertEquals(0, carUsageRepository.findByEmployee("unknown-email").size());
        assertEquals(0, carUsageRepository.findByEmployee(null).size());
    }

    @Test
    public void canCarUsagesWithoutLicensePlateBeFound() {
        assertEquals(3, carUsageRepository.findCarUsagesWithoutLicensePlate().size());
    }

    @Test
    public void canAllCarUsagesFromFreePoolBeFound() {
        assertEquals(3, carUsageRepository.findAllFromFreePool().size());
    }

    @Test
    public void canOpenOrdersForEmployeeBeFound() {
        Employee employee = employeeRepository.findByEmail("email1");
        assertEquals(0, carUsageRepository.findOpenOrdersFor(employee.getId()).size());
        employee = employeeRepository.findByEmail("email4");
        assertEquals(1, carUsageRepository.findOpenOrdersFor(employee.getId()).size());
    }

    @Test
    public void canAllBeFoundWithLicensePlateSet() {
        assertEquals(3, carUsageRepository.findAllWithLicensePlateSet().size());
    }

    @Test
    public void canCurrentCarUsageForEmployeeIdBeFound() {
        Employee employee = employeeRepository.findByEmail("email1");
        assertNotNull(carUsageRepository.findCurrentUsage(employee.getId()));
        employee = employeeRepository.findByEmail("email4");
        assertNull(carUsageRepository.findCurrentUsage(employee.getId()));
    }

    @Test
    public void canAllWithoutLicensePlateBeFound() {
        assertNotNull(carUsageRepository.findCurrentUsageWithLicencePlate("licensePlate1"));
        assertNotNull(carUsageRepository.findCurrentUsageWithLicencePlate("licensePlate2"));
        assertNotNull(carUsageRepository.findCurrentUsageWithLicencePlate("licensePlate3"));
        assertNull(carUsageRepository.findCurrentUsageWithLicencePlate("unknownLicensePlate"));
    }
}
