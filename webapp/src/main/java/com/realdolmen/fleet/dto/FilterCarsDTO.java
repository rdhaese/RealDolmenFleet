package com.realdolmen.fleet.dto;

/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
public class FilterCarsDTO {

    private String model;
    private String brand;
    private String level = "allPossible";
    private String inFreePool = "doesNotMatter";

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

    public String getInFreePool() {
        return inFreePool;
    }

    public void setInFreePool(String inFreePool) {
        this.inFreePool = inFreePool;
    }
}
