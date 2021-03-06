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
import org.springframework.web.bind.annotation.*;

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

        return new Car();
    }



    @RequestMapping(value="/fleet/createcar", method = RequestMethod.POST)
    public String processCar(@Valid Car car, Errors errors, Model m) {
        if( errors.hasErrors()){
            return "/fleet/createcar";
        }
        createdCar = car;
        createdCar.setExtraOptions(packService.findGeneralRDOptions());
        m.addAttribute("packList", packService.findAll());
        m.addAttribute("car", createdCar);
        m.addAttribute("optionList", packService.findMissingGeneralRDOptions(createdCar.getExtraOptions()));
        return "/fleet/optionselection";
    }

    @RequestMapping(value="/fleet/caroverview", method = RequestMethod.GET)
    public List<Car> getAllCars(){
       return carService.findAll();
    }



    @RequestMapping(value = "/fleet/setajaxbasicpack", method = RequestMethod.POST)
    public @ResponseBody
    String setAjaxBasicPack(@RequestParam("id") Long id, Model model) {
        Pack p = packService.findPackById(id);
        createdCar.setBasePack(p);
        return "ok";
    }

    @RequestMapping(value = "/fleet/addajaxextrapack", method = RequestMethod.POST)
    public @ResponseBody
    String addAjaxExtraPack(@RequestParam("id") Long id, Model model) {

        Pack p = packService.findPackById(id);
        createdCar.addExtraPack(p);

        return "ok";
    }


    @RequestMapping(value = "/fleet/addAjaxExtraOption", method = RequestMethod.POST)
    public @ResponseBody
    String addAjaxExtraOption(@RequestParam("id") Long id, Model model) {

        CarOption carOption = carOptionsService.getCarOptionByID(id);
        createdCar.addExtraOption(carOption);

        return carOption.getName();
    }




    @RequestMapping(value = "/fleet/removeajaxextrapack", method = RequestMethod.POST)
    public @ResponseBody
    String removeAjaxExtraPack(@RequestParam("id") int id, Model model) {
        createdCar.getExtraPacks().remove(id);
        return "ok";
    }


    @RequestMapping(value = "/fleet/removeajaxextraoption", method = RequestMethod.POST)
    public @ResponseBody
    List<CarOption> removeAjaxExtraOption(@RequestParam int id, Model model) {
        createdCar.getExtraOptions().remove(id);
        return packService.findMissingGeneralRDOptions(createdCar.getExtraOptions());
    }

    @RequestMapping(value = "/fleet/savecar", method = RequestMethod.GET)
    public
    String saveCar(@RequestParam Long id) {
       carService.saveNewCar(createdCar);
        return "redirect:/fleet/caroverview";
    }


    @RequestMapping(value = "/fleet/deletecar", method = RequestMethod.GET)
    public String deleteCarFromCatalog(@RequestParam Long id) {

        carService.deleteCarSoft(id);
        return "redirect:/fleet/caroverview";
    }
}
