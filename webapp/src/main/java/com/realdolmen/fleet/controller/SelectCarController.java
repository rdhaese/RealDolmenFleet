package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.dto.FilterCarsDTO;
import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.service.EmployeeService;
import com.realdolmen.fleet.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.userdetails.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 29/10/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class SelectCarController {

    private static final int DIMENSIONAL_LIST_BOUNDARY = 3;
    @Autowired
    private CarService carService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value="/employees/select-car", method = RequestMethod.GET)
    public String cars( Model model){
        if (!employeeService.loggedInUserCanOrderNewCar()){
            return "index";
        }
        List<Car> cars = carService.findAll(employeeService.functionalLevelForLoggedInUser());
        model.addAttribute("dimensionalCarList", Utils.toTwoDimensionalList(cars, DIMENSIONAL_LIST_BOUNDARY));
        model.addAttribute(new FilterCarsDTO());
        return "employees/select-car";
    }

    @RequestMapping(value="/employees/select-car", method = RequestMethod.POST)
    public String filterCars(@ModelAttribute FilterCarsDTO filterCarsDTO, Model model){
        if (!employeeService.loggedInUserCanOrderNewCar()){
            return "index";
        }
        List<Car> cars = carService.findAll(employeeService.functionalLevelForLoggedInUser());
        cars = filterOnBrand(filterCarsDTO, cars);
        cars = filterOnModel(filterCarsDTO, cars);
        cars = filterOnLevel(filterCarsDTO, cars);
        cars = filterOnInFreePool(filterCarsDTO, cars);
        model.addAttribute("dimensionalCarList", Utils.toTwoDimensionalList(cars, DIMENSIONAL_LIST_BOUNDARY));
        return "employees/select-car";
    }

    private List<Car> filterOnInFreePool(FilterCarsDTO filterCarsDTO, List<Car> cars) {
        switch (filterCarsDTO.getInFreePool()){
            case "yes":
                cars = carService.filterInFreePool(cars);
                break;
            case "no":
                cars = carService.filterNotInFreePool(cars);
                break;
        }
        return cars;
    }

    private List<Car> filterOnLevel(FilterCarsDTO filterCarsDTO, List<Car> cars) {
        switch (filterCarsDTO.getLevel()){
            case "downgrade":
                cars = carService.filterDowngrade(cars, employeeService.functionalLevelForLoggedInUser());
                break;
            case "myLevel":
                cars = carService.filterSpecificLevel(cars, employeeService.functionalLevelForLoggedInUser());
                break;
            case "upgrade":
                cars = carService.filterUpgrade(cars, employeeService.functionalLevelForLoggedInUser());
                break;
        }
        return cars;
    }

    private List<Car> filterOnModel(@ModelAttribute FilterCarsDTO filterCarsDTO, List<Car> cars) {
        String carModel = filterCarsDTO.getModel();
        if (carModel != null && !carModel.isEmpty()){
            cars = carService.filterOnModel(cars, carModel);
        }
        return cars;
    }

    private List<Car> filterOnBrand(@ModelAttribute FilterCarsDTO filterCarsDTO, List<Car> cars) {
        String brand = filterCarsDTO.getBrand();
        if (brand != null && !brand.isEmpty()){
            cars = carService.filterOnBrand(cars, brand);
        }
        return cars;
    }
}
