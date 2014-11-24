package portal.model.views;

import portal.config.ApplicationContants;
import portal.config.JPA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = ApplicationContants.VIEW_NAME_ORDR_LIN)
public class SaleOrderLineView implements Serializable {
    @Id
    @Column(name = "docnum")
    private Long docNum;
    @Id
    @Column(name = "itemcode")
    private String itemCode;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "u_au_comision")
    private Double uAuComision;
    @Column(name = "totalcomision")
    private Double totalComision;
    @Column(name = "whscode")
    private Integer whsCode;

    //<editor-fold desc="Getters and Setters">
    public Long getDocNum() {
        return docNum;
    }

    public void setDocNum(Long docNum) {
        this.docNum = docNum;
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Integer getWhsCode() {
        return whsCode;
    }

    public void setWhsCode(Integer whsCode) {
        this.whsCode = whsCode;
    }
    //</editor-fold>

    public static List<SaleOrderLineView> findByDocNum(Long docNum) {
        return JPA.query("SELECT lv FROM SaleOrderLineView lv WHERE lv.docNum = ?1", docNum);
    }
}
