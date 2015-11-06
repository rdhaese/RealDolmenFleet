package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.config.TestConfig;
import com.realdolmen.fleet.persist.*;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.service.EmployeeService;
import junit.framework.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;

/**
 * Created on 5/11/2015.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
@WebAppConfiguration
public abstract class AbstractControllerTest extends Assert {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected CarService carService;

    @Autowired
    protected EmployeeService employeeService;



}
