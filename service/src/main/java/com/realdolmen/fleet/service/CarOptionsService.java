package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.persist.CarOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 29/10/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class CarOptionsService {

    private static final int PAGE_SIZE = 5;

    @Autowired
    private CarOptionsRepository carOptionsRepository;

    public List<CarOption> findAll(){
        return carOptionsRepository.findAll();
    }

    public void saveCarOption(CarOption carOption) {carOptionsRepository.save(carOption);}


    public Page<CarOption> getCarOptions(Integer pageNumber) {
        PageRequest request =
                new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "name");
        return carOptionsRepository.findAll(request);
    }
}
