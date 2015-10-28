package com.realdolmen.fleet.util;

import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.model.EmployeeType;

/**
 * Created on 28/10/2015.
 *
 * @author Robin D'Haese
 */
public class EntityFactory {

    public static Employee createEmployee(String name, String email, String password, EmployeeType role, int functionalLevel){
        Employee emp = new Employee();
        emp.setName(name);
        emp.setEmail(email);
        emp.setPassword(password);
        emp.setRole(role);
        emp.setFunctionalLevel(functionalLevel);
        return emp;
    }
}
