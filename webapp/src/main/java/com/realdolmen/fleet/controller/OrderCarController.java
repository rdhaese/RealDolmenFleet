package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.listener.LoginListener;
import com.realdolmen.fleet.model.*;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.service.ConfirmOrderService;
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
import java.util.List;

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
    @Autowired
    private ConfirmOrderService confirmOrderService;


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
        constructOrderedCar(color, selectedPacks, selectedOptions, oCar, car);
        CarUsage carUsage = new CarUsage();
        Employee employee = employeeService.getLoggedInUser();
        constructCarUsage(oCar, car, carUsage, employee);
        removePossibleOpenOrders(employee);
        orderedCarService.placeOrder(carUsage);
        loginListener.updateLoggedInUserCanOrderNewCar();
        return "employees/order-confirmed";
    }

    private void constructOrderedCar(@RequestParam("color") String color, @RequestParam(value = "selectedPacks", required = false) Long[] selectedPacks, @RequestParam(value = "selectedOptions", required = false) Long[] selectedOptions, OrderedCar oCar, Car car) {
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
    }

    private void constructCarUsage(OrderedCar oCar, Car car, CarUsage carUsage, Employee employee) {
        carUsage.setEmployee(employee);
        carUsage.setOrderedCar(oCar);
        Date now = new Date();
        carUsage.setOrderDate(new Date());
        carUsage.setStartDate(Utils.addDaysToDate(now, car.getDeliveryTime() * DAYS_IN_MONTH));
        carUsage.setInitialEndDate(Utils.addDaysToDate(now, new Double(DAYS_IN_YEAR * CHANGE_REQUIRED_AFTER_YEARS)));
    }

    private void removePossibleOpenOrders(Employee employee) {
        List<CarUsage> previousOpenOrders = carService.findOpenOrdersFor(employee);
        for (CarUsage openOrder: previousOpenOrders){
            if (confirmOrderService.isFromFreePool(openOrder)){
                carService.backToFreePool(openOrder);
            } else {
                carService.removeOpenOrder(openOrder);
            }
        }
    }

    @RequestMapping(value="/employees/order-free-pool-car", method = RequestMethod.POST)
    public String confirmOrder(@RequestParam("carUsageId") long carUsageId){
        CarUsage carUsage = carService.findCarUsageById(carUsageId);
        Employee employee = employeeService.getLoggedInUser();
        removePossibleOpenOrders(employee);
        carUsage.setEmployee(employee);
        orderedCarService.placeOrder(carUsage);
        loginListener.updateLoggedInUserCanOrderNewCar();
        return "employees/order-confirmed";
    }


}
