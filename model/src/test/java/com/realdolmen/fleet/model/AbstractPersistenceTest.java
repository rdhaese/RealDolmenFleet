package com.realdolmen.fleet.model;

import com.realdolmen.fleet.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static junit.framework.Assert.assertEquals;

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

    protected String getStringOfXCharacters(int x){
        if (x <= 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < x; index++){
            sb.append("a");
        }
        return sb.toString();
    }

    @Test
    public void isAStringOfCorrectAmountOfCharactersReturned(){
        assertEquals(10, getStringOfXCharacters(10).length());
        assertEquals(1000, getStringOfXCharacters(1000).length());
        assertEquals(0, getStringOfXCharacters(0).length());
        assertEquals(0, getStringOfXCharacters(-10).length());
    }

}
