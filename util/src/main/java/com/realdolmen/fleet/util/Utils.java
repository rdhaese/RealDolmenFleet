package com.realdolmen.fleet.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created on 29/10/2015.
 * Utility class. Contains static methods for various purposes.
 * @author Robin D'Haese
 */
public final class Utils {
    /**
     * Instantiating is disabled
     */
    private Utils() {
    }

    /**
     * Utility method to turn a one dimensional list into a two dimensional list. The second dimension lists their size are limited to the given boundary.<br/>
     * In example: When the one dimensional list contains 10 items and the given boundary is 3, then the method will return a list with 4 inner lists.
     * The first 3 lists will contain 3 elements of the given class and the last list will contain 1 element.
     *
     * @param oneDimensionalList to turn into two dimensional list
     * @param boundary           of the second dimension its size
     * @param <T>                Type of the second dimension its elements
     * @return the two dimensional list; is empty when a boundary lower than 1 is given.
     */
    public static <T> List<List<T>> toTwoDimensionalList(List<T> oneDimensionalList, int boundary) {
        if (boundary <= 0) {
            return new ArrayList<List<T>>();
        }
        List<List<T>> twoDimensionalList = new ArrayList<>();
        int listIndex = -1;
        for (int index = 0; index < oneDimensionalList.size(); index++) {
            if (index % boundary == 0) {
                twoDimensionalList.add(new ArrayList<>());
                listIndex++;
            }
            twoDimensionalList.get(listIndex).add(oneDimensionalList.get(index));
        }
        return twoDimensionalList;
    }


    /**
     * Adds the given days to the given date.
     * It is also possible to subtract days by giving a negative value in daysToAdd.
     * The method takes a Double as argument for the daysToAdd, this will be converted to an int using Double.intValue() before adding it to the date.
     *
     * @param date      to add days to
     * @param daysToAdd amount of days to add to date
     * @return a new date = date + daysToAdd
     */
    public static Date addDaysToDate(Date date, Double daysToAdd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, daysToAdd.intValue());
        return cal.getTime();
    }
}
