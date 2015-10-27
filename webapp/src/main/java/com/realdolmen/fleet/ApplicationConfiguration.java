package com.realdolmen.fleet;

import config.Config;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created on 27/10/2015.
 *
 * @author Robin D'Haese
 */
@SpringBootApplication
public class ApplicationConfiguration {
    public static void main (String[] args){
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ApplicationConfiguration.class).profiles("production").build().run(args);

    }
}
