package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 2/11/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class AllowOrderController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value="/fleet/allow-order", method = RequestMethod.GET)
    public String allowOrder(Model model){
        model.addAttribute("usersThatNeedPermission", employeeService.getUsersThatNeedOrderPermission());
        return "fleet/allow-order";
    }

    @RequestMapping(value="/fleet/allow-order", method = RequestMethod.POST)
    public String allowOrder(@RequestParam("email") String email){
        Employee employee = employeeService.findUserOnEmail(email);
        if (employee == null){
            //TODO ERROR MESSAGE?
            return "fleet/allow-order";
        }
        employee.setPermissionToOrderNewCar(true);
        employeeService.save(employee);
        return "redirect:/fleet/allow-order";
    }

}
