package com.realdolmen.fleet.formatter;

import com.realdolmen.fleet.model.CarOption;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Locale;

import static junit.framework.Assert.assertEquals;

/**
 * Created on 6/11/2015.
 *
 * @author Robin D'Haese
 */
public class CarOptionFormatterTest {

    private CarOptionFormatter carOptionFormatter;

    @Before
    public void setUp(){
        carOptionFormatter = new CarOptionFormatter();
    }

    @Test
    public void isParsedCorrectly() throws ParseException {
        CarOption carOption = carOptionFormatter.parse("1", Locale.US);
        assertEquals(new Long(1), carOption.getId());
    }

    @Test
    public void isPrintedCorrectly(){
        CarOption carOption = new CarOption();
        carOption.setId(1L);
        assertEquals("1", carOptionFormatter.print(carOption, Locale.US));
    }
}
