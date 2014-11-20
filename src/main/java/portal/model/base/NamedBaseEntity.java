package portal.model.base;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NamedBaseEntity extends BaseEntity {
    protected String name;
    protected String description;

    //<editor-fold desc="Getters and Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //</editor-fold>

    @Override
    protected String attributes() {
        return super.attributes() + ", name: " + name + ", description: " + description;
    }
}
