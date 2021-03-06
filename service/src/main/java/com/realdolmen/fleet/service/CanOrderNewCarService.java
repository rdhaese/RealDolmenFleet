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

    public boolean loggedInEmployeeCanOrderNewCar() {
        return employeeService.getLoggedInUser().isPermissionToOrderNewCar();
    }

    public boolean needsPermission(Employee employee) {
        CarUsage carUsage = getLastCarUsageEmployee(employee);
        return ((!hasOpenOrder(carUsage) && (!hasACar(carUsage)) || reachedEndDate(carUsage) || reachedIdealKm(carUsage)));
    }

    public boolean hasOpenOrder(CarUsage carUsage) {
        if (carUsage == null){
            return false;
        }
        Date now = new Date();
        if (carUsage.getStartDate().before(now)){
            return true;
        }
        return false;
    }

    public boolean reachedIdealKm( CarUsage carUsage) {
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

    public boolean reachedEndDate( CarUsage carUsage) {
        Date currentDate = new Date();
        Date initialEndDate = carUsage.getInitialEndDate();
        if (currentDate.before(initialEndDate)){
            return false;
        }
        return true;
    }

    public boolean hasACar( CarUsage carUsage) {
        if (carUsage == null){
            return false;
        }
        if (carUsage.getEndDate() != null){
            return false;
        }
        return true;
    }

    public CarUsage getLastCarUsageEmployee(Employee employee){
        TreeSet<CarUsage> carUsages = new TreeSet<>(carUsageRepository.findByEmployee(employee.getEmail()));
        if (carUsages.isEmpty()){
            return null;
        }
        return carUsages.last();
    }
}
