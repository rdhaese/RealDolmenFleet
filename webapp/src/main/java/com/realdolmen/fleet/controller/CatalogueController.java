package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.dto.FilterCarsDTO;
import com.realdolmen.fleet.dto.FilterCatalogueDTO;
import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created on 12/11/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class CatalogueController {

    private static final int DIMENSIONAL_LIST_BOUNDARY = 4;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/employees/catalogue", method = RequestMethod.GET)
    public String catalogue(Model model) {
        setModelAttributes(model, carService.findAll(), carService.findAllFromFreePool(), new FilterCatalogueDTO());
        return "employees/catalogue";
    }

    @RequestMapping(value = "/employees/catalogue/filter", method = RequestMethod.GET)
    public String filterCatalogue(Model model, @ModelAttribute FilterCatalogueDTO filterCatalogueDTO) {
        List<Car> cars = carService.findAll();
        cars = filterOnBrand(filterCatalogueDTO, cars);
        cars = filterOnModel(filterCatalogueDTO, cars);
        cars = filterOnLevel(filterCatalogueDTO, cars);
        setModelAttributes(model, cars, carService.findAllFromFreePool(), filterCatalogueDTO);
        return "employees/catalogue";
    }

    @RequestMapping(value = "/employees/catalogue/detail", method = RequestMethod.GET)
    public String catalogueDetail(Model model, @RequestParam("id") Long carId) {
        if (carId == null){
            return "employees/overview";
        }
        Car car = carService.findById(carId);
        if (car == null){
            return "employees/overview";
        }
        model.addAttribute(car);
        return "employees/catalogue-detail";
    }

    @RequestMapping(value = "/employees/catalogue/detail-from-free-pool", method = RequestMethod.GET)
    public String catalogueDetailFromFreePool(Model model, @RequestParam("id") Long carId) {
        if (carId == null){
            return "employees/overview";
        }
        CarUsage carUsage = carService.findCarUsageById(carId);
        if (carUsage == null){
            return "employees/overview";
        }
        model.addAttribute(carUsage);
        return "employees/catalogue-detail-from-free-pool";
    }

    private List<Car> filterOnLevel(FilterCatalogueDTO filterCatalogueDTO, List<Car> cars) {
        Integer lvl = filterCatalogueDTO.getFunctionalLevel();
        if ((lvl != null) && (lvl > 0) && (lvl < 9)){
            cars = carService.filterSpecificLevel(cars, lvl);
        }
        return cars;
    }

    private List<Car> filterOnBrand(FilterCatalogueDTO filterCatalogueDTO, List<Car> cars) {
        String carBrand = filterCatalogueDTO.getBrand();
        if ((carBrand != null) && (!carBrand.isEmpty())) {
            cars = carService.filterOnBrand(cars, carBrand);
        }
        return cars;
    }

    private List<Car> filterOnModel(FilterCatalogueDTO filterCatalogueDTO, List<Car> cars) {
        String carModel = filterCatalogueDTO.getModel();
        if (carModel != null && !carModel.isEmpty()) {
            cars = carService.filterOnModel(cars, carModel);
        }
        return cars;
    }

    private void setModelAttributes(Model model, List<Car> catalogueCars, List<CarUsage> freePoolCars, FilterCatalogueDTO filterCatalogueDTO) {
        model.addAttribute("catalogueCars", Utils.toTwoDimensionalList(catalogueCars, DIMENSIONAL_LIST_BOUNDARY));
        model.addAttribute("freePoolCars", Utils.toTwoDimensionalList(freePoolCars, DIMENSIONAL_LIST_BOUNDARY));
        model.addAttribute("filter", filterCatalogueDTO);
    }
}
