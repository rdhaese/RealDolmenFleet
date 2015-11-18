package com.realdolmen.fleet.comparator;

import com.realdolmen.fleet.model.CarUsage;

import java.util.Comparator;

/**
 * Created on 2/11/2015.
 * Comparator implementation for {@link CarUsage}. Compares 2 instances on their orderDate (descending, for example: '5/10/2010' is put before '10/10/2010' in a list).
 * @author Robin D'Haese
 */
public class CarUsageOnOrderDateComparator implements Comparator<CarUsage> {

    /**
     * @param o1 CarUsage 1
     * @param o2 CarUsage 2
     * @return value < 0 if o1.orderDate is before o2.orderDate, else a value > 0. If the dates are equal, 0 is returned.
     */
    @Override
    public int compare(CarUsage o1, CarUsage o2) {
        return o1.getOrderDate().compareTo(o2.getOrderDate());
    }
}
