package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.persist.PackRepository;
import com.realdolmen.fleet.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JVDAX31 on 28/10/2015.
 */
@Controller
@Scope("session")
public class PackController {
    @Autowired
    private PackService packService;

    @Autowired
    private CarOptionsRepository carOptionsRepository;

    private List<CarOption> carOptions = new ArrayList<>();
    private Pack p = new Pack();
    List<CarOption> allCarOptions = new ArrayList<>();


    public void populateModel(Model model){
        model.addAttribute("allCarOptions", allCarOptions);
        model.addAttribute("pack", p);
        model.addAttribute("carOption", new CarOption());
    }

    @RequestMapping(value="/packs", method = RequestMethod.GET)
    public List<Pack> packs(){
       return packService.findAll();
    }

    @RequestMapping(value="/createpack", method = RequestMethod.GET)
    public String showNewPack(Model model) {
        allCarOptions = carOptionsRepository.findAll();
        p = new Pack();
        carOptions = new ArrayList<>();
        populateModel(model);
        return "createpack";
    }

    @RequestMapping(value="/createpack", method = RequestMethod.POST)
    public String processPack(@Valid Pack pack, Errors errors, Model model) {
        System.out.println("in process create pack: " + pack.getId());
        if( errors.hasErrors()){
            pack.setCarOptions(carOptions);
            model.addAttribute("allCarOptions", carOptionsRepository.findAll());
            model.addAttribute("pack", pack);
            model.addAttribute("carOption", new CarOption());
            return "createpack";
        }

        pack.setCarOptions(carOptions);
        packService.savePackWithExistingCarOptions(pack);
        return "redirect:/packs";
    }



    @RequestMapping(value = "/addoptiontopack/{id}", method = RequestMethod.GET)
    public
    String addOption(@RequestParam Long id, Model model) {
        System.out.println("add option in create");
        CarOption selectedOption = packService.getCarOption(id);
        carOptions.add(selectedOption);
        p.addCarOption(selectedOption);
        populateModel(model);
        return "createpack";
    }

    @RequestMapping(value = "/removeoption/{id}", method = RequestMethod.GET)
       public //@ResponseBody
    String removeOption(@RequestParam int id, Model model) {
        carOptions.remove(id);
        p.removeCarOption(id);
        populateModel(model);
        // Prepare the response string
        return "createpack";
    }


    @RequestMapping(value="/createnewpackoption", method = RequestMethod.POST)
    public String processCarOption(@Valid CarOption carOption, Errors errors, Model model) {
        System.out.println("caroption ready to save");
        if( errors.hasErrors()){
            System.out.println("errors found");
            p.setCarOptions(carOptions);
            populateModel(model);
            return "createpack";
        }
        System.out.println("caroption ready to save");
        carOptionsRepository.save(carOption);
        carOptions.add(carOption);
        p.addCarOption(carOption);
        populateModel(model);
        return "createPack";
    }









}
