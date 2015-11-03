package com.realdolmen.fleet.service;

import com.realdolmen.fleet.comparator.CarUsageOnOrderDateComparator;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.persist.CarUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<CarUsage> getOrdersToConfirm() {
        TreeSet<CarUsage> sortedResult = new TreeSet(new CarUsageOnOrderDateComparator());
        sortedResult.addAll(carUsageRepository.findCarUsagesWithoutLicensePlate());
        return new ArrayList<CarUsage>(sortedResult);
    }

    public CarUsage getOrder(long id) {
        return carUsageRepository.findOne(id);
    }

    public void confirmOrder(CarUsage order) {
        carUsageRepository.save(order);
    }
}
