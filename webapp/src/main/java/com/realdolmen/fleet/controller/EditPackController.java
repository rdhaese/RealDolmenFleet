package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.service.CarOptionsService;
import com.realdolmen.fleet.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private CarOptionsService carOptionsService;
    List<CarOption> allCarOptions = new ArrayList<>();
    private Pack p = new Pack();


    public void populateModel(Model model){
        model.addAttribute("allCarOptions", allCarOptions);
        model.addAttribute("pack", p);
        model.addAttribute("carOption", new CarOption());
    }


    @RequestMapping(value = "/fleet/editpack/{id}", method = RequestMethod.GET)
    public String editPack(@RequestParam Long id, Model model) {
        allCarOptions = carOptionsService.findAll();
        p = packService.findPackById(id);
        populateModel(model);
        return "/fleet/editpack";
    }

    @RequestMapping(value="/fleet/editpack", method = RequestMethod.POST)
    public String processEditPack(@Valid Pack pack, Errors errors, Model model) {
        pack.setId(p.getId());
        pack.setCarOptions(p.getCarOptions());
        if( errors.hasErrors()){
            p = pack;
            populateModel(model);
            return "/fleet/editpack";
        }
        packService.editPackWithExistingCarOptions(pack);
        return "redirect:/fleet/packs";
    }


    @RequestMapping(value = "/fleet/addajaxoptioninedit", method = RequestMethod.POST)
    public @ResponseBody
    String addAjaxOptionInEdit(@RequestParam Long id, Model model) {
        CarOption selectedOption = packService.getCarOption(id);
        p.addCarOption(selectedOption);

        return "ok";
    }


    @RequestMapping(value = "/fleet/addoptioninedit", method = RequestMethod.GET, params="id")
    public
    String addOptionInEdit(@RequestParam Long id, Model model) {
        CarOption selectedOption = packService.getCarOption(id);
        p.addCarOption(selectedOption);
        populateModel(model);
        return "/fleet/editpack";
    }

    @RequestMapping(value = "/fleet/removeoptionedit/{id}", method = RequestMethod.GET)
    public
    String removeOptionInEdit(@RequestParam int id, Model model) {
        p.removeCarOption(id);
        populateModel(model);
        return "/fleet/editpack";
    }
    @RequestMapping(value = "/fleet/removeajaxoptionedit", method = RequestMethod.POST)
    public @ResponseBody
    String removeAjaxOptionInEdit(@RequestParam("id") int id, Model model) {
        p.removeCarOption(id);
        populateModel(model);
        return "ok";
    }


    @RequestMapping(value="/fleet/createeditpackoption", method = RequestMethod.POST)
    public String processCarOptionEdit(@Valid CarOption carOption, Errors errors, Model model) {
        if( errors.hasErrors()){
            model.addAttribute("optionNameError", true);
            populateModel(model);

            return "/fleet/editpack";

        }

        carOptionsService.saveCarOption(carOption);
        p.addCarOption(carOption);
        populateModel(model);
        return "/fleet/editpack";
    }

}
