package com.realdolmen.fleet.service;

import com.realdolmen.fleet.comparator.CarUsageOnOrderDateComparator;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.PeriodicUsageUpdate;
import com.realdolmen.fleet.persist.CarUsageRepository;
import com.realdolmen.fleet.persist.PeriodicUsageUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

/**
 * Created on 2/11/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class ConfirmOrderService {

    @Autowired
    private CarUsageRepository carUsageRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private PeriodicUsageUpdateRepository periodicUsageUpdateRepository;

    public List<CarUsage> getOrdersToConfirm() {
        TreeSet<CarUsage> sortedResult = new TreeSet(new CarUsageOnOrderDateComparator());
        sortedResult.addAll(carUsageRepository.findCarUsagesWithoutLicensePlate());
        return new ArrayList<CarUsage>(sortedResult);
    }

    public CarUsage getOrder(long id) {
        return carUsageRepository.findOne(id);
    }

    public void confirmOrder(CarUsage order) {
        CarUsage carUsage = carUsageRepository.findCurrentUsage(order.getEmployee().getId());
        if (carUsage != null){
            carService.backToFreePool(carUsage);
        }
        if (order.getUsageUpdates().isEmpty()) {
            PeriodicUsageUpdate firstUsageUpdate = new PeriodicUsageUpdate(new Date(), 1, 0, 0);
            periodicUsageUpdateRepository.save(firstUsageUpdate);
            order.getUsageUpdates().add(firstUsageUpdate);
        }
        carUsageRepository.save(order);
    }

    public boolean isFromFreePool(CarUsage order) {
        return (!order.getUsageUpdates().isEmpty()) && (order.getUsageUpdates().get(order.getUsageUpdates().size() - 1).getNewTotalKm() > 0);
    }
}
