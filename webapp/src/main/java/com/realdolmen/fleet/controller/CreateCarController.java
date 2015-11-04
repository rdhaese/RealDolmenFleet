package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import com.realdolmen.fleet.service.CarOptionsService;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by JVDAX31 on 2/11/2015.
 */
@Controller
@Scope("session")
public class CreateCarController {

    @Autowired
    private CarService carService;

    @Autowired
    private PackService packService;

    @Autowired
    private CarOptionsService carOptionsService;

    private Car createdCar;

    @RequestMapping(value="/fleet/createcar", method = RequestMethod.GET)
    public Car createNewCar(){
    //    System.out.println("caroption initialized");
        return new Car();
    }

/*
    @RequestMapping(value="/optionselection", method = RequestMethod.GET)
    public String optionselection(Model m){
        //    System.out.println("caroption initialized");
        createdCar = carService.findById(1);
        m.addAttribute("packList", packService.findAll());
        m.addAttribute("car", createdCar);
        return "optionselection";
    }

    */

    @RequestMapping(value="/fleet/createcar", method = RequestMethod.POST)
    public String processCar(@Valid Car car, Errors errors, Model m) {
        System.out.println("car ready to save");
        if( errors.hasErrors()){
            return "/fleet/createcar";
        }
        System.out.println("caroption ready to save");
       // carService.saveCar(car);
        System.out.println("caroption saved");
       // return "redirect:/caroverview";
        createdCar = car;
        m.addAttribute("packList", packService.findAll());
        m.addAttribute("car", createdCar);
        m.addAttribute("optionList", carOptionsService.findAll());
        return "/fleet/optionselection";
    }

    @RequestMapping(value="/fleet/caroverview", method = RequestMethod.GET)
    public List<Car> getAllCars(){
        //    System.out.println("caroption initialized");
        return carService.findAll();
    }

    @RequestMapping(value = "/fleet/setbasicpack/{id}", method = RequestMethod.GET)
    public
    String setBasicPack(@RequestParam Long id, Model model) {

        Pack p = packService.findPackById(id);
        System.out.println(createdCar);
        createdCar.setBasePack(p);
        model.addAttribute("car", createdCar);
        model.addAttribute("packList", packService.findAll());
        model.addAttribute("optionList", carOptionsService.findAll());
        return "/fleet/optionselection";
    }

    @RequestMapping(value = "/fleet/addExtraPack/{id}", method = RequestMethod.GET)
    public
    String addExtraPack(@RequestParam Long id, Model model) {

        Pack p = packService.findPackById(id);
        createdCar.addExtraPack(p);
        model.addAttribute("car", createdCar);
        model.addAttribute("packList", packService.findAll());
        model.addAttribute("optionList", carOptionsService.findAll());
        return "/fleet/optionselection";
    }

    @RequestMapping(value = "/fleet/addExtraOption/{id}", method = RequestMethod.GET)
    public
    String addExtraOption(@RequestParam Long id, Model model) {

        CarOption carOption = carOptionsService.getCarOptionByID(id);
        createdCar.addExtraOption(carOption);
        System.out.println(carOption.getId());
        model.addAttribute("car", createdCar);
        model.addAttribute("packList", packService.findAll());
        model.addAttribute("optionList", carOptionsService.findAll());
        return "/fleet/optionselection";
    }


    @RequestMapping(value = "/fleet/removeextrapack/{id}", method = RequestMethod.GET)
    public //@ResponseBody
    String removeExtraPack(@RequestParam int id, Model model) {
        createdCar.getExtraPacks().remove(id);
        model.addAttribute("car", createdCar);
        model.addAttribute("packList", packService.findAll());
        model.addAttribute("optionList", carOptionsService.findAll());
        // Prepare the response string
        return "/fleet/optionselection";
    }

    @RequestMapping(value = "/fleet/removeextraoption/{id}", method = RequestMethod.GET)
    public //@ResponseBody
    String removeExtraOption(@RequestParam int id, Model model) {
        createdCar.getExtraOptions().remove(id);
        model.addAttribute("car", createdCar);
        model.addAttribute("packList", packService.findAll());
        model.addAttribute("optionList", carOptionsService.findAll());
        // Prepare the response string
        return "/fleet/optionselection";
    }

    @RequestMapping(value = "/fleet/savecar", method = RequestMethod.GET)
    public
    String saveCar(@RequestParam Long id) {

       carService.saveCar(createdCar);
        return "redirect:/fleet/caroverview";
    }
}
