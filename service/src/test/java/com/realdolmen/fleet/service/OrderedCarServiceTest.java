package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.*;
import com.realdolmen.fleet.persist.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import static org.mockito.Mockito.*;

/**
 * Created on 5/11/2015.
 * Test class for {@link OrderedCarService}
 * @author Robin D'Haese
 */
@SpringApplicationConfiguration(classes = OrderedCarService.class)
public class OrderedCarServiceTest extends AbstractServiceTest {

    @Autowired
    private OrderedCarService orderedCarService;

    @Autowired
    private PackRepository packRepository;
    @Autowired
    private CarOptionsRepository carOptionsRepository;
    @Autowired
    private CarUsageRepository carUsageRepository;
    @Autowired
    private OrderedCarRepository orderedCarRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void getPackOnIdPerformedCorrectly(){
        orderedCarService.getPackOnId(1L);
        verify(packRepository, times(1)).findOne(1L);
    }

    @Test
    public void getOptionOnIdPerformedCorrectly(){
        orderedCarService.getOptionOnId(1L);
        verify(carOptionsRepository, times(1)).findOne(1L);
    }

    @Test
    public void placeOrderPerformedCorrectly(){
        CarUsage carUsage = new CarUsage();
        carUsage.setEmployee(new Employee());
        carUsage.setOrderedCar(new OrderedCar());
        orderedCarService.placeOrder(carUsage);
        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(orderedCarRepository, times(1)).save(any(OrderedCar.class));
        verify(carUsageRepository, times(1)).save(any(CarUsage.class));
    }
}
