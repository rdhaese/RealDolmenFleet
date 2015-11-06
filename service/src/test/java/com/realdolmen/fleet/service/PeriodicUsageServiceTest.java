package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.PeriodicUsageUpdate;
import com.realdolmen.fleet.persist.EmployeeRepository;
import com.realdolmen.fleet.persist.PeriodicUsageUpdateRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

/**
 * Created on 5/11/2015.
 *
 * @author Robin D'Haese
 */
@SpringApplicationConfiguration(classes = PeriodicUsageService.class)
public class PeriodicUsageServiceTest extends AbstractServiceTest {

    @Autowired
    private PeriodicUsageService periodicUsageService;

    @Autowired
    private PeriodicUsageUpdateRepository periodicUsageUpdateRepository;

    @Test
    public void canAllBeFound(){
        periodicUsageService.findAll();
        Mockito.verify(periodicUsageUpdateRepository, times(1)).findAll();
    }

    @Test
    public void canPeriodUpdateBeSaved(){
        PeriodicUsageUpdate puu = new PeriodicUsageUpdate();
        periodicUsageService.savePeriodicUpdate(puu);
        Mockito.verify(periodicUsageUpdateRepository, times(1)).save(any(PeriodicUsageUpdate.class));
    }
}
