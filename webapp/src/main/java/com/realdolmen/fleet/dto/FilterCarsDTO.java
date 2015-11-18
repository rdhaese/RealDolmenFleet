package com.realdolmen.fleet.dto;

import java.io.Serializable;

/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
public class FilterCarsDTO implements Serializable {

    private String model;
    private String brand;
    private String level = "allPossible";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilterCarsDTO that = (FilterCarsDTO) o;

        if (getModel() != null ? !getModel().equals(that.getModel()) : that.getModel() != null) return false;
        if (getBrand() != null ? !getBrand().equals(that.getBrand()) : that.getBrand() != null) return false;
        return !(getLevel() != null ? !getLevel().equals(that.getLevel()) : that.getLevel() != null);

    }

    @Override
    public int hashCode() {
        int result = getModel() != null ? getModel().hashCode() : 0;
        result = 31 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 31 * result + (getLevel() != null ? getLevel().hashCode() : 0);
        return result;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
