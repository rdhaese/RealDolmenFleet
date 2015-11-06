package com.realdolmen.fleet.webservice;

import com.realdolmen.fleet.model.PeriodicUsageUpdate;
import com.realdolmen.fleet.service.CarOptionsService;
import com.realdolmen.fleet.service.CarService;
import com.realdolmen.fleet.service.PeriodicUsageService;
import com.realdolmen.fleet.web_service.GetStorePeriodicUsageRequest;
import com.realdolmen.fleet.web_service.GetStorePeriodicUsageResponse;
import com.realdolmen.fleet.web_service.PerUsage;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.Date;

/**
 * Created by JVDAX31 on 3/11/2015.
 */


@Endpoint
public class PeriodicUsageEndpoint {



    private static final String NAMESPACE_URI = "http://realdolmen.com/fleet/web-service";

    @Autowired
    private PeriodicUsageService periodicUsageService;

    @Autowired
    private CarService carService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStorePeriodicUsageRequest")
    @ResponsePayload
    public GetStorePeriodicUsageResponse getStorePeriodicUsage(@RequestPayload GetStorePeriodicUsageRequest request) {
        PerUsage receivedUsage = request.getPerUsage();
        Date d = receivedUsage.getUpdatedate().toGregorianCalendar().getTime();
        PeriodicUsageUpdate periodicUsageUpdate = new PeriodicUsageUpdate(d, receivedUsage.getTotalkm(), receivedUsage.getTotalprice(), receivedUsage.getTotalfuelinliter());
        String result =	carService.addPeriodUsageUpdateToCar(periodicUsageUpdate, receivedUsage.getNumberplate());
        GetStorePeriodicUsageResponse response = new GetStorePeriodicUsageResponse();
        response.setResponse(result);

        return response;
    }




}
