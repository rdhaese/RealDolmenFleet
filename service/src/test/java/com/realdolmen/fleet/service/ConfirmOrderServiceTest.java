package com.realdolmen.fleet.service;

import com.realdolmen.fleet.comparator.CarUsageOnOrderDateComparator;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.model.PeriodicUsageUpdate;
import com.realdolmen.fleet.persist.CarUsageRepository;
import com.realdolmen.fleet.persist.PeriodicUsageUpdateRepository;
import com.realdolmen.fleet.util.Utils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import static org.mockito.Mockito.*;

/**
 * Created on 5/11/2015.
 * Test class for {@link ConfirmOrderService}
 * @author Robin D'Haese
 */
@SpringApplicationConfiguration(classes = {ConfirmOrderService.class, ConfirmOrderServiceTest.InnerConfig.class})
public class ConfirmOrderServiceTest extends AbstractServiceTest {

    @Configuration
    @Profile("test")
    static class InnerConfig{
        @Bean
        public CarService carService(){
            return mock(CarService.class);
        }
    }
    @Autowired
    private ConfirmOrderService confirmOrderService;
    @Autowired
    private CarUsageRepository carUsageRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private PeriodicUsageUpdateRepository periodicUsageUpdateRepository;

    @Test
    public void getOrdersToConfirmPerformedCorrectly(){
        when(carUsageRepository.findCarUsagesWithoutLicensePlate()).thenReturn(dummyCarUsages());
        List<CarUsage> carUsages = confirmOrderService.getOrdersToConfirm();
        verify(carUsageRepository, times(1)).findCarUsagesWithoutLicensePlate();
        assertTrue(carUsages.get(0).getOrderDate().before(carUsages.get(1).getOrderDate()));
        assertTrue(carUsages.get(1).getOrderDate().before(carUsages.get(2).getOrderDate()));
    }

    private List<CarUsage> dummyCarUsages() {
        List<CarUsage> carUsages = new ArrayList<>();
        CarUsage cu1 = new CarUsage();
        cu1.setOrderDate(Utils.addDaysToDate(new Date(), 5D));
        carUsages.add(cu1);
        CarUsage cu2 = new CarUsage();
        cu2.setOrderDate(Utils.addDaysToDate(new Date(), -10D));
        carUsages.add(cu2);
        CarUsage cu3 = new CarUsage();
        cu3.setOrderDate(Utils.addDaysToDate(new Date(), 10D));
        carUsages.add(cu3);
        return carUsages;
    }

    @Test
    public void getOrderPerformedCorrectly(){
        confirmOrderService.getOrder(1L);
        verify(carUsageRepository, times(1)).findOne(1L);
    }

    @Test
    public void confirmOrderPerformedCorrectly(){
        CarUsage carUsage = mock(CarUsage.class);
        Employee employee = mock(Employee.class);
        when(employee.getId()).thenReturn(1L);
        when(carUsage.getEmployee()).thenReturn(employee);

        confirmOrderService.confirmOrder(carUsage);
        verify(carUsageRepository, times(1)).findCurrentUsage(1L);
        verify(periodicUsageUpdateRepository, times(1)).save(any(PeriodicUsageUpdate.class));
        verify(carUsageRepository, times(1)).save(any(CarUsage.class));

        when(carUsageRepository.findCurrentUsage(1L)).thenReturn(new CarUsage());
        confirmOrderService.confirmOrder(carUsage);
        verify(carService, times(1)).backToFreePool(any(CarUsage.class));
    }

    @Test
    public void isFromFreePoolPerformedCorrectly(){
        CarUsage carUsage = new CarUsage();
        assertFalse(confirmOrderService.isFromFreePool(carUsage));
        carUsage.addUsageUpdate(new PeriodicUsageUpdate(new Date(), 0, 0, 0));
        assertFalse(confirmOrderService.isFromFreePool(carUsage));
        carUsage.addUsageUpdate(new PeriodicUsageUpdate(new Date(), 10, 10D, 10D));
        assertTrue(confirmOrderService.isFromFreePool(carUsage));
    }
}
