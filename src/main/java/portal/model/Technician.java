package portal.model;

import portal.config.JPA;
import portal.model.base.BaseEntity;

import javax.persistence.Entity;
import java.util.List;
import java.util.UUID;

@Entity
public class Technician extends BaseEntity {
    private String firstName;
    private String lastName;

    //<editor-fold desc="Getters and setters">
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    //</editor-fold>

    @Override
    protected String attributes() {
        return super.attributes() + ", firstName: " + firstName + ", lastName: " + lastName;
    }

    //<editor-fold desc="Static methods">
    public static Technician dummy() {
        Technician dummy = new Technician();
        dummy.setFirstName("name_" + UUID.randomUUID().toString());
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
    //</editor-fold>
}
