package model.user;

import model.base.NamedBaseEntity;

import javax.persistence.Entity;

@Entity
public class User extends NamedBaseEntity {
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
