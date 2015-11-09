package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.config.TestConfig;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created on 5/11/2015.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public abstract class AbstractRepositoryTest extends Assert{
    @Autowired
    protected CarUsageRepository carUsageRepository;
    @Autowired
    protected PackRepository packRepository;
    @Autowired
    protected CarOptionsRepository carOptionsRepository;
    @Autowired
    protected OrderedCarRepository orderedCarRepository;
    @Autowired
    protected CarRepository carRepository;
    @Autowired
    protected EmployeeRepository employeeRepository;

    protected void deleteAll(){
        carUsageRepository.deleteAll();
        employeeRepository.deleteAll();
        orderedCarRepository.deleteAll();
        carRepository.deleteAll();
        packRepository.deleteAll();
        carOptionsRepository.deleteAll();
    }
}
