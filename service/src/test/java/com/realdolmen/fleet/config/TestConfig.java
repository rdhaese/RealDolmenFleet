package com.realdolmen.fleet.config;

import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.persist.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
}
