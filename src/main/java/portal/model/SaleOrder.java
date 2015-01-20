package portal.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import portal.config.JPA;
import portal.model.base.BaseEntity;
import portal.model.views.SaleOrderHeaderView;
import portal.model.views.SaleOrderLineView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SaleOrder extends BaseEntity {
    private static final Logger logger = LoggerFactory.getLogger(SaleOrder.class);

    private Long idDocNum;
    @Transient
    private SaleOrderHeaderView headerView = null;
    @Transient
    private List<SaleOrderLineView> headerLines = new ArrayList<>();
    private Integer parking;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "saleOrder", cascade = CascadeType.REMOVE)
    private List<SaleOrderTechnician> technicians = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private SaleOrderStatus status;
    @OneToMany(fetch = FetchType.LAZY)
    private List<SaleOrderLog> logs = new ArrayList<>();

    //<editor-fold desc="Getters and Setters">
    public Long getIdDocNum() {
        return idDocNum;
    }

    public void setIdDocNum(Long idDocNum) {
        this.idDocNum = idDocNum;
    }

    public List<SaleOrderTechnician> getTechnicians() {
        return technicians;
    }

    public void setTechnicians(List<SaleOrderTechnician> technicians) {
        this.technicians = technicians;
    }

    public Integer getParking() {
        return parking;
    }

    public void setParking(Integer parking) {
        this.parking = parking;
    }

    public SaleOrderStatus getStatus() {
        return status;
    }

    public void setStatus(SaleOrderStatus status) {
        this.status = status;
    }

    public List<SaleOrderLog> getLogs() {
        return logs;
    }

    public void setLogs(List<SaleOrderLog> logs) {
        this.logs = logs;
    }
    //</editor-fold>

    //<editor-fold desc="Methods">
    @Transient
    public SaleOrderHeaderView getHeaderView() {
        return headerView;
    }

    @Transient
    public List<SaleOrderLineView> getHeaderLines() {
        return headerLines;
    }

    @Override
    protected String attributes() {
        return super.attributes() + ", idDocNum: " + idDocNum + ", parking: " + parking + ", status: " + (status != null ? status.getName() : "S/I");
    }

    public boolean saveWithTechnicians(Long[] idTechnicians, Double comission, String username) {
        technicians.clear();
        SaleOrderTechnician.removeForSaleOrder(id);
        if (idTechnicians != null) {
            for (Technician technician : Technician.findbyIds(idTechnicians)) {
                SaleOrderTechnician saleOrderTechnician = new SaleOrderTechnician(this, technician, comission);
                saleOrderTechnician.save();
                technicians.add(saleOrderTechnician);
            }
        }
        boolean result = save();
        if (result) {
            log("Se ha actualizado la orden.", username);
        }
        return result;
    }

    public boolean save() {
        try {
            if (!JPA.em().contains(this)) {
                JPA.em().persist(this);
            } else {
                JPA.em().flush();
            }
            logger.info("Modified order " + toString());
            return true;
        } catch (Exception e) {
            logger.error("Exception modifying order", e);
        }
        return false;
    }

    public void log(String message, String username) {
        new SaleOrderLog(this, message, username).save();
    }

    public void validateSaleOrderForm(BindingResult errors) {
        if (!SaleOrder.isValidParkingLot(idDocNum, parking)) {
            errors.rejectValue("parking", "saleOrder.validate.parking.invalid");
        }
    }

    private void loadViews(boolean loadLines) {
        headerView = SaleOrderHeaderView.findByDocNum(idDocNum);
        if (loadLines) {
            headerLines.addAll(SaleOrderLineView.findByDocNum(idDocNum));
        }
    }

    public void getData(SaleOrder saleOrder) {
        if (saleOrder.getStatus() != null && saleOrder.getStatus().getId() != null) {
            this.status = saleOrder.getStatus();
        } else {
            this.status = null;
        }
        this.parking = saleOrder.getParking();
    }
    //</editor-fold>

    //<editor-fold desc="Static Methods">
    public static SaleOrder findByDocNum(Long docNum) {
        return findByDocNum(docNum, false);
    }

    public static SaleOrder findByDocNum(Long docNum, boolean loadLines) {
        SaleOrder order = JPA.queryFirst("SELECT o FROM SaleOrder o WHERE o.idDocNum = ?1", docNum);
        if (order == null) {
            order = new SaleOrder();
            order.idDocNum = docNum;
        }
        order.loadViews(loadLines);
        return order;
    }

    public static SaleOrder findById(Long id) {
        return JPA.em().find(SaleOrder.class, id);
    }

    public static boolean isValidParkingLot(Long idDocNum, Integer parking) {
        Number count = JPA.querySingle("SELECT COUNT(s) FROM SaleOrder s WHERE s.parking = ?1 AND s.idDocNum <> ?2", parking, idDocNum);
        return count != null && count.intValue() == 0;
    }
    //</editor-fold>
}
