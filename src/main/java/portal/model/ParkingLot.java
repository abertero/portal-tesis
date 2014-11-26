package portal.model;

import portal.config.JPA;
import portal.model.base.NamedBaseEntity;
import portal.model.views.SaleOrderHeaderView;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * TODO: Puede que está entidad no sirva, ver taller mecánico, preguntar si se debe modificar algo ahi (en caso que no, se debe borrar esto).
 */
@Entity
public class ParkingLot extends NamedBaseEntity {
    private String location;
    private Long idDocNum;
    @Transient
    private SaleOrderHeaderView headerView = null;

    //<editor-fold desc="Getters and setters">
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getIdDocNum() {
        return idDocNum;
    }

    public void setIdDocNum(Long idDocNum) {
        this.idDocNum = idDocNum;
    }
    //</editor-fold>

    @Transient
    public SaleOrderHeaderView getHeaderView() {
        return headerView;
    }

    @Override
    protected String attributes() {
        return super.attributes() + ", location: " + location + ", idDocNum: " + idDocNum;
    }

    //<editor-fold desc="Static methods">
    public static ParkingLot dummy() {
        ParkingLot dummy = new ParkingLot();
        dummy.setName("name_" + UUID.randomUUID().toString());
        dummy.setLocation("location_" + UUID.randomUUID().toString());
        return dummy;
    }

    public static Map<Long, ParkingLot> findAll(int page) {
        Map<Long, ParkingLot> result = new HashMap<>();
        for (SaleOrderHeaderView headerView : SaleOrderHeaderView.findAll(page)) {
            ParkingLot parkingLot = ParkingLot.findByDocNum(headerView.getDocNum());
            parkingLot.headerView = headerView;
        }
        return result;
    }

    public static ParkingLot findById(Long id) {
        return JPA.em().find(ParkingLot.class, id);
    }

    public static ParkingLot findByAltKey(String altKey) {
        return JPA.findByAltKey(ParkingLot.class, altKey);
    }

    public static ParkingLot findByDocNum(Long docNum) {
        ParkingLot parkingLot = JPA.queryFirst("SELECT p FROM ParkingLot p WHERE p.idDocNum = ?1", docNum);
        if (parkingLot == null) {
            parkingLot = new ParkingLot();
            parkingLot.setIdDocNum(docNum);
        }
        return parkingLot;
    }
    //</editor-fold>
}
