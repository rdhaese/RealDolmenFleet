package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.Pack;
import com.realdolmen.fleet.persist.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created on 29/10/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class PackService {

    @Autowired
    private PackRepository packRepository;

    public List<Pack> findAll(){
        return packRepository.findAll();
    }

    public void save(Pack pack){
        packRepository.save(pack);
    }
}
