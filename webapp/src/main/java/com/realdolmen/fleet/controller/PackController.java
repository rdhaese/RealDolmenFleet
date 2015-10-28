package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.persist.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by JVDAX31 on 28/10/2015.
 */
@Controller
public class PackController {
    @Autowired
    private PackRepository packRepository;

    @RequestMapping(value="/packs", method = RequestMethod.GET)
    public List<Pack> packs(){
        List<Pack> packs = packRepository.findAll();
        System.out.println(packs);
        return packs;
    }
}
