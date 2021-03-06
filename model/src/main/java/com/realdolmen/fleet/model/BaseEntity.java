package com.realdolmen.fleet.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 27/10/2015.
 *
 * Base Entity for every Entity to extend.
 * Makes sure every entity has an id and version
 * @author Robin D'Haese
 */
@MappedSuperclass
public class BaseEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
