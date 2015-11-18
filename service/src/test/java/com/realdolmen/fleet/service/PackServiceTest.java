package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.persist.PackRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created on 5/11/2015.
 * Test class for {@link PackService}
 * @author Robin D'Haese
 */
@SpringApplicationConfiguration(classes = {PackService.class,PackServiceTest.InnerConfig.class})
public class PackServiceTest extends AbstractServiceTest {
    @Configuration
    @Profile("test")
    static class InnerConfig{
        @Bean
        public CarOptionsService carOptionsService(){
            return  mock(CarOptionsService.class);
        }
    }

    @Autowired
    private PackService packService;

    @Autowired
    private PackRepository packRepository;
    @Autowired
    private CarOptionsRepository carOptionsRepository;
    @Autowired
    private CarOptionsService carOptionsService;

    @Test
    public void findAllPerformedCorrectly(){
        packService.findAll();
        verify(packRepository, times(1)).findByNameNot("RealDolmen General Options");
    }

   @Test
   public void findGeneralRdOptionsPerformedCorrectly(){
       packService.findGeneralRDOptions();
       verify(packRepository, times(1)).findByName("RealDolmen General Options");
   }

    @Test
    public void savePackPerformedCorrectly(){
        packService.savePack(new Pack());
        verify(packRepository, times(1)).save(any(Pack.class));
    }

    @Test
    public void getCarOptionPerformedCorrectly(){
        packService.getCarOption(1L);
        verify(carOptionsService, times(1)).getCarOptionByID(1L);
    }

    @Test
    public void findPackByIdPerformedCorrectly(){
        packService.findPackById(1L);
        verify(packRepository, times(1)).findOne(1L);
    }
}
