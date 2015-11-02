package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.Pack;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.persist.CarUsageRepository;
import com.realdolmen.fleet.persist.OrderedCarRepository;
import com.realdolmen.fleet.persist.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created on 2/11/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class OrderedCarService {

    @Autowired
    private PackRepository packRepository;
    @Autowired
    private CarOptionsRepository carOptionsRepository;
    @Autowired
    private CarUsageRepository carUsageRepository;
    @Autowired
    private OrderedCarRepository orderedCarRepository;

    public Pack getPackOnId(Long packId) {
        return  packRepository.findOne(packId);
    }

    public CarOption getOptionOnId(Long optionId) {
        return carOptionsRepository.findOne(optionId);
    }

    //TODO @Transactional
    public void placeOrder(CarUsage carUsage) {
        orderedCarRepository.save(carUsage.getOrderedCar());
        carUsageRepository.save(carUsage);
    }
}
