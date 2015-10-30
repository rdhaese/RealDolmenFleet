package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.persist.PackRepository;
import com.realdolmen.fleet.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PackController {
    @Autowired
    private PackService packService;

    @Autowired
    private CarOptionsRepository carOptionsRepository;

    private List<CarOption> carOptions = new ArrayList<>();
    private Pack p = new Pack();

    @RequestMapping(value="/packs", method = RequestMethod.GET)
    public List<Pack> packs(){
       return packService.findAll();
    }

    @RequestMapping(value="/createpack", method = RequestMethod.GET)
    public String showNewPack(Model model) {
        List<CarOption> allCarOptions = carOptionsRepository.findAll();
        p = new Pack();
        carOptions = new ArrayList<>();
        model.addAttribute("allCarOptions", allCarOptions);
        model.addAttribute("pack", p);
        model.addAttribute("selectedOptions", carOptions);
        return "createpack";
    }

    @RequestMapping(value="/createpack", method = RequestMethod.POST)
    @Transactional
    public String processPack(@Valid Pack pack, Errors errors) {

        if( errors.hasErrors()){
            return "createcaroption";
        }

        List<CarOption> chozenoptions = pack.getCarOptions();
        List<CarOption> attachedOptions = new ArrayList<>();
        for(CarOption car :chozenoptions){
           attachedOptions.add(carOptionsRepository.getOne(car.getId()));
        }
        pack.setCarOptions(carOptions);
        packService.savePackWithExistingCarOptions(pack);
        return "redirect:/packs";
    }



    @RequestMapping(value = "/addoptiontopack", method = RequestMethod.GET)
    public
    String processAJAXRequest(@RequestParam("id") String id, Model model) {
        System.out.println("in process");
        CarOption selectedOption = packService.getCarOption(Long.valueOf(id));
        carOptions.add(selectedOption);
        Pack p = new Pack();
        p.setCarOptions(carOptions);
        model.addAttribute("allCarOptions", carOptionsRepository.findAll());
        model.addAttribute("selectedOptions", carOptions);
        model.addAttribute("pack", p);
        // Process the request
        // Prepare the response string
        return "createpack";
    }

    @RequestMapping(value = "/addoptiontopack/{id}", method = RequestMethod.GET)
    public
    String addOption(@RequestParam Long id, Model model) {
        System.out.println("in process");
        CarOption selectedOption = packService.getCarOption(id);
        carOptions.add(selectedOption);

        p.setCarOptions(carOptions);

        model.addAttribute("allCarOptions", carOptionsRepository.findAll());
        model.addAttribute("selectedOptions", carOptions);
        model.addAttribute("pack", p);        // Process the request
        // Prepare the response string
        return "createpack";
    }
}
