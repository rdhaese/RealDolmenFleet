package com.realdolmen.fleet.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created on 4/11/2015.
 * Unit test for {@link Utils}
 * @author Robin D'Haese
 */
public class UtilsTest {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void isListConvertedToMultiDimensionalListCorrectly(){
        List<String> singleDimensionalList = new ArrayList<>();
        for (Integer index = 1; index <= 100; index++){
            singleDimensionalList.add(index.toString());
        }
        List<List<String>> twoDimensionalList = Utils.toTwoDimensionalList(singleDimensionalList, 5);
        assertEquals(20, twoDimensionalList.size());
        for (List<String> innerList : twoDimensionalList){
            assertEquals(5, innerList.size());
        }
        assertEquals("1", twoDimensionalList.get(0).get(0));
        assertEquals("5", twoDimensionalList.get(0).get(4));
        assertEquals("96", twoDimensionalList.get(19).get(0));
        assertEquals("100", twoDimensionalList.get(19).get(4));

        twoDimensionalList = Utils.toTwoDimensionalList(singleDimensionalList, 95);
        assertEquals(2, twoDimensionalList.size());
        assertEquals(95, twoDimensionalList.get(0).size());
        assertEquals(5, twoDimensionalList.get(1).size());

        twoDimensionalList = Utils.toTwoDimensionalList(singleDimensionalList, 110);
        assertEquals(1, twoDimensionalList.size());
        assertEquals(100, twoDimensionalList.get(0).size());
    }

    @Test
    public void areDaysAddedToDateCorrectly() throws ParseException {
        Date date = FORMAT.parse("10/10/2010");
        assertEquals("20/10/2010", FORMAT.format(Utils.addDaysToDate(date, 10.9)));
        assertEquals("20/10/2010", FORMAT.format(Utils.addDaysToDate(date, 10.1)));
        assertEquals("10/10/2010", FORMAT.format(Utils.addDaysToDate(date, 0.9)));
        assertEquals("10/10/2010", FORMAT.format(Utils.addDaysToDate(date, 0D)));
        assertEquals("30/09/2010", FORMAT.format(Utils.addDaysToDate(date, -10D)));
    }
}
