package com.realdolmen.fleet.model;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by JVDAX31 on 28/10/2015.
 */

@Entity
public class Pack extends BaseEntity {

    private String name;

    @OneToMany
    private List<CarOption> carOptions;

    private double price;


    public Pack(String name, double price, List<CarOption> carOptions) {
        this.name = name;
        this.price = price;
        this.carOptions = carOptions;
    }

    public Pack(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<CarOption> getCarOptions() {
        return carOptions;
    }

    public void setCarOptions(List<CarOption> carOptions) {
        this.carOptions = carOptions;
    }
}
