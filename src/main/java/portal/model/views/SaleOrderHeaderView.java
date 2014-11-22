package portal.model.views;

import portal.config.ApplicationContants;
import portal.config.JPA;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = ApplicationContants.VIEW_NAME_ORDR_ENC)
public class SaleOrderHeaderView {
    @Id
    @Column(name = "docnum")
    private Long docNum;
    @Column(name = "cardcode")
    private String cardCode;
    @Column(name = "cardname")
    private String cardName;
    @Column(name = "docdate")
    @Temporal(TemporalType.DATE)
    private Date docDate;
    @Column(name = "name")
    private String name;
    @Column(name = "u_au_ano")
    private String uAuAno;
    @Column(name = "u_au_patente")
    private String uAuPatente;
    @Column(name = "u_au_chasis")
    private String uAuChasis;
    @Column(name = "color")
    private String color;
    @Column(name = "vendedor")
    private String vendedor;

    //<editor-fold desc="Getters and Setters">
    public Long getDocNum() {
        return docNum;
    }

    public void setDocNum(Long docNum) {
        this.docNum = docNum;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuAuAno() {
        return uAuAno;
    }

    public void setuAuAno(String uAuAno) {
        this.uAuAno = uAuAno;
    }

    public String getuAuPatente() {
        return uAuPatente;
    }

    public void setuAuPatente(String uAuPatente) {
        this.uAuPatente = uAuPatente;
    }

    public String getuAuChasis() {
        return uAuChasis;
    }

    public void setuAuChasis(String uAuChasis) {
        this.uAuChasis = uAuChasis;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }
    //</editor-fold>

    public static List<SaleOrderHeaderView> findAll(int page) {
        return JPA.pageQuery("SELECT hv FROM SaleOrderHeaderView hv", SaleOrderHeaderView.class, page);
    }

    public static SaleOrderHeaderView findByDocNum(Long docNum) {
        return JPA.em().find(SaleOrderHeaderView.class, docNum);
    }
}
