package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.listener.LoginListener;
import com.realdolmen.fleet.model.Employee;
import com.realdolmen.fleet.service.MailService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created on 6/11/2015.
 *
 * @author Robin D'Haese
 */
@ContextConfiguration(classes = AllowOrderController.class)
public class AllowOrderControllerTest extends AbstractControllerTest {

   @Autowired
   private LoginListener loginListener;

    @Autowired
    private MailService mailService;

    @Test
    public void getAllowOrderHandledCorrectly() throws Exception {
        when(employeeService.getUsersThatNeedOrderPermission()).thenReturn(new ArrayList<Employee>());
        mockMvc.perform(get("/fleet/allow-order")).andExpect(status().isOk()).andExpect(view().name("fleet/allow-order")).andExpect(model().attributeExists("usersThatNeedPermission"));
    }

    @Test
    public void postAllowOrderWhenUserNotFoundHandledCorrectly() throws Exception {
        when(employeeService.findUserOnEmail(any())).thenReturn(null);
        MockHttpServletRequestBuilder post = post("/fleet/allow-order")
                .param("email", "youwontfindme@lost.com");
        mockMvc.perform(post).andExpect(status().isOk()).andExpect(view().name("fleet/allow-order")).andExpect(model().attributeExists("emailNotFound")).andExpect(model().attributeExists("usersThatNeedPermission"));
    }

    @Test
    public void postAllowOrderHandledCorrectly() throws Exception {
        Employee emp = new Employee();
        when(employeeService.findUserOnEmail(any())).thenReturn(emp);
        MockHttpServletRequestBuilder post = post("/fleet/allow-order")
                .param("email", "youwontfindme@lost.com");
        mockMvc.perform(post).andExpect(redirectedUrl("/fleet/allow-order"));
        verify(employeeService, times(1)).save(emp);
        verify(loginListener, times(1)).updateLoggedInUserCanOrderNewCar();
        verify(mailService, times(1)).sendMail(any(),any(),any());
    }
}
