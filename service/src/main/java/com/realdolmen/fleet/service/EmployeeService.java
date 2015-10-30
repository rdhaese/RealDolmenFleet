package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.persist.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 29/10/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CanOrderNewCarService canOrderNewCarService;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }


    public int getFunctionalLevelFor(String email) {
        return employeeRepository.findByEmail(email).getFunctionalLevel();
    }

    public int functionalLevelForLoggedInUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getFunctionalLevelFor(user.getUsername());
    }

    public boolean loggedInUserCanOrderNewCar() {
        return canOrderNewCarService.canOrderNewCar();
    }
}
