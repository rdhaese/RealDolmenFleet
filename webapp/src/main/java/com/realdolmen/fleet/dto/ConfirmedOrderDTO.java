package com.realdolmen.fleet.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2/11/2015.
 *
 * @author Robin D'Haese
 */
public class ConfirmedOrderDTO implements Serializable{
    private Long orderId;
    private String licensePlate;
    private Double price;
    private String orderedDate;
    private String startDate;
    private String initialEndDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfirmedOrderDTO that = (ConfirmedOrderDTO) o;

        if (getOrderId() != null ? !getOrderId().equals(that.getOrderId()) : that.getOrderId() != null) return false;
        if (getLicensePlate() != null ? !getLicensePlate().equals(that.getLicensePlate()) : that.getLicensePlate() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(that.getPrice()) : that.getPrice() != null) return false;
        if (getStartDate() != null ? !getStartDate().equals(that.getStartDate()) : that.getStartDate() != null)
            return false;
        return !(getInitialEndDate() != null ? !getInitialEndDate().equals(that.getInitialEndDate()) : that.getInitialEndDate() != null);

    }

    @Override
    public int hashCode() {
        int result = getOrderId() != null ? getOrderId().hashCode() : 0;
        result = 31 * result + (getLicensePlate() != null ? getLicensePlate().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        result = 31 * result + (getInitialEndDate() != null ? getInitialEndDate().hashCode() : 0);
        return result;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getInitialEndDate() {
        return initialEndDate;
    }

    public void setInitialEndDate(String initialEndDate) {
        this.initialEndDate = initialEndDate;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }
}
