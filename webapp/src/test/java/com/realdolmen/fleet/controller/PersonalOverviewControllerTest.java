package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.config.TestConfig;
import com.realdolmen.fleet.enums.EmployeeType;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created on 6/11/2015.
 *
 * @author Robin D'Haese
 */
@ContextConfiguration(classes = PersonalOverviewController.class)
public class PersonalOverviewControllerTest extends AbstractControllerTest {

    @Before
    public void setUp(){
        when(employeeService.getLoggedInUser()).thenReturn(dummyEmployee());
    }

    private Employee dummyEmployee() {
        return new Employee("name", "email", "password", EmployeeType.ROLE_FLEET, 3);
    }

    private CarUsage dummyCarUsage() {
        CarUsage carUsage = new CarUsage();
        carUsage.setEmployee(dummyEmployee());
        return carUsage;
    }

    @Test
    public void isListeningToTheRightRequest() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("employees/overview"));

        mockMvc.perform(get("/index")).andExpect(status().isOk())
                .andExpect(view().name("employees/overview"));

        mockMvc.perform(get("/employees")).andExpect(status().isOk())
                .andExpect(view().name("employees/overview"));

        mockMvc.perform(get("/fleet")).andExpect(status().isOk())
                .andExpect(view().name("employees/overview"));

        mockMvc.perform(get("/employees/overview")).andExpect(status().isOk())
                .andExpect(view().name("employees/overview"));
    }

    @Test
    public void isLoggedInEmployeeSetOnModel() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(model().attribute("employee", dummyEmployee()));
    }

    @Test
    public void isCarUsageSetOnModelIfLoggedInEmployeeHasOne() throws Exception {
        when(carService.findCarUsageForEmployee(dummyEmployee().getEmail())).thenReturn(dummyCarUsage());
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(model().attribute("carUsage", dummyCarUsage()));
    }

    @Test
    public void isNoCarUsageSetOnModelIfLoggedInEmployeeHasNone() throws Exception {
        when(carService.findCarUsageForEmployee(dummyEmployee().getEmail())).thenReturn(null);
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("carUsage"));
    }
}
