package com.realdolmen.fleet.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created on 28/10/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class Car extends BaseEntity{

    private String brand;
    private String model;
    private int category;
    private double pk;
    private double emission;
    private String fuelType;
    private double fiscalHP;
    private double deliveryTime;
    private int idealKm;
    private int maxKm;
    private double listPrice;
    private double benefit;
    private double amountUpgrade;
    private double amountDowngrade;

    @ManyToOne
    private Pack basePack;
    @OneToMany
    private List<Pack> extraPacks;
    @OneToMany
    private List<CarOption> extraOptions;



    public Car(String brand, String model, int category, double pk, double emission, String fuelType, double fiscalHP, double deliveryTime, int idealKm, int maxKm, double listPrice, double benefit, double amountUpgrade, double amountDowngrade, Pack basePack, List<Pack> extraPacks, List<CarOption> extraOptions) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.pk = pk;
        this.emission = emission;
        this.fuelType = fuelType;
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

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
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


}
