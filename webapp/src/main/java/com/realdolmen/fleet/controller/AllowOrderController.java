package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.listener.LoginListener;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.service.EmployeeService;
import com.realdolmen.fleet.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created on 2/11/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class AllowOrderController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private LoginListener loginListener;
    @Autowired
    private MailService mailService;

    @RequestMapping(value="/fleet/allow-order", method = RequestMethod.GET)
    public String allowOrder(Model model){
        addUsersThatNeedPermissionsToModel(model);
        return "fleet/allow-order";
    }



    @RequestMapping(value="/fleet/allow-order", method = RequestMethod.POST)
    public String allowOrder(@RequestParam("email") String email, Model model){
        Employee employee = employeeService.findUserOnEmail(email);
        if (employee == null){
            model.addAttribute("emailNotFound", true);
            addUsersThatNeedPermissionsToModel(model);
            return "/fleet/allow-order";
        }
        employee.setPermissionToOrderNewCar(true);
        employeeService.save(employee);
        loginListener.updateLoggedInUserCanOrderNewCar();
        mailService.sendMail(employee.getEmail(), "Ordering New Car Available", "From this point, it is possible to order a new car on the fleet portal.");
        return "redirect:/fleet/allow-order";
    }

    private void addUsersThatNeedPermissionsToModel(Model model) {
        model.addAttribute("usersThatNeedPermission", employeeService.getUsersThatNeedOrderPermission());
    }

}
