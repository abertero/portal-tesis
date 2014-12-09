package portal.model;

import portal.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class SaleOrderTechnician extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private SaleOrder saleOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    private Technician technician;
    private Double comission = 0d;

    public SaleOrderTechnician() {
    }

    public SaleOrderTechnician(SaleOrder saleOrder, Technician technician, Double comission) {
        this.saleOrder = saleOrder;
        this.technician = technician;
        this.comission = comission;
    }

    //<editor-fold desc="Getters and Setters">
    public SaleOrder getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public Double getComission() {
        return comission;
    }

    public void setComission(Double comission) {
        this.comission = comission;
    }
    //</editor-fold>
}
