package portal.model;

import portal.config.JPA;
import portal.model.base.BaseEntity;
import portal.model.views.SaleOrderHeaderView;
import portal.model.views.SaleOrderLineView;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SaleOrder extends BaseEntity {
    private Long idDocNum;
    @Transient
    private SaleOrderHeaderView headerView = null;
    @Transient
    private List<SaleOrderLineView> headerLines = new ArrayList<>();

    public Long getIdDocNum() {
        return idDocNum;
    }

    public void setIdDocNum(Long idDocNum) {
        this.idDocNum = idDocNum;
    }

    @Override
    protected String attributes() {
        return super.attributes() + ", idDocNum: " + idDocNum;
    }

    @Transient
    public SaleOrderHeaderView getHeaderView() {
        return headerView;
    }

    @Transient
    public List<SaleOrderLineView> getHeaderLines() {
        return headerLines;
    }

    private void loadViews(boolean loadLines) {
        headerView = SaleOrderHeaderView.findByDocNum(idDocNum);
        if (loadLines) {
            headerLines.addAll(SaleOrderLineView.findByDocNum(idDocNum));
        }
    }

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
}
