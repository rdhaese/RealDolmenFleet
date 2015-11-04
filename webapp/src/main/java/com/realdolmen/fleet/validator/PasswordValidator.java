package com.realdolmen.fleet.validator;

import com.realdolmen.fleet.dto.EditPasswordDTO;
import com.realdolmen.fleet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by JVDAX31 on 4/11/2015.
 */
public class PasswordValidator implements Validator {



    @Override
    public boolean supports(Class clazz) {
        return EditPasswordDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
       EditPasswordDTO editPasswordDTO = (EditPasswordDTO) target;

        if (! editPasswordDTO.getNewPassword().equals(editPasswordDTO.getConfirmedPassword())) {
            errors.rejectValue("confirmedPassword", "password.confirmdifferent");

        }

        if(! editPasswordDTO.getEmployeeService().checkPassword(editPasswordDTO.getExistingPassword())){
            errors.rejectValue("existingPassword", "password.wrongExistingPassword");
        }

        if(editPasswordDTO.getNewPassword().length() <6){
            errors.rejectValue("newPassword", "password.minSize6");
        }



    }

}

