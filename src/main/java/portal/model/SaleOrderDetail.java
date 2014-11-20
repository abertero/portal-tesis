package portal.model;

import portal.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class SaleOrderDetail extends BaseEntity {
    private String itemCode;
    @Lob
    private String description;
    private Long quantity;
    private String whsCode;
    private Double uAuComision;
    private Double totalComision;

    @ManyToOne(fetch = FetchType.LAZY)
    private SaleOrder saleOrder;

    //<editor-fold desc="Getters and Setters">
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getWhsCode() {
        return whsCode;
    }

    public void setWhsCode(String whsCode) {
        this.whsCode = whsCode;
    }

    public Double getuAuComision() {
        return uAuComision;
    }

    public void setuAuComision(Double uAuComision) {
        this.uAuComision = uAuComision;
    }

    public Double getTotalComision() {
        return totalComision;
    }

    public void setTotalComision(Double totalComision) {
        this.totalComision = totalComision;
    }

    public SaleOrder getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }
    //</editor-fold>
}
