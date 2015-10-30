package com.realdolmen.fleet.model;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import java.util.Date;


/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class PeriodicUsageUpdate extends BaseEntity {

    @DecimalMin("0")
    private double totalFuelledForPeriod;
    @DecimalMin("0")
    private double newTotalKm;
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Temporal(TemporalType.DATE)
    private Date toDate;

    public PeriodicUsageUpdate(double totalFuelledForPeriod, double newTotalKm, Date fromDate, Date toDate) {
        this.totalFuelledForPeriod = totalFuelledForPeriod;
        this.newTotalKm = newTotalKm;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public PeriodicUsageUpdate() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodicUsageUpdate that = (PeriodicUsageUpdate) o;

        if (Double.compare(that.totalFuelledForPeriod, totalFuelledForPeriod) != 0) return false;
        if (Double.compare(that.newTotalKm, newTotalKm) != 0) return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        return !(toDate != null ? !toDate.equals(that.toDate) : that.toDate != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(totalFuelledForPeriod);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(newTotalKm);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        return result;
    }

    public double getTotalFuelledForPeriod() {
        return totalFuelledForPeriod;
    }

    public void setTotalFuelledForPeriod(double totalFuelledForPeriod) {
        this.totalFuelledForPeriod = totalFuelledForPeriod;
    }

    public double getNewTotalKm() {
        return newTotalKm;
    }

    public void setNewTotalKm(double newTotalKm) {
        this.newTotalKm = newTotalKm;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
