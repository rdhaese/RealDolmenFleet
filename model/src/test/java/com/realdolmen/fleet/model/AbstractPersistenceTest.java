package com.realdolmen.fleet.model;

import com.realdolmen.fleet.config.TestConfig;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created on 28/10/2015.
 * Default persistence test configuration, extend from this class when testing persistence abilities of an Entity
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
@Transactional
public abstract class AbstractPersistenceTest {

    @PersistenceContext
    protected EntityManager em;

}
