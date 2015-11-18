package com.realdolmen.fleet.dto;

import java.io.Serializable;

/**
 * Created on 12/11/2015.
 *
 * @author Robin D'Haese
 */
public class FilterCatalogueDTO implements Serializable {

    private String brand;
    private String model;
    private Integer functionalLevel;


    public FilterCatalogueDTO() {
    }

    public FilterCatalogueDTO(String brand, String model, Integer functionalLevel) {
        this.brand = brand;
        this.model = model;
        this.functionalLevel = functionalLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilterCatalogueDTO that = (FilterCatalogueDTO) o;

        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        return !(functionalLevel != null ? !functionalLevel.equals(that.functionalLevel) : that.functionalLevel != null);

    }

    @Override
    public int hashCode() {
        int result = brand != null ? brand.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (functionalLevel != null ? functionalLevel.hashCode() : 0);
        return result;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getFunctionalLevel() {
        return functionalLevel;
    }

    public void setFunctionalLevel(Integer functionalLevel) {
        this.functionalLevel = functionalLevel;
    }
}
