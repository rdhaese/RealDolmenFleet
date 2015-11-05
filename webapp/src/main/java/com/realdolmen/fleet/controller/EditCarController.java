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

/**
 * Created by JVDAX31 on 3/11/2015.
 */
@Controller
@Scope("session")
public class EditCarController {

    @Autowired
    private CarService carService;

    @Autowired
    private PackService packService;

    @Autowired
    private CarOptionsService carOptionsService;

    private Car original;
    private Car updatedCar;


    public void populateModel(Model model){
        model.addAttribute("car", updatedCar);
        model.addAttribute("packList", packService.findAll());
        model.addAttribute("optionList", carOptionsService.findAll());
    }


    @RequestMapping(value = "/fleet/editcar/{id}", method = RequestMethod.GET)
    public String editCar(@RequestParam Long id, Model model) {
        updatedCar = carService.findById(id);
        original = updatedCar;
        System.out.println(updatedCar.getId());
        System.out.println(updatedCar.getBasePack());
        System.out.println(updatedCar.getExtraPacks().size());
        populateModel(model);
        return "/fleet/editcar";
    }

    @RequestMapping(value="/fleet/editcar", method = RequestMethod.POST)
    public String processEditCar(@Valid Car car, Errors errors, Model m) {
        System.out.println(car.getId());
        System.out.println(car.getBasePack());
        System.out.println(car.getExtraPacks().size());
        updatedCar = car;
        populateModel(m);
        if( errors.hasErrors()){
            return "/fleet/editcar";
        }
        updatedCar.setBasePack(original.getBasePack());
        updatedCar.setExtraOptions(original.getExtraOptions());
        updatedCar.setExtraPacks(original.getExtraPacks());
        return "/fleet/editoptionselection";
    }


    @RequestMapping(value = "/fleet/seteditbasicpack/{id}", method = RequestMethod.GET)
    public
    String setEditBasicPack(@RequestParam Long id, Model model) {

        Pack p = packService.findPackById(id);

       updatedCar.setBasePack(p);
       populateModel(model);
        return "/fleet/editoptionselection";
    }

    @RequestMapping(value = "/fleet/addEditExtraPack/{id}", method = RequestMethod.GET)
    public
    String addEditExtraPack(@RequestParam Long id, Model model) {

        Pack p = packService.findPackById(id);
        updatedCar.addExtraPack(p);
        populateModel(model);
        return "/fleet/editoptionselection";
    }

    @RequestMapping(value = "/fleet/addEditExtraOption/{id}", method = RequestMethod.GET)
    public
    String addEditExtraOption(@RequestParam Long id, Model model) {

        CarOption carOption = carOptionsService.getCarOptionByID(id);
        updatedCar.addExtraOption(carOption);
        populateModel(model);
        return "/fleet/editoptionselection";
    }


    @RequestMapping(value = "/fleet/removeeditextrapack/{id}", method = RequestMethod.GET)
    public //@ResponseBody
    String removeEditExtraPack(@RequestParam int id, Model model) {
        updatedCar.getExtraPacks().remove(id);
        populateModel(model);

        return "/fleet/editoptionselection";
    }

    @RequestMapping(value = "/fleet/removeeditextraoption/{id}", method = RequestMethod.GET)
    public //@ResponseBody
    String removeEditExtraOption(@RequestParam int id, Model model) {
        updatedCar.getExtraOptions().remove(id);
        populateModel(model);

        return "/fleet/editoptionselection";
    }

    @RequestMapping(value = "/fleet/updatecar", method = RequestMethod.GET)
    public
    String updateCar(@RequestParam Long id) {
       // updatedCar.setId(original.getId());
        original.CopyCar(updatedCar);
        carService.saveNewCar(original);
        return "redirect:/fleet/caroverview";
    }



}
