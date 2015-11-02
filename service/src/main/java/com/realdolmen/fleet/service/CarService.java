package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.persist.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 29/10/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findAll(int functionalLevel) {
        return carRepository.findFor(functionalLevel);
    }

    public List<Car> filterOnBrand(List<Car> cars, String brand) {
        List<Car> filteredList = new ArrayList<>();
        cars.forEach(car -> {
                    if (car.getBrand().toLowerCase().equals(brand.toLowerCase())) {
                        filteredList.add(car);
                    }
                }
        );
        return filteredList;
    }

    public List<Car> filterOnModel(List<Car> cars, String model) {
        List<Car> filteredList = new ArrayList<>();
        cars.forEach(car -> {
                    if (car.getModel().toLowerCase().equals(model.toLowerCase())) {
                        filteredList.add(car);
                    }
                }
        );
        return filteredList;
    }

    public List<Car> filterDowngrade(List<Car> cars, int userLevel){
        return filterSpecificLevel(cars, userLevel - 1);
    }

    public List<Car> filterUpgrade(List<Car> cars, int userLevel){
       return filterSpecificLevel(cars, userLevel + 1);
    }

    public List<Car> filterSpecificLevel(List<Car> cars, int userLevel){
        List<Car> filteredList = new ArrayList<>();
        cars.forEach(car -> {
                    if (car.getCategory() == userLevel) {
                        filteredList.add(car);
                    }
                }
        );
        return filteredList;
    }

    public List<Car> filterInFreePool(List<Car> cars){
        List<Car> filteredList = new ArrayList<>();
        cars.forEach(car -> {
                    if (car.getInFreePool() == true) {
                        filteredList.add(car);
                    }
                }
        );
        return filteredList;
    }

    public List<Car> filterNotInFreePool(List<Car> cars){
        List<Car> filteredList = new ArrayList<>();
        cars.forEach(car -> {
                    if (car.getInFreePool() == false) {
                        filteredList.add(car);
                    }
                }
        );
        return filteredList;
    }

    public Car findById(long id) {
        return carRepository.findOne(id);
    }


    public void saveCar(Car car){
        carRepository.save(car);
    }


}
