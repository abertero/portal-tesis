package portal.model.views;

import portal.config.ApplicationContants;
import portal.config.JPA;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = ApplicationContants.VIEW_NAME_MACH_SHOP)
public class MachineShopListView {
    @Id
    @Column(name = "orden_de_venta")
    private Long docNum;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date docDate;
    @Column(name = "vehiculo")
    private String vehicle;
    @Column(name = "color")
    private String color;
    @Column(name = "patente")
    private String uAuPatente;
    @Column(name = "chasis")
    private String uAuChasis;
    @Column(name = "ubicacion")
    private Long location;
    @Column(name = "vendedor")
    private String uAuNameVendor;
    @Column(name = "instalador")
    private String nameInst;
    @Column(name = "bodega")
    private String uAuBodDestino;
    @Column(name = "canal")
    private String prjName;
    @Column(name = "estado")
    private String status;

    //<editor-fold desc="Getters and Setters">
    public Long getDocNum() {
        return docNum;
    }

    public void setDocNum(Long docNum) {
        this.docNum = docNum;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public String getuAuNameVendor() {
        return uAuNameVendor;
    }

    public void setuAuNameVendor(String uAuNameVendor) {
        this.uAuNameVendor = uAuNameVendor;
    }

    public String getNameInst() {
        return nameInst;
    }

    public void setNameInst(String nameInst) {
        this.nameInst = nameInst;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Transient
    public boolean isFinished() {
        return status != null && status.equals("Finalizado");
    }
    // </editor-fold>

    public static List<MachineShopListView> findAll(int page) {
        return JPA.pageQuery("SELECT msv FROM MachineShopListView msv", MachineShopListView.class, page);
    }

    public static int findNumberOfPages() {
        return JPA.numberOfPages(MachineShopListView.class);
    }
}
