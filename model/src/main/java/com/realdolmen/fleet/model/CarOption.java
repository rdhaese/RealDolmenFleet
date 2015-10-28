package com.realdolmen.fleet.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by JVDAX31 on 28/10/2015.
 * Entity that represents an option for a car.
 */
@Entity
public class CarOption extends BaseEntity {

    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarOption carOption = (CarOption) o;

        if (getName() != null ? !getName().equals(carOption.getName()) : carOption.getName() != null) return false;
        return !(getDescription() != null ? !getDescription().equals(carOption.getDescription()) : carOption.getDescription() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
