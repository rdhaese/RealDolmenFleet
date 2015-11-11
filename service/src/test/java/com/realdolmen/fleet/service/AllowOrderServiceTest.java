package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.transaction.Transactional;

import static org.mockito.Mockito.*;

/**
 * Created on 11/11/2015.
 *
 * @author Robin D'Haese
 */
@SpringApplicationConfiguration(classes = {AllowOrderService.class, AllowOrderServiceTest.InnerConfig.class})
public class AllowOrderServiceTest extends AbstractServiceTest {
    @Configuration
    @Profile("test")
    static class InnerConfig {
        @Bean
        public EmployeeService employeeService() {
            return mock(EmployeeService.class);
        }

        @Bean
        public CanOrderNewCarService canOrderNewCarService(){ return mock(CanOrderNewCarService.class);}

        @Bean
        public MailService mailService() {
            return mock(MailService.class);
        }

        @Bean
        public MessageSource messageSource(){ return mock(MessageSource.class);}
    }

    @Autowired
    private AllowOrderService allowOrderService;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MailService mailService;

    @Test
    public void allowTest(){
        Employee emp = new Employee();
        allowOrderService.allow(emp);
        verify(employeeService, times(1)).save(emp);
        verify(mailService, times(1)).sendMail(any(), any(), any());
        assertTrue(emp.isPermissionToOrderNewCar());
    }

}
