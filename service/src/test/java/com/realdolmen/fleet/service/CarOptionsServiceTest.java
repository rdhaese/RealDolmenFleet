package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;


import static org.mockito.Mockito.*;

/**
 * Created on 5/11/2015.
 * Test class for {@link CarOptionsService}
 * @author Robin D'Haese
 */
@SpringApplicationConfiguration(classes = CarOptionsService.class)
public class CarOptionsServiceTest extends AbstractServiceTest {

    @Autowired
    private CarOptionsService carOptionsService;
    @Autowired
    private CarOptionsRepository carOptionsRepository;

    @Test
    public void findAllPerformsCorrectly() {
        carOptionsService.findAll();
        verify(carOptionsRepository, times(1)).findAll();
    }

    @Test
    public void saveCarOptionPerformsCorrectly() {
        carOptionsService.saveCarOption(new CarOption());
        verify(carOptionsRepository, times(1)).save(any(CarOption.class));
    }

    @Test
    public void getCarOptionsPerformsCorrectly() {
        carOptionsService.getCarOptions(2);
        verify(carOptionsRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    public void getCarOptionsByIdPerformsCorrectly() {
        carOptionsService.getCarOptionByID(2L);
        verify(carOptionsRepository, times(1)).findOne(2L);
    }
}
