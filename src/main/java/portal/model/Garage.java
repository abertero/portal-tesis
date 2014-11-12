package portal.model;

import portal.model.base.NamedBaseEntity;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Garage extends NamedBaseEntity {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Garage dummy() {
        Garage dummy = new Garage();
        dummy.setName("name_" + UUID.randomUUID().toString());
        dummy.setAddress("address_" + UUID.randomUUID().toString());
        return dummy;
    }
}
