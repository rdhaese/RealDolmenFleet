package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import com.realdolmen.fleet.persist.CarOptionsRepository;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JVDAX31 on 31/10/2015.
 */
@Controller
@Scope("session")
public class EditPackController {


    @Autowired
    private PackService packService;

    @Autowired
    private CarOptionsRepository carOptionsRepository;
    List<CarOption> allCarOptions = new ArrayList<>();
    private Pack p = new Pack();





    public void populateModel(Model model){
        model.addAttribute("allCarOptions", allCarOptions);
        model.addAttribute("pack", p);
        model.addAttribute("carOption", new CarOption());
    }

    //Edit pack functions

    @RequestMapping(value = "/editpack/{id}", method = RequestMethod.GET)
    public String editPack(@RequestParam Long id, Model model) {
        allCarOptions = carOptionsRepository.findAll();
        p = packService.findPackById(id);
        populateModel(model);
        return "editpack";
    }

    @RequestMapping(value="/editpack", method = RequestMethod.POST)
    public String processEditPack(@Valid Pack pack, Errors errors, Model model) {
        pack.setId(p.getId());
        pack.setCarOptions(p.getCarOptions());
        if( errors.hasErrors()){

            p = pack;
            populateModel(model);
            return "editpack";
        }
        packService.editPackWithExistingCarOptions(pack);
        return "redirect:/packs";
    }


    @RequestMapping(value = "/addoptioninedit", method = RequestMethod.GET, params="id")
    public
    String addOptionInEdit(@RequestParam Long id, Model model) {
        CarOption selectedOption = packService.getCarOption(id);
        p.addCarOption(selectedOption);
        populateModel(model);
        return "editpack";
    }

    @RequestMapping(value = "/removeoptionedit/{id}", method = RequestMethod.GET)
    public
    String removeOptionInEdit(@RequestParam int id, Model model) {
        p.removeCarOption(id);
        populateModel(model);
        return "editpack";
    }

    @RequestMapping(value="/createeditpackoption", method = RequestMethod.POST)
    public String processCarOptionEdit(@Valid CarOption carOption, Errors errors, Model model) {
        if( errors.hasErrors()){
            model.addAttribute("allCarOptions", allCarOptions);
            model.addAttribute("pack", p);
            model.addAttribute("carOption", carOption);
            return "editpack";
        }
        carOptionsRepository.save(carOption);
        p.addCarOption(carOption);
        populateModel(model);
        return "editpack";
    }

}
