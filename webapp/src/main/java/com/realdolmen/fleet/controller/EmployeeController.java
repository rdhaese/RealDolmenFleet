package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.persist.EmployeeRepository;
import com.realdolmen.fleet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        employee.setPassword("******");
        model.addAttribute("employee", employee);
        return "fleet/editemployee";
    }


    @RequestMapping(value = "/fleet/editemployee", method = RequestMethod.POST)
    public String editUpdatedEmployee(@Valid Employee employee, Errors errors, Model model) {
        System.out.println("in process edit employee");
        System.out.println(employee.getId());
        if( errors.hasErrors()){
            System.out.println("employee has errors");
            return "fleet/editemployee";
        }



        employeeService.saveEditedEmployee(employee.getId(), employee);

        return "redirect:/fleet/rdemployee";
    }

    @RequestMapping(value = "/fleet/createemployee", method = RequestMethod.GET)
    public String crEmployee(Model model) {
        Employee e = new Employee();
        e.setInServiceDate(new Date());
        model.addAttribute("employee", e);
        return "fleet/createemployee";
    }

    @RequestMapping(value = "/fleet/createemployee", method = RequestMethod.POST)
    public String processCar(@Valid Employee employee, Errors errors, Model m) {
        System.out.println("in process employee");

        if( errors.hasErrors()){
            System.out.println("employee has errors");
            return "fleet/createemployee";
        }

        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
        employee.setPassword(e.encode(employee.getPassword()));
    //    System.out.println(e.encode("123"));
        employeeService.save(employee);

        return "redirect:/fleet/rdemployee";
    }

    /*
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

    */






}
