package com.realdolmen.fleet.comparator;

import com.realdolmen.fleet.model.HistoryRecord;

import java.util.Comparator;

/**
 * Created on 11/11/2015.
 *
 * @author Robin D'Haese
 */
public class HistoryRecordComparator implements Comparator<HistoryRecord> {
    @Override
    public int compare(HistoryRecord o1, HistoryRecord o2) {
        return Integer.compare(o2.getLastKm(), o1.getLastKm());
    }
}
