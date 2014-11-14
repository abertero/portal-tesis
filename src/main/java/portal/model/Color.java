package portal.model;

import portal.config.JPA;
import portal.model.base.NamedBaseEntity;

import javax.persistence.Entity;

@Entity
public class Color extends NamedBaseEntity {

    public static Color findById(Long id) {
        return JPA.em().getReference(Color.class, id);
    }
}
