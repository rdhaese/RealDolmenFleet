package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.persist.EmployeeRepository;
import com.realdolmen.fleet.service.CarOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value="/fleet/caroptions", method = RequestMethod.GET)
    public List<CarOption> carOptions(){
       return carOptionsService.findAll();
    }

    @RequestMapping(value="/fleet/createcaroption", method = RequestMethod.GET)
    public CarOption showNewCarOption(){
        return new CarOption();
    }

    @RequestMapping(value="/fleet/createcaroption", method = RequestMethod.POST)
    public String processCarOption(@Valid CarOption carOption, Errors errors) {
        if( errors.hasErrors()){
            return "/fleet/createcaroption";
        }
        carOptionsService.saveCarOption(carOption);
        return "redirect:/fleet/caroptions";
    }
}
