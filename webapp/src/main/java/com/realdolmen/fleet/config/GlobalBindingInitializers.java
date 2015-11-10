package com.realdolmen.fleet.config;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created on 10/11/2015.
 *
 * @author Robin D'Haese
 */
//@ControllerAdvice
public class GlobalBindingInitializers {


    //TODO REMOVE OR FIX
   /* @InitBinder
    public void initDoubleBinder(WebDataBinder binder) throws Exception {
        System.out.println("BEEEEEEN HEREEEEEE");
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, df, true));
    }*/
}
