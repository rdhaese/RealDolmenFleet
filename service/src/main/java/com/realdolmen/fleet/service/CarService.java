package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.*;
import com.realdolmen.fleet.persist.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private CarOptionsRepository carOptionsRepository;

    @Autowired
    private PeriodicUsageUpdateRepository periodicUsageUpdateRepository;

    @Autowired
    private HistoryRecordRepository historyRecordRepository;

    public List<Car> findAll() {
        return carRepository.findByIsdeletedNot(true);
    }

    public List<Car> findAll(int functionalLevel) {
        return carRepository.findFor(functionalLevel);
    }

    public List<Car> filterOnBrand(List<Car> cars, String brand) {
        List<Car> filteredList = new ArrayList<>();
        cars.forEach(car -> {
                    if (car.getBrand().toLowerCase().contains(brand.toLowerCase())) {
                        filteredList.add(car);
                    }
                }
        );
        return filteredList;
    }

    public List<Car> filterOnModel(List<Car> cars, String model) {
        List<Car> filteredList = new ArrayList<>();
        cars.forEach(car -> {
                    if (car.getModel().toLowerCase().contains(model.toLowerCase())) {
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
        if(!carUsage.getUsageUpdates().isEmpty()) {
            HistoryRecord historyRecord = new HistoryRecord();
            historyRecord.setEmployee(carUsage.getEmployee());
            PeriodicUsageUpdate lastUpdate = carUsage.getUsageUpdates().get(0);
            historyRecord.setDrivenUntil(lastUpdate.getUpdateDate());
            historyRecord.setLastKm(lastUpdate.getNewTotalKm());
            historyRecordRepository.save(historyRecord);
            carUsage.getHistoryRecords().add(historyRecord);
        }
        carUsage.setEmployee(null);
        carUsage.setLicensePlate(null);
        carUsageRepository.save(carUsage);
    }

    public void saveNewCar(Car car){
        List<CarOption> chozenoptions = car.getExtraOptions();
        List<CarOption> attachedOptions = new ArrayList<>();
        for(CarOption caropt :chozenoptions){
            attachedOptions.add(carOptionsRepository.getOne(caropt.getId()));
        }
        car.setExtraOptions(attachedOptions);
        saveCar(car);
    }


    public void saveCar(Car car){
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

    public void deleteCarSoft(Long id){
        Car c = findById(id);
        c.setIsdeleted(true);
        saveCar(c);
    }

    public String addPeriodUsageUpdateToCar(PeriodicUsageUpdate p, String licenseplate){
        CarUsage carUsage = carUsageRepository.findCurrentUsageWithLicencePlate(licenseplate);
        if(carUsage == null)
            return "This is not a valid licenseplate of RealDolmen employee, please check";
        if( p.getTotalFuelPrice() < 0 || p.getTotalFuelledForPeriod() < 0)
            return "Refuel is not correct, please check";
        periodicUsageUpdateRepository.save(p);
        System.out.println("update saved");
        carUsage.addUsageUpdate(p);
        save(carUsage);
        return "PeriodUsageUpdate stored successfully!";
    }
}
