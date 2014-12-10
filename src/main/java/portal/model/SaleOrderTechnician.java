package portal.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portal.config.JPA;
import portal.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class SaleOrderTechnician extends BaseEntity {
    private static final Logger logger = LoggerFactory.getLogger(SaleOrderTechnician.class);

    @ManyToOne(fetch = FetchType.LAZY)
    private SaleOrder saleOrder;
    @ManyToOne(fetch = FetchType.EAGER)
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

    public boolean save() {
        try {
            if (!JPA.em().contains(this)) {
                JPA.em().persist(this);
            } else {
                JPA.em().flush();
            }
            logger.info("Modified sale order technician " + toString());
            return true;
        } catch (Exception e) {
            logger.error("Exception modifying sale order technician", e);
        }
        return false;
    }

    @Override
    public String attributes() {
        return super.attributes() + ", saleOrder: {" + saleOrder.attributes() + "}, technician: {" + technician.attributes() +
                "}, comission: " + comission;
    }

    public static void removeForSaleOrder(Long idSaleOrder) {
        JPA.updateNamed("DELETE FROM SaleOrderTechnician st WHERE st.saleOrder.id = :idSaleOrder", "idSaleOrder", idSaleOrder);
    }
}
