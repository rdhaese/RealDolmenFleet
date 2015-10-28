package com.realdolmen.fleet.model;

import javax.persistence.Entity;

/**
 * Created by JVDAX31 on 28/10/2015.
 */

@Entity
public class CarOption extends BaseEntity {

    private String name;
    private String description;


    public CarOption(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public CarOption(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
