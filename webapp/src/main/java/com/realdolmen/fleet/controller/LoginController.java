package com.realdolmen.fleet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 28/10/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class LoginController {

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String showLogin(){
        return "login";
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String doLogin(){
        return"j_spring_security_check";
      }

}
