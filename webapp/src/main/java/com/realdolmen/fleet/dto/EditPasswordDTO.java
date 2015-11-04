package com.realdolmen.fleet.dto;

import com.realdolmen.fleet.service.EmployeeService;

/**
 * Created by JVDAX31 on 4/11/2015.
 */
public class EditPasswordDTO {
    private String existingPassword;
    private String newPassword;
    private String confirmedPassword;

    private String username;

    private EmployeeService employeeService;

    public EditPasswordDTO(String confirmedPassword, String newPassword, String existingPassword, String username) {
        this.confirmedPassword = confirmedPassword;
        this.newPassword = newPassword;
        this.existingPassword = existingPassword;
        this.username = username;
        this.username = username;
    }
 // sdfsdf
    public EditPasswordDTO(){

    }


    public String getExistingPassword() {
        return existingPassword;
    }

    public void setExistingPassword(String existingPassword) {
        this.existingPassword = existingPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
