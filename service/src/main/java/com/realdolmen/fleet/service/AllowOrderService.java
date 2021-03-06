package com.realdolmen.fleet.service;

import com.realdolmen.fleet.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import javax.transaction.Transactional;
import java.util.Locale;

/**
 * Created on 9/11/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AllowOrderService {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MailService mailService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;

    @Transactional
    public void allow(Employee employee) {
        employee.setPermissionToOrderNewCar(true);
        employeeService.save(employee);
        mailService.sendMail(employee.getEmail(), getMessage("mail.allowordering.subject"), getMessage("mail.allowordering.text"));
    }

    private String getMessage(String key){
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}
