package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 29/10/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class CarOptionsService {

    @Autowired
    private CarOptionsRepository carOptionsRepository;

    public List<CarOption> findAll(){
        return carOptionsRepository.findAll();
    }

}
