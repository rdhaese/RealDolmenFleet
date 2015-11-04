package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.dto.ConfirmedOrderDTO;
import com.realdolmen.fleet.dto.OrderToConfirmDTO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        model.addAttribute("ordersToConfirm", mapToDTO(orderService.getOrdersToConfirm()));
        return "fleet/confirm-order";
    }

    private List<OrderToConfirmDTO> mapToDTO(List<CarUsage> ordersToConfirm) {
        List<OrderToConfirmDTO> dtos = new ArrayList<>();
        for (CarUsage order : ordersToConfirm){
            OrderToConfirmDTO dto = new OrderToConfirmDTO();
            dto.setOrderId(order.getId());
            dto.setEmployeeName(order.getEmployee().getName());
            dto.setOrderDate(dateFormat.format(order.getOrderDate()));
            dtos.add(dto);
        }
        return dtos;
    }

    @RequestMapping(value="/fleet/confirm-order-detail", method = RequestMethod.GET)
    public String confirmOrder(@RequestParam("id") long id, Model model){
        CarUsage order = orderService.getOrder(id);
        model.addAttribute("order", order);
        ConfirmedOrderDTO confirmedOrderDTO = mapToDTO(order);
        model.addAttribute(confirmedOrderDTO);
        if (orderService.isFromFreePool(order)){
            return "fleet/confirm-order-from-free-pool-detail";
        }
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

    @RequestMapping(value="/fleet/confirm-order-from-free-pool-detail", method = RequestMethod.POST)
    public String confirmOrderFromFreePoolDetail(@ModelAttribute ConfirmedOrderDTO confirmedOrderDTO, Model model) {
        CarUsage order = orderService.getOrder(confirmedOrderDTO.getOrderId());
        if (confirmedOrderDTO.getLicensePlate() == null || confirmedOrderDTO.getLicensePlate().isEmpty()){
            model.addAttribute("licensePlateError", true);
            model.addAttribute(confirmedOrderDTO);
            model.addAttribute("order", order);
            return "fleet/confirm-order-from-free-pool-detail";
        }
        order.setLicensePlate(confirmedOrderDTO.getLicensePlate());
        orderService.confirmOrder(order);
        return "redirect:/fleet/confirm-order";
    }

    @RequestMapping(value="/fleet/confirm-order-detail", method = RequestMethod.POST)
    public String confirmOrderDetail(@ModelAttribute ConfirmedOrderDTO confirmedOrderDTO, Model model) {
        CarUsage order = orderService.getOrder(confirmedOrderDTO.getOrderId());
        if (hasError(model, confirmedOrderDTO, order)){
            model.addAttribute("order", order);
            model.addAttribute(confirmedOrderDTO);
            return "fleet/confirm-order-detail";
        }
        orderService.confirmOrder(order);
        return "redirect:/fleet/confirm-order";
    }

    private boolean hasError(Model model, ConfirmedOrderDTO confirmedOrderDTO, CarUsage order) {
        boolean error = false;
        if (confirmedOrderDTO.getLicensePlate() == null || confirmedOrderDTO.getLicensePlate().isEmpty()){
            model.addAttribute("licensePlateError", true);
            error = true;
        } else {
            order.setLicensePlate(confirmedOrderDTO.getLicensePlate());
        }
        if (confirmedOrderDTO.getPrice() == null || confirmedOrderDTO.getPrice() < 1){
            model.addAttribute("priceError", true);
            error = true;
        } else {
            order.getOrderedCar().setPrice(confirmedOrderDTO.getPrice());
        }
        try{
            order.setStartDate(dateFormat.parse(confirmedOrderDTO.getStartDate()));
        } catch (ParseException ex){
            model.addAttribute("startDateError", true);
            error = true;
        }
        try{
            order.setInitialEndDate(dateFormat.parse(confirmedOrderDTO.getStartDate()));
        } catch (ParseException ex){
            model.addAttribute("initialEndDateError", true);
            error = true;
        }
        return error;
    }
}
