package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.persist.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created on 5/11/2015.
 * Test class for {@link EmployeeService}
 * @author Robin D'Haese
 */
@SpringApplicationConfiguration(classes = {EmployeeService.class, EmployeeServiceTest.InnerConfig.class})
public class EmployeeServiceTest extends AbstractServiceTest {
    @Configuration
    @Profile("test")
    static class InnerConfig {
        @Bean
        public CanOrderNewCarService canOrderNewCarService() {
            return mock(CanOrderNewCarService.class);
        }

        @Bean
        public MailService mailService() {
            return mock(MailService.class);
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CanOrderNewCarService canOrderNewCarService;

    @Autowired
    private MailService mailService;

    @Before
    public void setUp(){
        reset(employeeRepository, canOrderNewCarService, mailService);
    }

    @Test
    public void findAllPerformedCorrectly() {
        employeeService.findAll();
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void findUserOnEmailPerformedCorrectly() {
        employeeService.findUserOnEmail("email");
        verify(employeeRepository, times(1)).findByEmail("email");
    }

    @Test
    public void findUseryByIdPerformedCorrectly() {
        employeeService.findUserById(1L);
        verify(employeeRepository, times(1)).findOne(1L);
    }

    @Test
    public void savePerformedCorrectly(){
        employeeService.save(new Employee());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

   @Test
   public void saveNewEmployeePerfomedCorrectly(){
       employeeService.saveNewEmployee(new Employee(), "password");
       verify(employeeRepository, times(1)).save(any(Employee.class));
       verify(mailService, times(1)).sendMail(any(String.class), any(String.class), any(String.class));
   }

    @Test
    public void getUsersThatNeedOrderPermissionPerformedCorrectly(){
        when(employeeRepository.findEmployeesWithoutPermissionTOrderNewCar()).thenReturn(dummyEmployees());
        assertEquals(0, employeeService.getUsersThatNeedOrderPermission().size());
        verify(employeeRepository, times(1)).findEmployeesWithoutPermissionTOrderNewCar();
        verify(canOrderNewCarService, times(3)).needsPermission(any(Employee.class));
    }

    private List<Employee> dummyEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());
        employees.add(new Employee());
        return employees;
    }

    @Test
    public void saveEditedEmployeePerformedCorrectly(){
        when(employeeRepository.findOne(1L)).thenReturn(new Employee());
        employeeService.saveEditedEmployee(1L, new Employee());
        verify(employeeRepository, times(1)).findOne(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }
}
