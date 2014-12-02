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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sale_order_technician",
            joinColumns = {@JoinColumn(name = "id_sale_order", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_technician", referencedColumnName = "id")})
    private List<Technician> technicians = new ArrayList<>();

    //<editor-fold desc="Getters and Setters">
    public Long getIdDocNum() {
        return idDocNum;
    }

    public void setIdDocNum(Long idDocNum) {
        this.idDocNum = idDocNum;
    }

    public List<Technician> getTechnicians() {
        return technicians;
    }

    public void setTechnicians(List<Technician> technicians) {
        this.technicians = technicians;
    }

    public Integer getParking() {
        return parking;
    }

    public void setParking(Integer parking) {
        this.parking = parking;
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
        return super.attributes() + ", idDocNum: " + idDocNum;
    }

    public boolean saveWithTechnicians(Long[] idTechnicians) {
        if (idTechnicians != null) {
            technicians.clear();
            technicians.addAll(Technician.findbyIds(idTechnicians));
        }
        return save();
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

    public void validateSaleOrderForm(BindingResult errors) {

    }

    private void loadViews(boolean loadLines) {
        headerView = SaleOrderHeaderView.findByDocNum(idDocNum);
        if (loadLines) {
            headerLines.addAll(SaleOrderLineView.findByDocNum(idDocNum));
        }
    }

    public void getData(SaleOrder saleOrder) {
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
    //</editor-fold>
}
