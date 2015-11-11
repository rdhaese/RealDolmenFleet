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
 * Created on 27/10/2015.
 *
 * @author Robin D'Haese
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
    ////(Model model, @ModelAttribute("user") User user, BindingResult result

    @RequestMapping(value="/employees/editpassword", method = RequestMethod.POST)
    public String saveEditPass(@ModelAttribute("editPassword") EditPasswordDTO editPasswordDTO, BindingResult errors, Model model){
        PasswordValidator passwordValidator = new PasswordValidator();
        editPasswordDTO.setEmployeeService(employeeService);
        passwordValidator.validate(editPasswordDTO, errors);

        if( errors.hasErrors()){
            return "employees/editpassword";
        }


         //   employeeService.saveEditedEmployee(employeeService.getLoggedInUser(), editPasswordDTO.getExistingPassword(), editPasswordDTO.getNewPassword());
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
//        e.setInServiceDate(new Date());
        model.addAttribute("employee", e);
        return "fleet/createemployee";
    }

    @RequestMapping(value = "/fleet/createemployee", method = RequestMethod.POST)
    public String createEmpl(@Valid Employee employee, Errors errors, Model m) {
        System.out.println("in process employee");

        if( errors.hasErrors()){
            System.out.println("employee has errors");
            return "fleet/createemployee";
        }
        String originalPassword = employee.getPassword();
        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
        employee.setPassword(e.encode(employee.getPassword()));
    //    System.out.println(e.encode("123"));
        employeeService.saveNewEmployee(employee, originalPassword);

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
