package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.dto.EditPasswordDTO;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.service.EmployeeService;
import com.realdolmen.fleet.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 31/10/2015.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CarService carService;

    private Employee employee;

    @RequestMapping(value="/fleet/rdemployee", method = RequestMethod.GET)
    public List<Employee> employees(){
        return employeeService.findAll();
    }


    @RequestMapping(value="/employees/editpassword", method = RequestMethod.GET)
    public String editPass(Model model){
        model.addAttribute("editPassword", new EditPasswordDTO());
        return "employees/editpassword";
    }

    @RequestMapping(value="/employees/editpassword", method = RequestMethod.POST)
    public String saveEditPass(@ModelAttribute("editPassword") EditPasswordDTO editPasswordDTO, BindingResult errors, Model model){
        PasswordValidator passwordValidator = new PasswordValidator();
        editPasswordDTO.setEmployeeService(employeeService);
        passwordValidator.validate(editPasswordDTO, errors);
        if( errors.hasErrors()){
            return "employees/editpassword";
        }

        employeeService.saveNewPassword(editPasswordDTO.getNewPassword());
        Employee loggedInUser = employeeService.getLoggedInUser();
        model.addAttribute("employee", loggedInUser);

        CarUsage carUsage = carService.findCarUsageForEmployee(employeeService.getLoggedInUser().getEmail());
        if (carUsage != null) {
            model.addAttribute("carUsage", carUsage);
        }

        model.addAttribute("passwordEdit", true);

        return "/employees/overview";
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

        if( errors.hasErrors()){
            return "fleet/editemployee";
        }

        employeeService.saveEditedEmployee(employee.getId(), employee);
        return "redirect:/fleet/rdemployee";
    }

    @RequestMapping(value = "/fleet/createemployee", method = RequestMethod.GET)
    public String crEmployee(Model model) {
        Employee e = new Employee();
        model.addAttribute("employee", e);
        return "fleet/createemployee";
    }

    @RequestMapping(value = "/fleet/createemployee", method = RequestMethod.POST)
    public String createEmpl(@Valid Employee employee, Errors errors, Model m) {
        if( errors.hasErrors()){

            return "fleet/createemployee";
        }
        String originalPassword = employee.getPassword();
        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
        employee.setPassword(e.encode(employee.getPassword()));

        employeeService.saveNewEmployee(employee, originalPassword);
        return "redirect:/fleet/rdemployee";
    }


}
