package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import com.realdolmen.fleet.persist.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
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

    @Autowired
    private CarOptionsRepository carOptionsRepository;

    @Autowired
    private CarOptionsService carOptionsService;

    public List<Pack> findAll(){
        return packRepository.findAll();
    }

    public void savePack(Pack pack){ packRepository.save(pack);}

    public void savePackWithExistingCarOptions(Pack pack){
        List<CarOption> chozenoptions = pack.getCarOptions();
        List<CarOption> attachedOptions = new ArrayList<>();
        pack.setCarOptions(attachedOptions);
        savePack(pack);
        for(CarOption car :chozenoptions){
            attachedOptions.add(carOptionsRepository.getOne(car.getId()));
        }
        pack.setCarOptions(attachedOptions);
        savePack(pack);
    }

    public CarOption getCarOption(Long id){
        return carOptionsService.getCarOptionByID(id);
    }
}
