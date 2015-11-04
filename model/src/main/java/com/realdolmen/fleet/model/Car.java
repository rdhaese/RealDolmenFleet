package com.realdolmen.fleet.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28/10/2015.
 * Entity that represents a car.
 * @author Robin D'Haese
 */
@Entity
public class Car extends BaseEntity{

    @NotNull
    private String brand;
    @NotNull
    private String model;
    @DecimalMin(value = "0")
    @DecimalMax(value = "8")
    private int category;
    @DecimalMin(value = "1")
    private double pk;
    @DecimalMin(value = "1")
    private double emission;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @DecimalMin(value = "1")
    private double fiscalHP;
    @DecimalMin(value = "0")
    private double deliveryTime;
    @DecimalMin(value = "1")
    private int idealKm;
    @DecimalMin(value = "1")
    private int maxKm;
    @DecimalMin(value = "1")
    private double listPrice;
    @DecimalMin(value = "1")
    private double benefit;
    @DecimalMin(value = "0")
    private double amountUpgrade;
    @DecimalMin(value = "0")
    private double amountDowngrade;
    @ElementCollection
    private List<String> pictures = new ArrayList<>();
    @ManyToOne
    private Pack basePack;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pack> extraPacks = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CarOption> extraOptions = new ArrayList<>();


    public Car(String brand, String model, int category, double pk, double emission, FuelType fuelType, CarType carType, double fiscalHP, double deliveryTime, int idealKm, int maxKm, double listPrice, double benefit, double amountUpgrade, double amountDowngrade, Pack basePack, List<Pack> extraPacks, List<CarOption> extraOptions) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.pk = pk;
        this.emission = emission;
        this.fuelType = fuelType;
        this.carType = carType;
        this.fiscalHP = fiscalHP;
        this.deliveryTime = deliveryTime;
        this.idealKm = idealKm;
        this.maxKm = maxKm;
        this.listPrice = listPrice;
        this.benefit = benefit;
        this.amountUpgrade = amountUpgrade;
        this.amountDowngrade = amountDowngrade;
        this.basePack = basePack;
        this.extraPacks = extraPacks;
        this.extraOptions = extraOptions;

    }
     public Car(){

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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPk() {
        return pk;
    }

    public void setPk(double pk) {
        this.pk = pk;
    }

    public double getEmission() {
        return emission;
    }

    public void setEmission(double emission) {
        this.emission = emission;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public double getFiscalHP() {
        return fiscalHP;
    }

    public void setFiscalHP(double fiscalHP) {
        this.fiscalHP = fiscalHP;
    }

    public double getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(double deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getIdealKm() {
        return idealKm;
    }

    public void setIdealKm(int idealKm) {
        this.idealKm = idealKm;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getBenefit() {
        return benefit;
    }

    public void setBenefit(double benefit) {
        this.benefit = benefit;
    }

    public double getAmountUpgrade() {
        return amountUpgrade;
    }

    public void setAmountUpgrade(double amountUpgrade) {
        this.amountUpgrade = amountUpgrade;
    }

    public double getAmountDowngrade() {
        return amountDowngrade;
    }

    public void setAmountDowngrade(double amountDowngrade) {
        this.amountDowngrade = amountDowngrade;
    }

    public Pack getBasePack() {
        return basePack;
    }

    public void setBasePack(Pack basePack) {
        this.basePack = basePack;
    }

    public List<Pack> getExtraPacks() {
        return extraPacks;
    }

    public void setExtraPacks(List<Pack> extraPacks) {
        this.extraPacks = extraPacks;
    }

    public List<CarOption> getExtraOptions() {
        return extraOptions;
    }

    public void setExtraOptions(List<CarOption> extraOptions) {
        this.extraOptions = extraOptions;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (getCategory() != car.getCategory()) return false;
        if (Double.compare(car.getPk(), getPk()) != 0) return false;
        if (Double.compare(car.getEmission(), getEmission()) != 0) return false;
        if (Double.compare(car.getFiscalHP(), getFiscalHP()) != 0) return false;
        if (Double.compare(car.getDeliveryTime(), getDeliveryTime()) != 0) return false;
        if (getIdealKm() != car.getIdealKm()) return false;
        if (getMaxKm() != car.getMaxKm()) return false;
        if (Double.compare(car.getListPrice(), getListPrice()) != 0) return false;
        if (Double.compare(car.getBenefit(), getBenefit()) != 0) return false;
        if (Double.compare(car.getAmountUpgrade(), getAmountUpgrade()) != 0) return false;
        if (Double.compare(car.getAmountDowngrade(), getAmountDowngrade()) != 0) return false;
        if (getBrand() != null ? !getBrand().equals(car.getBrand()) : car.getBrand() != null) return false;
        if (getModel() != null ? !getModel().equals(car.getModel()) : car.getModel() != null) return false;
        if (getFuelType() != car.getFuelType()) return false;
        if (getCarType() != car.getCarType()) return false;
        if (getBasePack() != null ? !getBasePack().equals(car.getBasePack()) : car.getBasePack() != null) return false;
        if (getExtraPacks() != null ? !getExtraPacks().equals(car.getExtraPacks()) : car.getExtraPacks() != null)
            return false;
        return !(getExtraOptions() != null ? !getExtraOptions().equals(car.getExtraOptions()) : car.getExtraOptions() != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getBrand() != null ? getBrand().hashCode() : 0;
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + getCategory();
        temp = Double.doubleToLongBits(getPk());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getEmission());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getFuelType() != null ? getFuelType().hashCode() : 0);
        result = 31 * result + (getCarType() != null ? getCarType().hashCode() : 0);
        temp = Double.doubleToLongBits(getFiscalHP());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getDeliveryTime());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getIdealKm();
        result = 31 * result + getMaxKm();
        temp = Double.doubleToLongBits(getListPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getBenefit());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getAmountUpgrade());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getAmountDowngrade());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getBasePack() != null ? getBasePack().hashCode() : 0);
        result = 31 * result + (getExtraPacks() != null ? getExtraPacks().hashCode() : 0);
        result = 31 * result + (getExtraOptions() != null ? getExtraOptions().hashCode() : 0);
        return result;
    }

    public void addExtraPack(Pack p){
        extraPacks.add(p);
    }

    public void addExtraOption(CarOption c){
       extraOptions.add(c);
    }

    public void CopyCar(Car source){
            this.brand = source.brand;
            this.model = source.model;
            this.category = source.category;
            this.pk = source.pk;
            this.emission = source.emission;
            this.fuelType = source.fuelType;
            this.carType = source.carType;
            this.fiscalHP = source.fiscalHP;
            this.deliveryTime = source.deliveryTime;
            this.idealKm = source.idealKm;
            this.maxKm = source.maxKm;
            this.listPrice = source.listPrice;
            this.benefit = source.benefit;
            this.amountUpgrade = source.amountUpgrade;
            this.amountDowngrade = source.amountDowngrade;
            this.basePack = source.basePack;
            this.extraPacks = source.extraPacks;
            this.extraOptions = source.extraOptions;

        }


}
