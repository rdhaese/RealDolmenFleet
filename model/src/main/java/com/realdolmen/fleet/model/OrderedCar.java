package com.realdolmen.fleet.model;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 30/10/2015.
 *
 * @author Robin D'Haese
 */
//@Entity
public class OrderedCar extends BaseEntity{
    private Car car;
    private String color;
    private List<Pack> packs = new ArrayList<>();
    private List<CarOption> options = new ArrayList<>();
    private double price;
}
