package portal.model;

import portal.model.base.NamedBaseEntity;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Vehicle extends NamedBaseEntity {
    public static Vehicle dummy() {
        Vehicle dummy = new Vehicle();
        dummy.setName("name_" + UUID.randomUUID().toString());
        return dummy;
    }
}
