package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 4/11/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class PersonalOverviewController {

    @Autowired
    private CarService carService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value={"", "/", "/index", "/employees", "/fleet", "/employees/overview"}, method = RequestMethod.GET)
    public String personalOverview(Model model){
        Employee loggedInUser = employeeService.getLoggedInUser();
        model.addAttribute("employee", loggedInUser);
        CarUsage carUsage = carService.findCarUsageForEmployee(employeeService.getLoggedInUser().getEmail());
        if (carUsage != null) {
            model.addAttribute("carUsage", carUsage);
        }
        return "employees/overview";
    }



}
