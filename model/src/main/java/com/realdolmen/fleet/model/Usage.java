package com.realdolmen.fleet.model;

import javax.persistence.*;
import java.util.Date;


/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
//@Entity
public class Usage extends BaseEntity {
    private String licensePlate;
    private Employee employee;
    private OrderedCar orderedCar;
    private Date orderDate;
    private Date startDate;
    private Date initialEndDate;
    private Date endDate;


}
