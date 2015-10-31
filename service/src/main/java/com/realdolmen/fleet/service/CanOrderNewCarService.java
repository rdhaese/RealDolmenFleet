package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.model.PeriodicUsageUpdate;
import com.realdolmen.fleet.persist.CarUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.TreeSet;

/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class CanOrderNewCarService {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CarUsageRepository carUsageRepository;

    //TODO test all this
    public boolean loggedInEmployeeCanOrderNewCar() {
        CarUsage carUsage = getLastCarUsageForLoggedInUser();
        return (!hasOpenOrder(carUsage) && (!hasACar(carUsage) || reachedEndDate(carUsage) || reachedIdealKm(carUsage)));
    }

    private boolean hasOpenOrder( CarUsage carUsage) {
        if (carUsage == null){
            return false;
        }
        Date now = new Date();
        if (carUsage.getStartDate().before(now)){
            return true;
        }
        return false;
    }

    private boolean reachedIdealKm( CarUsage carUsage) {
        List<PeriodicUsageUpdate> usageUpdates = carUsage.getUsageUpdates();
        if (usageUpdates.isEmpty()){
            return false;
        }
        int currentKm = carUsage.getUsageUpdates().get(carUsage.getUsageUpdates().size() - 1).getNewTotalKm();
        int idealKm = carUsage.getOrderedCar().getCar().getIdealKm();
        if (currentKm < idealKm){
            return false;
        }
        return true;
    }

    private boolean reachedEndDate( CarUsage carUsage) {
        Date currentDate = new Date();
        Date initialEndDate = carUsage.getInitialEndDate();
        if (currentDate.before(initialEndDate)){
            return false;
        }
        return true;
    }

    private boolean hasACar( CarUsage carUsage) {
        if (carUsage == null){
            return false;
        }
        if (carUsage.getEndDate() != null){
            return false;
        }
        return true;
    }

    private CarUsage getLastCarUsageForLoggedInUser(){
        Employee loggedInUser = employeeService.getLoggedInUser();
        TreeSet<CarUsage> carUsages = new TreeSet<>(carUsageRepository.findByEmployee(loggedInUser.getEmail()));
        if (carUsages.isEmpty()){
            return null;
        }
        return carUsages.last();
    }
}
