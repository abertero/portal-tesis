package portal.model;

import portal.model.base.NamedBaseEntity;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Technician extends NamedBaseEntity {
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static Technician dummy() {
        Technician dummy = new Technician();
        dummy.setName("name_" + UUID.randomUUID().toString());
        dummy.setLastName("last_name_" + UUID.randomUUID().toString());
        return dummy;
    }
}
