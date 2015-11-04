package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created on 3/11/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class ManageCarsController {
    @Autowired
    private CarService carService;

    @RequestMapping(value="/fleet/cars", method = RequestMethod.GET)
    public String cars(Model model){
        model.addAttribute("ourCarsInUse", carService.findAllOurCarsInUse());
        model.addAttribute("ourCarsInFreePool", carService.findAllFromFreePool());
        return "fleet/cars";
    }

    @RequestMapping(value="/fleet/cars/detail/{carUsageId}", method = RequestMethod.GET)
    public String carDetail(@PathVariable("carUsageId") Long carUsageId, Model model){
        model.addAttribute("carDetails", carService.findCarUsageById(carUsageId));
        return "fleet/car-detail";
    }


}
