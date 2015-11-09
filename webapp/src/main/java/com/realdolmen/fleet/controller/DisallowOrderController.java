package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.listener.LoginListener;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.service.DisallowOrderService;
import com.realdolmen.fleet.service.EmployeeService;
import com.realdolmen.fleet.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 9/11/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class DisallowOrderController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DisallowOrderService disallowOrderService;
    @Autowired
    private LoginListener loginListener;

    @RequestMapping(value="/fleet/disallow-ordering", method = RequestMethod.GET)
    public String disallowOrder(Model model){
        model.addAttribute("usersWithPermission", employeeService.getUsersWithPermission());
        return "fleet/disallow-ordering";
    }



    @RequestMapping(value="/fleet/disallow-ordering", method = RequestMethod.POST)
    public String disallowOrder(@RequestParam("email") String email, Model model){
        Employee employee = employeeService.findUserOnEmail(email);
        disallowOrderService.disallow(employee);
        loginListener.updateLoggedInUserCanOrderNewCar();
        return "redirect:/fleet/disallow-ordering";
    }
}
