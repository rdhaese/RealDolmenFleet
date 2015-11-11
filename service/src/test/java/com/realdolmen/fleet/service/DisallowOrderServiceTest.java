package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created on 11/11/2015.
 * Test class for {@link DisallowOrderService}
 * @author Robin D'Haese
 */
@SpringApplicationConfiguration(classes = {DisallowOrderService.class, DisallowOrderServiceTest.InnerConfig.class})
public class DisallowOrderServiceTest extends AbstractServiceTest {
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
    private DisallowOrderService disallowOrderService;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MailService mailService;

    @Test
    public void allowTest(){
        Employee emp = new Employee();
        emp.setPermissionToOrderNewCar(true);
        disallowOrderService.disallow(emp);
        verify(employeeService, times(1)).save(emp);
        verify(mailService, times(1)).sendMail(any(), any(), any());
        assertFalse(emp.isPermissionToOrderNewCar());
    }
}
