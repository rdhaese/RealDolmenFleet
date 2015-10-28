package com.realdolmen.fleet;

import config.Config;
import org.apache.log4j.Logger;
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
    private static final Logger LOGGER = Logger.getLogger(ApplicationConfiguration.class);
    public static void main (String[] args){
        LOGGER.info("APPLICATION STARTUP");
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ApplicationConfiguration.class).profiles("production").build().run(args);
        LOGGER.info("APPLICATION STARTED");
    }
}
