package portal.model;

import portal.config.JPA;
import portal.model.base.NamedBaseEntity;

import javax.persistence.Entity;
import java.util.List;
import java.util.UUID;

@Entity
public class ParkingLot extends NamedBaseEntity {
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
    public static ParkingLot dummy() {
        ParkingLot dummy = new ParkingLot();
        dummy.setName("name_" + UUID.randomUUID().toString());
        dummy.setAddress("address_" + UUID.randomUUID().toString());
        return dummy;
    }

    public static List<ParkingLot> findAll() {
        return JPA.findAll(ParkingLot.class);
    }

    public static ParkingLot findById(Long id) {
        return JPA.em().find(ParkingLot.class, id);
    }

    public static ParkingLot findByAltKey(String altKey) {
        return JPA.findByAltKey(ParkingLot.class, altKey);
    }
    //</editor-fold>
}
