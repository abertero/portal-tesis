package portal.model;

import portal.config.JPA;
import portal.model.base.NamedBaseEntity;

import javax.persistence.Entity;
import java.util.List;
import java.util.UUID;

@Entity
public class Garage extends NamedBaseEntity {
    private String address;

    //<editor-fold desc="Getters and setters">
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //</editor-fold>

    @Override
    protected String attributes() {
        return super.attributes() + ", address: " + address;
    }

    //<editor-fold desc="Static methods">
    public static Garage dummy() {
        Garage dummy = new Garage();
        dummy.setName("name_" + UUID.randomUUID().toString());
        dummy.setAddress("address_" + UUID.randomUUID().toString());
        return dummy;
    }

    public static List<Garage> findAll() {
        return JPA.findAll(Garage.class);
    }

    public static Garage findById(Long id) {
        return JPA.em().find(Garage.class, id);
    }

    public static Garage findByAltKey(String altKey) {
        return JPA.findByAltKey(Garage.class, altKey);
    }
    //</editor-fold>
}
