package com.realdolmen.fleet.model;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Created on 10/11/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class HistoryRecord extends BaseEntity{

    @ManyToOne
    @NotNull
    private Employee employee;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date drivenUntil;
    @NotNull
    @DecimalMin("1")
    private Integer lastKm;

    public HistoryRecord(Employee employee, Date drivenUntil, Integer lastKm) {
        this.employee = employee;
        this.drivenUntil = drivenUntil;
        this.lastKm = lastKm;
    }

    public HistoryRecord() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoryRecord that = (HistoryRecord) o;

        if (getEmployee() != null ? !getEmployee().equals(that.getEmployee()) : that.getEmployee() != null)
            return false;
        if (getDrivenUntil() != null ? !getDrivenUntil().equals(that.getDrivenUntil()) : that.getDrivenUntil() != null)
            return false;
        return !(getLastKm() != null ? !getLastKm().equals(that.getLastKm()) : that.getLastKm() != null);

    }

    @Override
    public int hashCode() {
        int result = getEmployee() != null ? getEmployee().hashCode() : 0;
        result = 31 * result + (getDrivenUntil() != null ? getDrivenUntil().hashCode() : 0);
        result = 31 * result + (getLastKm() != null ? getLastKm().hashCode() : 0);
        return result;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDrivenUntil() {
        return drivenUntil;
    }

    public void setDrivenUntil(Date drivenUntil) {
        this.drivenUntil = drivenUntil;
    }

    public Integer getLastKm() {
        return lastKm;
    }

    public void setLastKm(Integer lastKm) {
        this.lastKm = lastKm;
    }
}
