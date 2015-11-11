package com.realdolmen.fleet.model;

import com.realdolmen.fleet.comparator.HistoryRecordComparator;
import com.realdolmen.fleet.comparator.PeriodicUsageUpdateComparator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;


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
    @JoinColumn(name="ordered_car_id")
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
    @OneToMany(fetch = FetchType.EAGER)
    @OrderBy("newTotalKm DESC")
    @JoinTable(name="car_usage_usage_updates",
            joinColumns=@JoinColumn(name="car_usage_id"),
            inverseJoinColumns=@JoinColumn(name="usage_updates_id"))
    private List<PeriodicUsageUpdate> usageUpdates = new ArrayList<>();

    @OneToMany
    @OrderBy("lastKm DESC")
    private List<HistoryRecord> historyRecords = new ArrayList<>();

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

        if (getLicensePlate() != null ? !getLicensePlate().equals(carUsage.getLicensePlate()) : carUsage.getLicensePlate() != null)
            return false;
        if (getEmployee() != null ? !getEmployee().equals(carUsage.getEmployee()) : carUsage.getEmployee() != null)
            return false;
        if (getOrderedCar() != null ? !getOrderedCar().equals(carUsage.getOrderedCar()) : carUsage.getOrderedCar() != null)
            return false;
        if (getOrderDate() != null ? !getOrderDate().equals(carUsage.getOrderDate()) : carUsage.getOrderDate() != null)
            return false;
        if (getStartDate() != null ? !getStartDate().equals(carUsage.getStartDate()) : carUsage.getStartDate() != null)
            return false;
        if (getInitialEndDate() != null ? !getInitialEndDate().equals(carUsage.getInitialEndDate()) : carUsage.getInitialEndDate() != null)
            return false;
        if (getEndDate() != null ? !getEndDate().equals(carUsage.getEndDate()) : carUsage.getEndDate() != null)
            return false;
        if (getUsageUpdates() != null ? !getUsageUpdates().equals(carUsage.getUsageUpdates()) : carUsage.getUsageUpdates() != null)
            return false;
        return !(getHistoryRecords() != null ? !getHistoryRecords().equals(carUsage.getHistoryRecords()) : carUsage.getHistoryRecords() != null);

    }

    @Override
    public int hashCode() {
        int result = getLicensePlate() != null ? getLicensePlate().hashCode() : 0;
        result = 31 * result + (getEmployee() != null ? getEmployee().hashCode() : 0);
        result = 31 * result + (getOrderedCar() != null ? getOrderedCar().hashCode() : 0);
        result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 0);
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        result = 31 * result + (getInitialEndDate() != null ? getInitialEndDate().hashCode() : 0);
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        result = 31 * result + (getUsageUpdates() != null ? getUsageUpdates().hashCode() : 0);
        result = 31 * result + (getHistoryRecords() != null ? getHistoryRecords().hashCode() : 0);
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

    public void addUsageUpdate(PeriodicUsageUpdate usageUpdate){
        usageUpdates.add(usageUpdate);
    }

    public List<HistoryRecord> getHistoryRecords() {
        return historyRecords;
    }

    public void setHistoryRecords(List<HistoryRecord> historyRecords) {
        this.historyRecords = historyRecords;
    }

    @Override
    public int compareTo(CarUsage o) {
        return this.initialEndDate.compareTo(o.getInitialEndDate());
    }
}
