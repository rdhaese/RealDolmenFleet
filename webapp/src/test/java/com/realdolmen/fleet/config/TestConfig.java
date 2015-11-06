package com.realdolmen.fleet.config;

import com.realdolmen.fleet.listener.LoginListener;
import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.persist.*;
import com.realdolmen.fleet.service.CanOrderNewCarService;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.service.EmployeeService;
import com.realdolmen.fleet.service.MailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

/**
 * Created on 5/11/2015.
 *
 * @author Robin D'Haese
 */

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public MockMvc mockMvc(WebApplicationContext context){
       return MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Bean
    public CarService carService(){
        return mock(CarService.class);
    }

    @Bean
    public EmployeeService employeeService(){
        return mock(EmployeeService.class);
    }

    @Bean
    public CanOrderNewCarService canOrderNewCarService(){
        return mock(CanOrderNewCarService.class);
    }

    @Bean
    public MailService mailService(){
        return mock(MailService.class);
    }

    @Bean
    public LoginListener loginListener(){
        return mock(LoginListener.class);
    }

    @Bean
    public PeriodicUsageUpdateRepository periodicUsageUpdateRepository(){
        return mock(PeriodicUsageUpdateRepository.class);
    }

    @Bean
    public CarOptionsRepository carOptionsRepository(){
        return mock(CarOptionsRepository.class);
    }

    @Bean
    public CarRepository carRepository(){
        return mock(CarRepository.class);
    }

    @Bean
    public EmployeeRepository employeeRepository(){
        return mock(EmployeeRepository.class);
    }

    @Bean
    public OrderedCarRepository orderedCarRepository(){
        return mock(OrderedCarRepository.class);
    }

    @Bean
    public PackRepository packRepository(){
        return mock(PackRepository.class);
    }

    @Bean
    public CarUsageRepository carUsageRepository(){
        return mock(CarUsageRepository.class);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public Database database() {
        return Database.H2;
    }

}
