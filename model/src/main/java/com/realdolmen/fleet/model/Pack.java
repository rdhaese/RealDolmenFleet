package com.realdolmen.fleet.model;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JVDAX31 on 28/10/2015.
 * Entity representing a pack (=collection of car options, name and price) a car can have
 */

@Entity
public class Pack extends BaseEntity {

    @NotNull
    @Size(min=1, max=255)
    private String name;

    @ManyToMany
    @JoinTable(name="pack_car_options",
            joinColumns=@JoinColumn(name="pack_id"),
            inverseJoinColumns=@JoinColumn(name="car_options_id"))
    private List<CarOption> carOptions = new ArrayList<>();

    @Min(value=1)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pack pack = (Pack) o;

        if (Double.compare(pack.getPrice(), getPrice()) != 0) return false;
        if (getName() != null ? !getName().equals(pack.getName()) : pack.getName() != null) return false;
        return !(getCarOptions() != null ? !getCarOptions().equals(pack.getCarOptions()) : pack.getCarOptions() != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCarOptions() != null ? getCarOptions().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public void addCarOption(CarOption carOption){
        carOptions.add(carOption);
    }

    public void removeCarOption(int pos){
        carOptions.remove(pos);
    }
}
