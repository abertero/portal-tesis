package portal.model;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portal.config.JPA;
import portal.model.base.BaseEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class SaleOrderLog extends BaseEntity {
    private static final Logger logger = LoggerFactory.getLogger(SaleOrderLog.class);

    private String change;
    @Column(length = 4000)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String username;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatusNL2BR() {
        return status != null ? status.replace("\n", "</br>") : null;
    }

    public SaleOrderLog() {
    }

    public SaleOrderLog(SaleOrder saleOrder, String change, String username) {
        this.change = change;
        this.status = makeStatus(saleOrder);
        this.saleOrder = saleOrder;
        this.date = new Date();
        this.username = username;
    }

    private static String makeStatus(SaleOrder saleOrder) {
        String result = "T\u00e9cnicos: ";
        List<String> technicianNames = new ArrayList<>();
        for (SaleOrderTechnician saleOrderTechnician : saleOrder.getTechnicians()) {
            technicianNames.add(saleOrderTechnician.getTechnician().getFullName() + " (Comisi\u00f3n $" + (saleOrderTechnician.getComission() != null ? saleOrderTechnician.getComission() : 0d) + ")");
        }
        result += (technicianNames.isEmpty() ? "S/T" : StringUtils.join(technicianNames.toArray(), ", ")) + "\n";
        result += "Estacionamiento: " + (saleOrder.getParking() != null ? "N/D" : saleOrder.getParking()) + "\n";
        SaleOrderStatus status = null;
        if (saleOrder.getStatus() != null && saleOrder.getStatus().getId() != null) {
            status = SaleOrderStatus.findById(saleOrder.getStatus().getId());
        }
        result += "Estado: " + (status != null ? status.getName() : "S/E");
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
        return super.attributes() + ", saleOrder: {" + saleOrder.attributes() + "}, change: " + change + ", status: " + status +
                ", date: " + (new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(date)) + ", username: " + username;
    }

    public static List<SaleOrderLog> findBySaleOrder(Long idSaleOrder) {
        if (idSaleOrder == null) {
            return new ArrayList<>();
        }
        return JPA.query("SELECT l FROM SaleOrderLog l WHERE l.saleOrder.id = ?1 ORDER BY l.date DESC", idSaleOrder);
    }
}
