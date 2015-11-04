package com.realdolmen.fleet.dto;

import java.io.Serializable;

/**
 * Created on 3/11/2015.
 *
 * @author Robin D'Haese
 */
public class OrderToConfirmDTO implements Serializable {

    private String employeeName;
    private String orderDate;
    private Long orderId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderToConfirmDTO that = (OrderToConfirmDTO) o;

        if (getEmployeeName() != null ? !getEmployeeName().equals(that.getEmployeeName()) : that.getEmployeeName() != null)
            return false;
        if (getOrderDate() != null ? !getOrderDate().equals(that.getOrderDate()) : that.getOrderDate() != null)
            return false;
        return !(getOrderId() != null ? !getOrderId().equals(that.getOrderId()) : that.getOrderId() != null);

    }

    @Override
    public int hashCode() {
        int result = getEmployeeName() != null ? getEmployeeName().hashCode() : 0;
        result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 0);
        result = 31 * result + (getOrderId() != null ? getOrderId().hashCode() : 0);
        return result;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
