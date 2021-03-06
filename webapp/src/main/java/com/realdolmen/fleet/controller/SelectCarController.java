package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.dto.FilterCarsDTO;
import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.service.EmployeeService;
import com.realdolmen.fleet.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created on 29/10/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class SelectCarController {

    private static final int DIMENSIONAL_LIST_BOUNDARY = 4;
    @Autowired
    private CarService carService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees/select-car", method = RequestMethod.GET)
    public String cars(Model model) {
        if (!employeeService.loggedInUserCanOrderNewCar()) {
            return "employees/overview";
        }
        setModelAttributes(model, carService.findAll(employeeService.functionalLevelForLoggedInUser()) , carService.findAllFromFreePool(), new FilterCarsDTO());
        return "employees/select-car";
    }

    @RequestMapping(value = "/employees/select-car/filter", method = RequestMethod.GET)
    public String filterCars(@ModelAttribute FilterCarsDTO filterCarsDTO, Model model) {
        if (!employeeService.loggedInUserCanOrderNewCar()) {
            return "employees/overview";
        }
        List<Car> cars = carService.findAll(employeeService.functionalLevelForLoggedInUser());
        cars = filterOnBrand(filterCarsDTO, cars);
        cars = filterOnModel(filterCarsDTO, cars);
        cars = filterOnLevel(filterCarsDTO, cars);
        setModelAttributes(model, cars, carService.findAllFromFreePool(), filterCarsDTO);
        return "employees/select-car";
    }

    private void setModelAttributes(Model model, List<Car> cars, List<CarUsage> carsInFreePool, FilterCarsDTO filterCarsDTO) {
        model.addAttribute("dimensionalCarList", Utils.toTwoDimensionalList(cars, DIMENSIONAL_LIST_BOUNDARY));
        model.addAttribute("dimensionalFreePoolCarList", Utils.toTwoDimensionalList(carsInFreePool, DIMENSIONAL_LIST_BOUNDARY));
        model.addAttribute("functionalLevelForLoggedInUser", employeeService.functionalLevelForLoggedInUser());
        model.addAttribute(filterCarsDTO);
    }

    private List<Car> filterOnLevel(FilterCarsDTO filterCarsDTO, List<Car> cars) {
        switch (filterCarsDTO.getLevel()) {
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

    private List<Car> filterOnModel(FilterCarsDTO filterCarsDTO, List<Car> cars) {
        String carModel = filterCarsDTO.getModel();
        if (carModel != null && !carModel.isEmpty()) {
            cars = carService.filterOnModel(cars, carModel);
        }
        return cars;
    }

    private List<Car> filterOnBrand(FilterCarsDTO filterCarsDTO, List<Car> cars) {
        String brand = filterCarsDTO.getBrand();
        if (brand != null && !brand.isEmpty()) {
            cars = carService.filterOnBrand(cars, brand);
        }
        return cars;
    }
}
