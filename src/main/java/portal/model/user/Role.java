package portal.model.user;

import portal.config.JPA;
import portal.model.base.NamedBaseEntity;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;

@Entity
public class Role extends NamedBaseEntity {
    public static final Long ID_APP_USER = 1L;
    public static final Long ID_APP_ADMIN = 2L;
    public static final Long ID_INSTALLER = 3L;

    public static Role findById(Long id) {
        return JPA.em().getReference(Role.class, id);
    }

    public static List<Role> findbyIds(Long[] idRoles) {
        return JPA.queryNamed("SELECT t FROM Role t WHERE t.id IN :ids", "ids", Arrays.asList(idRoles));
    }

    public static List<Role> findAll() {
        return JPA.findAll(Role.class);
    }
}
