package model;

import model.base.NamedBaseEntity;

import javax.persistence.Entity;

@Entity
public class Garage extends NamedBaseEntity {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
