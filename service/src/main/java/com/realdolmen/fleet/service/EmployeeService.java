package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.persist.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Employee findUserOnEmail(String email){
       return employeeRepository.findByEmail(email);
    }

    public Employee findUserById(Long id){
        return employeeRepository.findOne(id);
    }

    public Employee getLoggedInUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findUserOnEmail(user.getUsername());
    }

    public int functionalLevelForLoggedInUser(){
        return getLoggedInUser().getFunctionalLevel();
    }

    public boolean loggedInUserCanOrderNewCar() {
        return canOrderNewCarService.loggedInEmployeeCanOrderNewCar();
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> getUsersThatNeedOrderPermission() {
        List<Employee> employeesWithoutPermission = employeeRepository.findEmployeesWithoutPermissionTOrderNewCar();
        List<Employee> employeesThatNeedPermission = new ArrayList<>();
        employeesWithoutPermission.forEach(employee ->{
                    if (canOrderNewCarService.needsPermission(employee)){
                        employeesThatNeedPermission.add(employee);
                    }
                }
        );
        return employeesThatNeedPermission;
    }
}
