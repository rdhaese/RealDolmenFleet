package com.realdolmen.fleet.listener;

import com.realdolmen.fleet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;


/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event)
    {
        httpSession.setAttribute("loggedInUserCanOrderNewCar", employeeService.loggedInUserCanOrderNewCar());
    }
}