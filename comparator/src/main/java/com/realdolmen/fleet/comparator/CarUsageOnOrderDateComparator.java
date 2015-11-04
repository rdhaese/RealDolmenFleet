package com.realdolmen.fleet.comparator;

import com.realdolmen.fleet.model.CarUsage;

import java.util.Comparator;

/**
 * Created on 2/11/2015.
 *
 * @author Robin D'Haese
 */
public class CarUsageOnOrderDateComparator implements Comparator<CarUsage> {
    @Override
    public int compare(CarUsage o1, CarUsage o2) {
        return o1.getOrderDate().compareTo(o2.getOrderDate());
    }
}
