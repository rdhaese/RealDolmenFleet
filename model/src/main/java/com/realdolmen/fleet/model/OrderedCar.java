package com.realdolmen.fleet.model;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class OrderedCar extends BaseEntity{
    @NotNull
    @OneToOne
    private Car car;
    @NotNull
    @Size(min=1, max=255)
    private String color;
    @ManyToMany
    private List<Pack> packs = new ArrayList<>();
    @ManyToMany
    private List<CarOption> options = new ArrayList<>();
    @DecimalMin("0")
    private double price = 0D;

    public OrderedCar(Car car, String color, List<Pack> packs, List<CarOption> options, double price) {
        this.car = car;
        this.color = color;
        this.packs = packs;
        this.options = options;
        this.price = price;
    }

    public OrderedCar() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderedCar that = (OrderedCar) o;

        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        if (getCar() != null ? !getCar().equals(that.getCar()) : that.getCar() != null) return false;
        if (getColor() != null ? !getColor().equals(that.getColor()) : that.getColor() != null) return false;
        if (getPacks() != null ? !getPacks().equals(that.getPacks()) : that.getPacks() != null) return false;
        return !(getOptions() != null ? !getOptions().equals(that.getOptions()) : that.getOptions() != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getCar() != null ? getCar().hashCode() : 0;
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + (getPacks() != null ? getPacks().hashCode() : 0);
        result = 31 * result + (getOptions() != null ? getOptions().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

    public List<CarOption> getOptions() {
        return options;
    }

    public void setOptions(List<CarOption> options) {
        this.options = options;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
