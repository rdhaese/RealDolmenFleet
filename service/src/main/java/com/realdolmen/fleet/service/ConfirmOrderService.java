package com.realdolmen.fleet.service;

import com.realdolmen.fleet.comparator.CarUsageOnOrderDateComparator;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.PeriodicUsageUpdate;
import com.realdolmen.fleet.persist.CarUsageRepository;
import com.realdolmen.fleet.persist.PeriodicUsageUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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
    @Autowired
    private MailService mailService;
    @Autowired
    private MessageSource messageSource;

    public List<CarUsage> getOrdersToConfirm() {
        TreeSet<CarUsage> sortedResult = new TreeSet(new CarUsageOnOrderDateComparator());
        sortedResult.addAll(carUsageRepository.findCarUsagesWithoutLicensePlate());
        return new ArrayList<CarUsage>(sortedResult);
    }

    public CarUsage getOrder(long id) {
        return carUsageRepository.findOne(id);
    }

    @Transactional
    public void confirmOrder(CarUsage order, CarUsage oldUsage) {
        String mail = order.getEmployee().getEmail();
        if ((oldUsage != null) && (isFromFreePool(oldUsage))){
            carService.backToFreePool(oldUsage);
        }
        if (order.getUsageUpdates().isEmpty()) {
            PeriodicUsageUpdate firstUsageUpdate = new PeriodicUsageUpdate(new Date(), 1, 0, 0);
            periodicUsageUpdateRepository.save(firstUsageUpdate);
            order.getUsageUpdates().add(firstUsageUpdate);
        }
        carUsageRepository.save(order);
        mailService.sendMail(mail, getMessage("mail.confirmorder.subject"), getMessage("mail.confirmorder.text"));
    }

    public boolean isFromFreePool(CarUsage order) {
        return (!order.getUsageUpdates().isEmpty()) && (order.getUsageUpdates().get(order.getUsageUpdates().size() - 1).getNewTotalKm() > 0);
    }

    @Transactional
    public void disallowOrder(CarUsage order) {
        if (isFromFreePool(order)){
            carService.backToFreePool(order);
        }else {
            carUsageRepository.delete(order);
        }
        mailService.sendMail(order.getEmployee().getEmail(), getMessage("mail.disalloworder.subject"), getMessage("mail.disalloworder.text"));
    }

    private String getMessage(String key){
        return messageSource.getMessage(key, null, Locale.US);
    }

    public CarUsage findCurrentUsage(Long id) {
        return carUsageRepository.findCurrentUsage(id);
    }
}
