package portal.model;

import portal.config.JPA;
import portal.model.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class SaleOrder extends BaseEntity {
    private Long docNum;
    private String cardCode;
    private String cardName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date docDate;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "saleOrder", cascade = CascadeType.ALL)
    public List<SaleOrderDetail> details = new ArrayList<>();

    //<editor-fold desc="Getters and setters">
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<SaleOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<SaleOrderDetail> details) {
        this.details = details;
    }

    //</editor-fold>


    @Override
    protected String attributes() {
        return super.attributes() + ", docNum: " + docNum + ", cardCode: " + cardCode + ", cardName: " + cardName +
                ", docDate: " + docDate + ", uAuAno: " + uAuAno + ",uAuPatente: " + uAuPatente + ", uAuChasis: " + uAuChasis +
                ", uAuCodVend: " + uAuCodVend + ", uAuNameVend: " + uAuNameVend + ", uAuLastNameVend: " + uAuLastNameVend +
                ", uAuBodDestino: " + uAuBodDestino + ", prjName: " + prjName;
    }

    //<editor-fold desc="Static methods">
    public static SaleOrder dummy() {
        SaleOrder dummy = new SaleOrder();
        dummy.setAltKey(UUID.randomUUID().toString());
        dummy.setCardName("card_name_" + UUID.randomUUID().toString());
        dummy.setCardCode("card_code_" + UUID.randomUUID().toString());
        dummy.setPrjName("prj_na,e_" + UUID.randomUUID().toString());
        dummy.setuAuBodDestino("u_au_bod_destino_" + UUID.randomUUID().toString());
        dummy.setuAuChasis("u_au_chasis_" + UUID.randomUUID().toString());
        dummy.setuAuCodVend("u_au_cod_vend_" + UUID.randomUUID().toString());
        dummy.setuAuPatente("u_au_patente_" + UUID.randomUUID().toString());
        dummy.setuAuNameVend("u_au_name_vend_" + UUID.randomUUID().toString());
        dummy.setuAuLastNameVend("u_au_last_name_vend_" + UUID.randomUUID().toString());
        dummy.setDocNum(312931L);
        dummy.setGarage(Garage.dummy());
        dummy.setTechnician(Technician.dummy());
        dummy.setDocDate(new Date());
        dummy.setVehicle(Vehicle.dummy());
        return dummy;
    }

    public static List<SaleOrder> dummyList() {
        return dummyList(10);
    }

    public static List<SaleOrder> dummyList(int N) {
        List<SaleOrder> dummyList = new ArrayList<SaleOrder>();
        for (int i = 0; i < N; i++) {
            dummyList.add(dummy());
        }
        return dummyList;
    }

    public static List<SaleOrder> findAll() {
        return JPA.findAll(SaleOrder.class);
    }

    public static SaleOrder findById(Long id) {
        return JPA.em().find(SaleOrder.class, id);
    }

    public static SaleOrder findByAltKey(String altKey) {
        return JPA.findByAltKey(SaleOrder.class, altKey);
    }
    //</editor-fold>

}
