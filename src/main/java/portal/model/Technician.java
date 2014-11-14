package portal.model;

import portal.config.JPA;
import portal.model.base.NamedBaseEntity;

import javax.persistence.Entity;
import java.util.List;
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

    public static List<Technician> findAll() {
        return JPA.findAll(Technician.class);
    }

    public static Technician findById(Long id) {
        return JPA.em().find(Technician.class, id);
    }

    public static Technician findByAltKey(String altKey) {
        return JPA.findByAltKey(Technician.class, altKey);
    }
}
