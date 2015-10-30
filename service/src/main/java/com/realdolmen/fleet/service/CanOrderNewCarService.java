package com.realdolmen.fleet.service;

import org.springframework.stereotype.Service;

/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class CanOrderNewCarService {

    public boolean canOrderNewCar() {
        return true; //TODO criteria
        //return (noOpenOrder() && (hasNoCar() || reachedEndDate() || reachedMaxKm()));
    }

    private boolean noOpenOrder() {
        return false;
        /*get logged in user
        * get usage for logged in user
        * where enddate = null
        * if none return true */
    }

    private boolean reachedMaxKm() {
        return false;
        /* get logged in user
            get usage for logged in user
            where car.currentkm
         */
    }

    private boolean reachedEndDate() {
        return false;
    }

    private boolean hasNoCar() {
        return false;
    }
}
