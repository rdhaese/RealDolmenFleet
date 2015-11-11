package com.realdolmen.fleet.comparator;

import com.realdolmen.fleet.model.PeriodicUsageUpdate;

import java.util.Comparator;

/**
 * Created on 11/11/2015.
 *
 * @author Robin D'Haese
 */
public class PeriodicUsageUpdateComparator implements Comparator<PeriodicUsageUpdate> {
    @Override
    public int compare(PeriodicUsageUpdate o1, PeriodicUsageUpdate o2) {
        return Integer.compare(o2.getNewTotalKm(), o1.getNewTotalKm());
    }
}
