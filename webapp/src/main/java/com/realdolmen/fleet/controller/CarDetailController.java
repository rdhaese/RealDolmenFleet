package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class CarDetailController {

    @Autowired
    private CarService carService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value="/employees/selected-car-detail", method = RequestMethod.GET)
    public String carDetail(@RequestParam(value = "id", required = false) Long id, Model model){
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
        model.addAttribute("functionalLevelForLoggedInUser", employeeService.functionalLevelForLoggedInUser());
        return "employees/selected-car-detail";
    }


}
