package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.persist.PackRepository;
import com.realdolmen.fleet.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value="/packs", method = RequestMethod.GET)
    public List<Pack> packs(){
       return packService.findAll();
    }

    @RequestMapping(value="/createpack", method = RequestMethod.GET)
    public String showNewPack(Model model) {
        List<CarOption> carOptions = carOptionsRepository.findAll();

        model.addAttribute("allCarOptions", carOptions);
        model.addAttribute("pack", new Pack());
        return "createpack";
    }

    @RequestMapping(value="/createpack", method = RequestMethod.POST)
    @Transactional
    public String processPack(@Valid Pack pack, Errors errors) {

        if( errors.hasErrors()){
            return "createcaroption";
        }
        /*
        List<CarOption> chozenoptions = pack.getCarOptions();
        List<CarOption> attachedOptions = new ArrayList<>();
        for(CarOption car :chozenoptions){
           attachedOptions.add(carOptionsRepository.getOne(car.getId()));
        }
        pack.setCarOptions(attachedOptions);*/
        packService.savePackWithExistingCarOptions(pack);
        return "redirect:/packs";
    }
}
