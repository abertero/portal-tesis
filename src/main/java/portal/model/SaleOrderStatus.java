package portal.model;

import portal.config.JPA;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class SaleOrderStatus {
    @Id
    private Long id;
    private String name;

    //<editor-fold desc="Getters and Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>

    public static List<SaleOrderStatus> findAll() {
        return JPA.findAll(SaleOrderStatus.class);
    }

    public static SaleOrderStatus findById(Long id){
        return JPA.em().find(SaleOrderStatus.class, id);
    }
}
