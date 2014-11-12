package model;

import model.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SaleOrder extends BaseEntity {
    private Long docNum;
    private String cardCode;
    private String cardName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date docDate;
    private Long quantity;
    private String whsCode;
    private Double uAuComision;
    private Integer uAuAno;
    private String uAuPatente;
    private String uAuChasis;
    private String uAuCodVend;
    private String uAuNameVend;
    private String uAuLastNameVend;
    private String uAuBodDestino;
    private String prjName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Garage garage;
    @ManyToOne(fetch = FetchType.LAZY)
    private Technician technician;

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

    public Integer getuAuAno() {
        return uAuAno;
    }

    public void setuAuAno(Integer uAuAno) {
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

    public String getuAuCodVend() {
        return uAuCodVend;
    }

    public void setuAuCodVend(String uAuCodVend) {
        this.uAuCodVend = uAuCodVend;
    }

    public String getuAuNameVend() {
        return uAuNameVend;
    }

    public void setuAuNameVend(String uAuNameVend) {
        this.uAuNameVend = uAuNameVend;
    }

    public String getuAuLastNameVend() {
        return uAuLastNameVend;
    }

    public void setuAuLastNameVend(String uAuLastNameVend) {
        this.uAuLastNameVend = uAuLastNameVend;
    }

    public String getuAuBodDestino() {
        return uAuBodDestino;
    }

    public void setuAuBodDestino(String uAuBodDestino) {
        this.uAuBodDestino = uAuBodDestino;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }
}
