package com.realdolmen.fleet.comparator;

import com.realdolmen.fleet.model.CarUsage;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created on 4/11/2015.
 * Unit test for {@link CarUsageOnOrderDateComparator}
 * @author Robin D'Haese
 */
public class CarUsageOnOrderDateComparatorTest {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private List<CarUsage> carUsages;
    private CarUsage c1;
    private CarUsage c2;
    private CarUsage c3;
    private CarUsage c4;

    @Before
    public void setUp() throws ParseException{
        carUsages = new ArrayList<>();
         c1 = new CarUsage();
         c2 = new CarUsage();
         c3 = new CarUsage();
        c4 = new CarUsage();
        c1.setOrderDate(FORMAT.parse("10/10/2010"));
        c2.setOrderDate(FORMAT.parse("15/10/2010"));
        c3.setOrderDate(FORMAT.parse("5/10/2010"));
        c4.setOrderDate(FORMAT.parse("20/10/2010"));
        carUsages.add(c1);
        carUsages.add(c2);
        carUsages.add(c3);
        carUsages.add(c4);
    }

    @Test
    public void areCarUsagesOrderedOnDateCorrectly(){
        assertEquals(c1, carUsages.get(0));
        assertEquals(c2, carUsages.get(1));
        assertEquals(c3, carUsages.get(2));
        assertEquals(c4, carUsages.get(3));

        carUsages.sort(new CarUsageOnOrderDateComparator());

        assertEquals(c3, carUsages.get(0)); // 5/10/2010
        assertEquals(c1, carUsages.get(1)); // 10/10/2010
        assertEquals(c2, carUsages.get(2)); // 15/10/2010
        assertEquals(c4, carUsages.get(3)); // 20/10/2010
    }
}
