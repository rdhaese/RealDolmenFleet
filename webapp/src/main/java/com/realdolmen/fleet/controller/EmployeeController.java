package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.persist.EmployeeRepository;
import com.realdolmen.fleet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created on 27/10/2015.
 *
 * @author Robin D'Haese
 */
@Controller

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private Employee employee;

    @RequestMapping(value="/fleet/rdemployee", method = RequestMethod.GET)
    public List<Employee> employees(){
        return employeeService.findAll();
    }


    @RequestMapping(value = "/fleet/editemployee/{id}", method = RequestMethod.GET)
    public String editEmployee(@RequestParam Long id, Model model) {
       employee =  employeeService.findUserById(id);
        model.addAttribute("employee", employee);
        return "editemployee";
    }

    @RequestMapping(value = "/fleet/createemployee", method = RequestMethod.GET)
    public String editEmployee(Model model) {
        Employee e = new Employee();
       // e.setInServiceDate(new Date());
        model.addAttribute("employee", e);
        return "fleet/createemployee";
    }

    @RequestMapping(value = "/fleet/createemployee", method = RequestMethod.POST)
    public String processCar(@Valid Employee employee, Errors errors, Model m) {
        if( errors.hasErrors()){
            return "fleet/createemployee";
        }
        employeeService.save(employee);

        return "redirect:/fleet/rdemployee";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

      ///     binder.registerCustomEditor(Date.class, new CustomDateEditor(
        //            new SimpleDateFormat("dd/MM/yyyy"), false));

    }





}
