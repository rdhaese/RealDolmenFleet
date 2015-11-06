package com.realdolmen.fleet.persist;


import com.realdolmen.fleet.enums.CarType;
import com.realdolmen.fleet.enums.FuelType;
import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created on 5/11/2015.
 *
 * @author Robin D'Haese
 */
public class CarRepositoryTest extends AbstractRepositoryTest {

    //TODO Create tests like EmployeeRepositoryTest

    private List<Pack> extraPacks;
    private List<CarOption> extraOptions;
    private Pack basePack;

    @Before
    public void setUp(){
        basePack = new Pack("basePack", 500, createBaseOptionsList());
        initExtraPacks();
        initExtraOptions();
        Car car = new Car("Audi", "A1", 1, 95, 90, FuelType.DIESEL, CarType.NORMAL, 8, 2.5,80000,120000,15000,120,2000,2000, basePack, extraPacks, extraOptions);
        carRepository.save(car);
    }

    private List<CarOption> createBaseOptionsList() {
        List<CarOption> baseOptions = new ArrayList<>();
        baseOptions.add(new CarOption("des5", "name5"));
        baseOptions.add(new CarOption(null, "name6"));
        return baseOptions;
    }

    private void initExtraOptions() {
        extraOptions = new ArrayList<>();
        extraOptions.add(new CarOption("des1", "name1"));
        extraOptions.add(new CarOption("des2", "name2"));
    }

    private void initExtraPacks() {
        extraPacks = new ArrayList<>();
        extraPacks.add(new Pack("pack1", 200.50, new ArrayList<CarOption>()));
        extraPacks.add(new Pack("pack2", 250, createCarOptionList()));
    }

    private List<CarOption> createCarOptionList() {
        List<CarOption> carOptions = new ArrayList<>();
        carOptions.add(new CarOption("des3", "name3"));
        carOptions.add(new CarOption(null, "name4"));
        return carOptions;
    }

}
