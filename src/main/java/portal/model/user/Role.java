package portal.model.user;

import portal.config.JPA;
import portal.model.base.NamedBaseEntity;

import javax.persistence.Entity;

@Entity
public class Role extends NamedBaseEntity {
    public static final Long ID_APP_USER = 1L;
    public static final Long ID_APP_ADMIN = 2L;
    public static final Long ID_INSTALLER = 3L;

    public static Role findById(Long id) {
        return JPA.em().getReference(Role.class, id);
    }
}
