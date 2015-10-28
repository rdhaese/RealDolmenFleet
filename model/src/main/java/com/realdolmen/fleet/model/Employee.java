package com.realdolmen.fleet.model;

import com.realdolmen.fleet.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

/**
 * Created on 27/10/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class Employee extends BaseEntity {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    @Size(min=6)
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeType role;
    @DecimalMin(value = "0")
    @DecimalMax(value = "8")
    private int functionalLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeType getRole() {
        return role;
    }

    public void setRole(EmployeeType role) {
        this.role = role;
    }

    public int getFunctionalLevel() {
        return functionalLevel;
    }

    public void setFunctionalLevel(int functionalLevel) {
        this.functionalLevel = functionalLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (getFunctionalLevel() != employee.getFunctionalLevel()) return false;
        if (getName() != null ? !getName().equals(employee.getName()) : employee.getName() != null) return false;
        if (getEmail() != null ? !getEmail().equals(employee.getEmail()) : employee.getEmail() != null) return false;
        if (getPassword() != null ? !getPassword().equals(employee.getPassword()) : employee.getPassword() != null)
            return false;
        return getRole() == employee.getRole();

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + getFunctionalLevel();
        return result;
    }
}
