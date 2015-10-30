package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.persist.EmployeeRepository;
import com.realdolmen.fleet.service.CarOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by JVDAX31 on 28/10/2015.
 */

@Controller
public class CarOptionsController {

    @Autowired
    private CarOptionsService carOptionsService;

    @RequestMapping(value="/caroptions", method = RequestMethod.GET)
    public List<CarOption> carOptions(){
       return carOptionsService.findAll();
    }

    @RequestMapping(value="/createcaroption", method = RequestMethod.GET)
    public CarOption showNewCarOption(){
        System.out.println("caroption initialized");
        return new CarOption();
    }

    @RequestMapping(value="/createcaroption", method = RequestMethod.POST)
    public String processCarOption(@Valid CarOption carOption, Errors errors) {
        System.out.println("caroption ready to save");
        if( errors.hasErrors()){
            return "createcaroption";
        }
        System.out.println("caroption ready to save");
        carOptionsService.save(carOption);
        System.out.println("caroption saved");
        return "redirect:/caroptions";
    }
}
