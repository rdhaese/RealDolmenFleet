package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.listener.LoginListener;
import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.OrderedCar;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.service.EmployeeService;
import com.realdolmen.fleet.service.OrderedCarService;
import com.realdolmen.fleet.util.Utils;
import com.sun.prism.Texture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created on 2/11/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class OrderCarController {

    private static final int DAYS_IN_YEAR = 365;
    private static final int DAYS_IN_MONTH = 31;
    private static final int CHANGE_REQUIRED_AFTER_YEARS = 4;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CarService carService;
    @Autowired
    private OrderedCarService orderedCarService;
    @Autowired
    private LoginListener loginListener;

    @RequestMapping(value="/employees/order-car", method = RequestMethod.GET)
    public String orderCar(@RequestParam(value = "id", required = false) Long id, Model model){
        if (id == null){
            return "employees/overview";
        }
        if (!employeeService.loggedInUserCanOrderNewCar()){
            return "employees/overview";
        }
        Car car = carService.findById(id);
        if (car == null){
            return "employees/overview";
        }
        model.addAttribute(car);
        return "employees/order-car";
    }

    @RequestMapping(value="/employees/order-car", method = RequestMethod.POST)
    public String confirmOrder(@RequestParam("carId") long carId, @RequestParam("color") String color, @RequestParam(value = "selectedPacks", required = false) Long[] selectedPacks, @RequestParam(value = "selectedOptions", required = false) Long[] selectedOptions){
        OrderedCar oCar = new OrderedCar();
        Car car = carService.findById(carId);
        oCar.setCar(car);
        oCar.setColor(color);
        if (selectedPacks != null){
        for (Long packId : selectedPacks){
            oCar.getPacks().add(orderedCarService.getPackOnId(packId));
        }}
        if (selectedOptions != null){
        for (Long optionId : selectedOptions){
            oCar.getOptions().add(orderedCarService.getOptionOnId(optionId));
        }}
        CarUsage carUsage = new CarUsage();
        carUsage.setEmployee(employeeService.getLoggedInUser());
        carUsage.setOrderedCar(oCar);
        Date now = new Date();
        carUsage.setOrderDate(new Date());
        carUsage.setStartDate(Utils.addDaysToDate(now, car.getDeliveryTime() * DAYS_IN_MONTH));
        carUsage.setInitialEndDate(Utils.addDaysToDate(now, new Double(DAYS_IN_YEAR * CHANGE_REQUIRED_AFTER_YEARS)));
        orderedCarService.placeOrder(carUsage);
        loginListener.updateLoggedInUserCanOrderNewCar();
        return "employees/order-confirmed";
    }
}
