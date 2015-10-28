package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.persist.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by JVDAX31 on 28/10/2015.
 */

@Controller
public class CarOptionsController {


    @Autowired
    private CarOptionsRepository carOptionsRepository;

    @RequestMapping(value="/caroptions", method = RequestMethod.GET)
    public List<CarOption> carOptions(){
        List<CarOption> carOptions = carOptionsRepository.findAll();
        System.out.println(carOptions);
        return carOptions;
    }
}
