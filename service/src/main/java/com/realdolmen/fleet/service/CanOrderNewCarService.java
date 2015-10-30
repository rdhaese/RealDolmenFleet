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
        //return ((hasNoCar() || reachedEndDate() || reachedMaxKm()) && noOpenOrder());
    }

    private boolean noOpenOrder() {
        return false;
    }

    private boolean reachedMaxKm() {
        return false;
    }

    private boolean reachedEndDate() {
        return false;
    }

    private boolean hasNoCar() {
        return false;
    }
}
