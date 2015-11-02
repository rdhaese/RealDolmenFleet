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

    public void savePack(Pack pack){

        System.out.println("saving pack with id: " + pack.getId());
        packRepository.save(pack);}

    public void savePackWithExistingCarOptions(Pack pack){
        System.out.println("in save pack with existing car options");
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

    public void editPackWithExistingCarOptions(Pack pack){
        System.out.println("in save pack with existing car options");
        List<CarOption> chozenoptions = pack.getCarOptions();
        List<CarOption> attachedOptions = new ArrayList<>();
        pack.setCarOptions(attachedOptions);

        Pack lookedupPack = findPackById(pack.getId());
        lookedupPack.setPrice(pack.getPrice());
        lookedupPack.setName(pack.getName());
        savePack(lookedupPack);
        for(CarOption car :chozenoptions){
            attachedOptions.add(carOptionsRepository.getOne(car.getId()));
        }
        lookedupPack.setCarOptions(attachedOptions);
        savePack(lookedupPack);
    }

    public CarOption getCarOption(Long id){
        return carOptionsService.getCarOptionByID(id);
    }

    public Pack findPackById(Long id){
        return packRepository.findOne(id);
    }
}
