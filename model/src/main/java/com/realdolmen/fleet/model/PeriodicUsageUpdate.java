package com.realdolmen.fleet.model;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
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
    private double totalFuelPrice;

    @DecimalMin("0")
    private int newTotalKm;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date updateDate;


    public PeriodicUsageUpdate(Date updateDate, int newTotalKm, double totalFuelledForPeriod, double totalFuelPrice) {
        this.updateDate = updateDate;
        this.newTotalKm = newTotalKm;
        this.totalFuelledForPeriod = totalFuelledForPeriod;
        this.totalFuelPrice = totalFuelPrice;
    }

    public PeriodicUsageUpdate() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodicUsageUpdate that = (PeriodicUsageUpdate) o;

        if (Double.compare(that.getTotalFuelledForPeriod(), getTotalFuelledForPeriod()) != 0) return false;
        if (Double.compare(that.getTotalFuelPrice(), getTotalFuelPrice()) != 0) return false;
        if (getNewTotalKm() != that.getNewTotalKm()) return false;
        return !(getUpdateDate() != null ? !getUpdateDate().equals(that.getUpdateDate()) : that.getUpdateDate() != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getTotalFuelledForPeriod());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getTotalFuelPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getNewTotalKm();
        result = 31 * result + (getUpdateDate() != null ? getUpdateDate().hashCode() : 0);
        return result;
    }

    public double getTotalFuelledForPeriod() {
        return totalFuelledForPeriod;
    }

    public void setTotalFuelledForPeriod(double totalFuelledForPeriod) {
        this.totalFuelledForPeriod = totalFuelledForPeriod;
    }

    public double getTotalFuelPrice() {
        return totalFuelPrice;
    }

    public void setTotalFuelPrice(double totalFuelPrice) {
        this.totalFuelPrice = totalFuelPrice;
    }

    public int getNewTotalKm() {
        return newTotalKm;
    }

    public void setNewTotalKm(int newTotalKm) {
        this.newTotalKm = newTotalKm;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
