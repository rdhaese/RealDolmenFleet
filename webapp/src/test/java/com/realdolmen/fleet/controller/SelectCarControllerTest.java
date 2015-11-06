package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.model.Car;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.model.OrderedCar;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created on 6/11/2015.
 *
 * @author Robin D'Haese
 */
@ContextConfiguration(classes = SelectCarController.class)
public class SelectCarControllerTest extends AbstractControllerTest {

    private static final int EMP_FUNC_LEVEL = 3;

    @Before
    public void setUp() {
        when(employeeService.functionalLevelForLoggedInUser()).thenReturn(EMP_FUNC_LEVEL);
        when(carService.findAll(EMP_FUNC_LEVEL)).thenReturn(dummyCarList());
        when(carService.findAllFromFreePool()).thenReturn(dummyFreePoolCarList());
    }

    private List<CarUsage> dummyFreePoolCarList() {
        List<CarUsage> freePoolCars = new ArrayList<>();
        freePoolCars.add(createCarUsage());
        return freePoolCars;
    }

    private CarUsage createCarUsage() {
        CarUsage carUsage = new CarUsage();
        OrderedCar oCar = new OrderedCar();
        oCar.setCar(createCar("Tesla", "Model S", 6));
        carUsage.setOrderedCar(oCar);
        return carUsage;
    }

    private List<Car> dummyCarList() {
        List<Car> cars = new ArrayList<>();
        cars.add(createCar("Audi", "A3", 3));
        cars.add(createCar("Fiat", "Punto", 2));
        cars.add(createCar("Audi", "A4", 4));
        cars.add(createCar("Passat", "Variant", 3));
        return cars;
    }

    private Car createCar(String model, String brand, int category) {
        Car car = new Car();
        car.setModel(model);
        car.setBrand(brand);
        car.setCategory(category);
        return car;
    }


    @Test
    public void getRequestWithoutFilterWhenLoggedInUserCantOrderANewCarHandledCorrectly() throws Exception {
        when(employeeService.loggedInUserCanOrderNewCar()).thenReturn(false);
        mockMvc.perform(get("/employees/select-car")).andExpect(status().isOk())
                .andExpect(view().name("employees/overview"));
    }

    @Test
    public void getRequestWithoutFilterWhenLoggedInUserCanOrderANewCarHandledCorrectly() throws Exception {
        when(employeeService.loggedInUserCanOrderNewCar()).thenReturn(true);
        mockMvc.perform(get("/employees/select-car")).andExpect(status().isOk())
                .andExpect(view().name("employees/select-car")).andExpect(model().attributeExists("dimensionalCarList")).andExpect(model().attributeExists("dimensionalFreePoolCarList")).andExpect(model().attributeExists("functionalLevelForLoggedInUser"));
    }

    @Test
    public void getRequestWithAllFiltersWhenLoggedInUserCantOrderANewCarHandledCorrectly() throws Exception {
        when(employeeService.loggedInUserCanOrderNewCar()).thenReturn(false);
        mockMvc.perform(get("/employees/select-car/filter?brand=audi&model=a3&level=myLevel")).andExpect(status().isOk())
                .andExpect(view().name("employees/overview"));
    }


    @Test
    public void getRequestWithAllFiltersWhenLoggedInUserCanOrderANewCarHandledCorrectly() throws Exception {
        when(employeeService.loggedInUserCanOrderNewCar()).thenReturn(true);
        mockMvc.perform(get("/employees/select-car/filter?brand=audi&model=a3&level=myLevel")).andExpect(status().isOk())
                .andExpect(view().name("employees/select-car")).andExpect(model().attributeExists("dimensionalCarList")).andExpect(model().attributeExists("dimensionalFreePoolCarList")).andExpect(model().attributeExists("functionalLevelForLoggedInUser"));
    }
}


