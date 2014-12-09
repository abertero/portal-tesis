package portal.model;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portal.config.JPA;
import portal.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SaleOrderLog extends BaseEntity {
    private static final Logger logger = LoggerFactory.getLogger(SaleOrderLog.class);

    private String change;
    @Column(length = 4000)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private SaleOrder saleOrder;

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SaleOrder getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }

    public SaleOrderLog() {
    }

    public SaleOrderLog(SaleOrder saleOrder, String change) {
        this.change = change;
        this.status = makeStatus(saleOrder);
        this.saleOrder = saleOrder;
    }

    private static String makeStatus(SaleOrder saleOrder) {
        String result = "Técnicos: ";
        List<String> technicianNames = new ArrayList<>();
        for (SaleOrderTechnician saleOrderTechnician : saleOrder.getTechnicians()) {
            technicianNames.add(saleOrderTechnician.getTechnician().getFullName() + " (Comisión $" + (saleOrderTechnician.getComission() != null ? saleOrderTechnician.getComission() : 0d) + ")");
        }
        result += (technicianNames.isEmpty() ? "S/T" : StringUtils.join(technicianNames.toArray(), ", ")) + "\n";
        result += "Estacionamiento: " + (saleOrder.getParking() != null ? "N/D" : saleOrder.getParking()) + "\n";
        result += "Estado: " + (saleOrder.getStatus() != null ? saleOrder.getStatus().getName() : "S/E");
        return result;
    }

    public boolean save() {
        try {
            if (!JPA.em().contains(this)) {
                JPA.em().persist(this);
            } else {
                JPA.em().flush();
            }
            logger.info("Added log " + toString());
            return true;
        } catch (Exception e) {
            logger.error("Exception modifying log", e);
        }
        return false;
    }

    @Override
    public String attributes() {
        return super.attributes() + ", saleOrder: {" + saleOrder.attributes() + "}, change: " + change + ", status: " + status;
    }
}
