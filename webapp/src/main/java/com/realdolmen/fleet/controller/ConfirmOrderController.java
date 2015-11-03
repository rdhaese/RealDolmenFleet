package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.dto.ConfirmedOrderDTO;
import com.realdolmen.fleet.model.CarUsage;
import com.realdolmen.fleet.service.ConfirmOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2/11/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class ConfirmOrderController {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    private ConfirmOrderService orderService;

    @RequestMapping(value="/fleet/confirm-order", method = RequestMethod.GET)
    public String confirmOrder(Model model){
        model.addAttribute("ordersToConfirm", orderService.getOrdersToConfirm());
        return "fleet/confirm-order";
    }

    @RequestMapping(value="/fleet/confirm-order-detail", method = RequestMethod.GET)
    public String confirmOrder(@RequestParam("id") long id, Model model){
        CarUsage order = orderService.getOrder(id);
        model.addAttribute("order", orderService.getOrder(id));
        ConfirmedOrderDTO confirmedOrderDTO = mapToDTO(order);
        model.addAttribute(confirmedOrderDTO);
        return "fleet/confirm-order-detail";
    }

    private ConfirmedOrderDTO mapToDTO(CarUsage order) {
        ConfirmedOrderDTO confirmedOrderDTO = new ConfirmedOrderDTO();
        confirmedOrderDTO.setOrderId(order.getId());
        confirmedOrderDTO.setInitialEndDate(dateFormat.format(order.getInitialEndDate()));
        confirmedOrderDTO.setStartDate(dateFormat.format(order.getStartDate()));
        confirmedOrderDTO.setOrderedDate(dateFormat.format(order.getOrderDate()));
        return confirmedOrderDTO;
    }

    @RequestMapping(value="/fleet/confirm-order-detail", method = RequestMethod.POST)
    public String confirmOrderDetail(@ModelAttribute ConfirmedOrderDTO confirmedOrderDTO) throws ParseException {
        CarUsage order = orderService.getOrder(confirmedOrderDTO.getOrderId());
        order.setLicensePlate(confirmedOrderDTO.getLicensePlate());
        order.getOrderedCar().setPrice(confirmedOrderDTO.getPrice());
        order.setStartDate(dateFormat.parse(confirmedOrderDTO.getStartDate()));
        order.setInitialEndDate(dateFormat.parse(confirmedOrderDTO.getStartDate()));

        orderService.confirmOrder(order);
        return "redirect:/fleet/confirm-order";
    }
}
