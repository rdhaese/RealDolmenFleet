package com.realdolmen.fleet.service;

import com.realdolmen.fleet.enums.EmployeeType;
import com.realdolmen.fleet.model.*;
import com.realdolmen.fleet.persist.CarUsageRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.realdolmen.fleet.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created on 5/11/2015.
 * Test class for {@link CanOrderNewCarService}
 * @author Robin D'Haese
 */
@SpringApplicationConfiguration(classes = {CanOrderNewCarService.class, CanOrderNewCarServiceTest.InnerConfig.class})
public class CanOrderNewCarServiceTest extends AbstractServiceTest {
    @Configuration
    @Profile("test")
    static class InnerConfig{
        @Bean
        public EmployeeService employeeService(){
            return  mock(EmployeeService.class);
        }

        @Bean
        public MailService mailService(){
            return mock(MailService.class);
        }
    }

    @Autowired
    private CanOrderNewCarService canOrderNewCarService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CarUsageRepository carUsageRepository;
    //TODO Create tests like PeriodicUsageServiceTest
    private Employee employee;

    @Before
    public void setUp(){
        employee = new Employee("name", "email", "password", EmployeeType.ROLE_FLEET, 3, new Date());
    }

    @Test
    public void loggedInEmployeeCanOrderNewCarPerformedCorrectly(){
        when(employeeService.getLoggedInUser()).thenReturn(employee);
       assertFalse(canOrderNewCarService.loggedInEmployeeCanOrderNewCar());
        employee.setPermissionToOrderNewCar(true);;
        assertTrue(canOrderNewCarService.loggedInEmployeeCanOrderNewCar());
        verify(employeeService, times(2)).getLoggedInUser();
    }

    @Test
    public void needsPermissionPerformedCorrectly(){
        Employee emp = new Employee();
        emp.setEmail("email");
        when(carUsageRepository.findByEmployee("email")).thenReturn(dummyCarUsagesList());
        canOrderNewCarService.needsPermission(emp);
        verify(carUsageRepository, times(1)).findByEmployee("email");
    }


    @Test
    public void hasOpenOrderPerformedCorrectly(){
        assertFalse(canOrderNewCarService.hasOpenOrder(null));
        CarUsage carUsage = new CarUsage();
        carUsage.setStartDate(Utils.addDaysToDate(new Date(), -10D));
        assertTrue(canOrderNewCarService.hasOpenOrder(carUsage));
        carUsage.setStartDate(Utils.addDaysToDate(new Date(), 10D));
        assertFalse(canOrderNewCarService.hasOpenOrder(carUsage));
    }


    @Test
    public void reachedIdealKmPerformedCorrectly(){
        CarUsage carUsage = new CarUsage();
        OrderedCar oCar = mock(OrderedCar.class);
        Car car = mock(Car.class);
        when(car.getIdealKm()).thenReturn(1000);
        when(oCar.getCar()).thenReturn(car);
        carUsage.setOrderedCar(oCar);
        assertFalse(canOrderNewCarService.reachedIdealKm(carUsage));
        carUsage.addUsageUpdate(new PeriodicUsageUpdate(new Date(), 0, 0D, 0D));
        assertFalse(canOrderNewCarService.reachedIdealKm(carUsage));
        carUsage.addUsageUpdate(new PeriodicUsageUpdate(new Date(), 100, 100D, 100D));
        assertFalse(canOrderNewCarService.reachedIdealKm(carUsage));
        carUsage.addUsageUpdate(new PeriodicUsageUpdate(new Date(), 1001, 100D, 100D));
        assertTrue(canOrderNewCarService.reachedIdealKm(carUsage));
    }

    @Test
    public void reachedEndDatePerformedCorrectly(){
        CarUsage carUsage = new CarUsage();
        carUsage.setInitialEndDate(Utils.addDaysToDate(new Date(), -10D));
        assertTrue(canOrderNewCarService.reachedEndDate(carUsage));
        carUsage.setInitialEndDate(Utils.addDaysToDate(new Date(), 10D));
        assertFalse(canOrderNewCarService.reachedEndDate(carUsage));
    }

   @Test
   public void hasACarPerformedCorrectly(){
        assertFalse(canOrderNewCarService.hasACar(null));
       CarUsage carUsage = new CarUsage();
       assertTrue(canOrderNewCarService.hasACar(carUsage));
       carUsage.setEndDate(new Date());
       assertFalse(canOrderNewCarService.hasACar(carUsage));
   }


    @Test
    public void getLastCarUsageEmployeePerformedCorrectly(){
        Employee emp = new Employee();
        emp.setEmail("email1");
        assertNull(canOrderNewCarService.getLastCarUsageEmployee(emp));
        when(carUsageRepository.findByEmployee("email1")).thenReturn(dummyCarUsagesList());
        CarUsage foundUsage = canOrderNewCarService.getLastCarUsageEmployee(emp);
        assertNotNull(foundUsage);
        assertEquals(new Long(2), foundUsage.getId());
        verify(carUsageRepository, times(2)).findByEmployee("email1");
    }

    private List<CarUsage> dummyCarUsagesList() {
        List<CarUsage> cus = new ArrayList<>();
        CarUsage cu1 = new CarUsage();
        cu1.setId(1L);
        cu1.setInitialEndDate(Utils.addDaysToDate(new Date(), -10D));
        cus.add(cu1);
        CarUsage cu2 = new CarUsage();
        cu2.setId(2L);
        cu2.setInitialEndDate(Utils.addDaysToDate(new Date(), 10D));
        cu2.setStartDate(new Date());
        cus.add(cu2);
        CarUsage cu3 = new CarUsage();
        cu3.setId(3L);
        cu3.setInitialEndDate(Utils.addDaysToDate(new Date(), 5D));
        cus.add(cu3);
        return cus;
    }
}
