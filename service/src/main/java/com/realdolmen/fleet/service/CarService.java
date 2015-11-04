package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.persist.CarRepository;
import com.realdolmen.fleet.persist.CarUsageRepository;
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

    @Autowired
    private CarUsageRepository carUsageRepository;

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



    public Car findById(long id) {
        return carRepository.findOne(id);
    }


    public List<CarUsage> findAllFromFreePool() {
        return carUsageRepository.findAllFromFreePool();
    }

    public CarUsage findCarUsageById(Long id){
        return carUsageRepository.findOne(id);
    }

    public void save(CarUsage carUsage) {
        carUsageRepository.save(carUsage);
    }

    public List<CarUsage> findOpenOrdersFor(Employee employee) {
        return carUsageRepository.findOpenOrdersFor(employee.getId());
    }

    public void removeOpenOrder(CarUsage previousOpenOrder) {
        removeCarUsage(previousOpenOrder);
    }

    public void backToFreePool(CarUsage carUsage) {
        carUsage.setLicensePlate(null);
        carUsage.setEmployee(null);
        carUsageRepository.save(carUsage);
    }


    public void saveCar(Car car){
        System.out.println("Save car with id: " + car.getId());
        carRepository.save(car);
    }


    public List<CarUsage> findAllOurCarsInUse() {
        return carUsageRepository.findAllWithLicensePlateSet();
    }

    public void removeFromFreePool(CarUsage carUsage) {
        removeCarUsage(carUsage);
    }

    public void removeCarUsage(CarUsage carUsage){
        carUsageRepository.delete(carUsage);
    }

    public CarUsage findCarUsageForEmployee(String email) {
        List<CarUsage> carUsagesForEmployee = carUsageRepository.findByEmployee(email);
        if (carUsagesForEmployee.isEmpty()){
            return null;
        }
        return carUsagesForEmployee.get(carUsagesForEmployee.size() - 1);
    }
}
