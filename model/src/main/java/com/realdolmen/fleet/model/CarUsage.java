package com.realdolmen.fleet.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class CarUsage extends BaseEntity implements Comparable<CarUsage> {

    @Size(min=1, max=255)
    private String licensePlate;
    @OneToOne
    private Employee employee;
    @OneToOne
    @NotNull
    private OrderedCar orderedCar;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date orderDate;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date initialEndDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany
    @OrderBy(value = "new_total_km") //TODO test this
    private List<PeriodicUsageUpdate> usageUpdates;

    public CarUsage(String licensePlate, Employee employee, OrderedCar orderedCar, Date orderDate, Date startDate, Date initialEndDate, Date endDate) {
        super();
        this.licensePlate = licensePlate;
        this.employee = employee;
        this.orderedCar = orderedCar;
        this.orderDate = orderDate;
        this.startDate = startDate;
        this.initialEndDate = initialEndDate;
        this.endDate = endDate;
    }

    public CarUsage(String licensePlate, Employee employee, OrderedCar orderedCar, Date orderDate, Date startDate, Date initialEndDate) {
       this(licensePlate,employee,orderedCar, orderDate, startDate,initialEndDate, null);
    }

    public CarUsage() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarUsage carUsage = (CarUsage) o;

        if (licensePlate != null ? !licensePlate.equals(carUsage.licensePlate) : carUsage.licensePlate != null) return false;
        if (employee != null ? !employee.equals(carUsage.employee) : carUsage.employee != null) return false;
        if (orderedCar != null ? !orderedCar.equals(carUsage.orderedCar) : carUsage.orderedCar != null) return false;
        if (orderDate != null ? !orderDate.equals(carUsage.orderDate) : carUsage.orderDate != null) return false;
        if (startDate != null ? !startDate.equals(carUsage.startDate) : carUsage.startDate != null) return false;
        if (initialEndDate != null ? !initialEndDate.equals(carUsage.initialEndDate) : carUsage.initialEndDate != null)
            return false;
        return !(endDate != null ? !endDate.equals(carUsage.endDate) : carUsage.endDate != null);

    }

    @Override
    public int hashCode() {
        int result = licensePlate != null ? licensePlate.hashCode() : 0;
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (orderedCar != null ? orderedCar.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (initialEndDate != null ? initialEndDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public OrderedCar getOrderedCar() {
        return orderedCar;
    }

    public void setOrderedCar(OrderedCar orderedCar) {
        this.orderedCar = orderedCar;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getInitialEndDate() {
        return initialEndDate;
    }

    public void setInitialEndDate(Date initialEndDate) {
        this.initialEndDate = initialEndDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<PeriodicUsageUpdate> getUsageUpdates() {
        return usageUpdates;
    }

    public void setUsageUpdates(List<PeriodicUsageUpdate> usageUpdates) {
        this.usageUpdates = usageUpdates;
    }

    @Override
    public int compareTo(CarUsage o) {
        return this.initialEndDate.compareTo(o.getInitialEndDate());
    }
}
