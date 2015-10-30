package com.realdolmen.fleet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class FavIconController {
    @RequestMapping("favicon.ico")
    String favicon() {
        return "forward:/resources/images/favicon.ico";
    }
}
