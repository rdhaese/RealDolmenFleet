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

    @RequestMapping(value="/fleet/packs", method = RequestMethod.GET)
    public List<Pack> packs(){
       return packService.findAll();
    }

    @RequestMapping(value="/fleet/createpack", method = RequestMethod.GET)
    public String showNewPack(Model model) {
        allCarOptions = carOptionsRepository.findAll();
        p = new Pack();
        carOptions = new ArrayList<>();
        populateModel(model);
        return "/fleet/createpack";
    }


    @RequestMapping(value = "/fleet/addoptiontopack", method = RequestMethod.GET)
    public
    String addOption(@RequestParam("id") Long id, Model model) {

        CarOption selectedOption = packService.getCarOption(id);
        carOptions.add(selectedOption);
        p.addCarOption(selectedOption);
        populateModel(model);
        return "fleet/createpack";
    }

    @RequestMapping(value = "/fleet/addajaxoptiontopack", method = RequestMethod.POST)
    public @ResponseBody
    String addAjaxOptionToPack(@ModelAttribute(value="id") Long id, Model model) {
        System.out.println("id received: " + id);
        CarOption selectedOption = packService.getCarOption(id);
        carOptions.add(selectedOption);
        p.addCarOption(selectedOption);
        return "id correct received";
    }

    /*
	@RequestMapping(value="/AddUser.htm",method=RequestMethod.POST)
	public @ResponseBody String addUser(@ModelAttribute(value="user") User user, BindingResult result ){
		String returnText;
		if(!result.hasErrors()){
			userList.add(user);
			returnText = "User has been added to the list. Total number of users are " + userList.size();
		}else{
			returnText = "Sorry, an error has occur. User has not been added to list.";
		}
		return returnText;
	}
*/

    @RequestMapping(value = "/fleet/removeoption", method = RequestMethod.GET)
       public //@ResponseBody
    String removeOption(@RequestParam("id") int id, Model model) {

        carOptions.remove(id);
        p.removeCarOption(id);
        populateModel(model);
        // Prepare the response string
        return "/fleet/createpack";
    }

    @RequestMapping(value = "/fleet/removeajaxoptiontopack", method = RequestMethod.POST)
    public @ResponseBody
    String removeAjaxOption(@RequestParam("id") int id, Model model) {

        carOptions.remove(id);
        p.removeCarOption(id);
       // populateModel(model);
        // Prepare the response string
        return "ok";
    }


    @RequestMapping(value="/fleet/createnewpackoption", method = RequestMethod.POST)
    public String processCarOption(@Valid CarOption carOption, Errors errors, Model model) {

        if( carOption.getName().equals("")){
            model.addAttribute("optionNameError", true);

           // p.setCarOptions(carOptions);
            //populateModel(model);
       //     model.addAttribute("allCarOptions", carOptionsRepository.findAll());
       //     model.addAttribute("pack", p);
       //     model.addAttribute("carOption", carOption);
            populateModel(model);
            return "/fleet/createpack";
        }

        carOptionsRepository.save(carOption);
        carOptions.add(carOption);
        p.addCarOption(carOption);
        populateModel(model);
        return "/fleet/createpack";
    }

    @RequestMapping(value="/fleet/createpack", method = RequestMethod.POST)
    public String processPack(@Valid Pack pack, Errors errors, Model model) {

        if( errors.hasErrors()){
            pack.setCarOptions(carOptions);
            model.addAttribute("allCarOptions", carOptionsRepository.findAll());
            model.addAttribute("pack", pack);
            model.addAttribute("carOption", new CarOption());
            return "/fleet/createpack";
        }

        pack.setCarOptions(carOptions);
        packService.savePackWithExistingCarOptions(pack);
        return "redirect:/fleet/packs";
    }










}
