package model.user;

import model.base.NamedBaseEntity;

import javax.persistence.Entity;

@Entity
public class Role extends NamedBaseEntity {
    public static final Long ID_APP_USER = 1L;
    public static final Long ID_APP_ADMIN = 2L;
    public static final Long ID_INSTALLER = 3L;
}
