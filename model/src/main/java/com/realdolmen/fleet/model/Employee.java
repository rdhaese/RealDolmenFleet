package com.realdolmen.fleet.model;

import javax.persistence.Entity;

/**
 * Created on 27/10/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class Employee extends BaseEntity{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
