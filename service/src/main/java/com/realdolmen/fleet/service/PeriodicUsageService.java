package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.PeriodicUsageUpdate;
import com.realdolmen.fleet.persist.PeriodicUsageUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JVDAX31 on 3/11/2015.
 */

@Service
public class PeriodicUsageService {

    @Autowired
    private PeriodicUsageUpdateRepository periodicUsageUpdateRepository;

    List<PeriodicUsageUpdate> findAll(){
       return periodicUsageUpdateRepository.findAll();
    }

    public void savePeriodicUpdate(PeriodicUsageUpdate periodicUsageUpdate){
        periodicUsageUpdateRepository.save(periodicUsageUpdate);
    }

    public void savePeriodicUpdateForUser(PeriodicUsageUpdate periodicUsageUpdate, String licenseplate){
        periodicUsageUpdateRepository.save(periodicUsageUpdate);
    }
}
