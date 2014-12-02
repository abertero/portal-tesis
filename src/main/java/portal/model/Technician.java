package portal.model;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import portal.config.JPA;
import portal.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
public class Technician extends BaseEntity implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Technician.class);
    private String code;
    private String firstName;
    private String lastName;


    //<editor-fold desc="Getters and setters">

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    //</editor-fold>

    @Transient
    public String getFullName() {
        return StringUtils.defaultString(firstName) + " " + StringUtils.defaultString(lastName);
    }

    @Override
    protected String attributes() {
        return super.attributes() + ", firstName: " + firstName + ", lastName: " + lastName;
    }

    //<editor-fold desc="Static methods">
    public static Technician dummy() {
        Technician dummy = new Technician();
        dummy.setFirstName("name_" + UUID.randomUUID().toString());
        dummy.setLastName("last_name_" + UUID.randomUUID().toString());
        return dummy;
    }

    public boolean save() {
        try {
            JPA.em().persist(this);
            logger.info("Added Technician " + toString());
            return true;
        } catch (Exception e) {
            logger.error("Excepcion creando Technician", e);
        }
        return false;
    }

    public void getData(Technician technician) {
        this.firstName = technician.getFirstName();
        this.lastName = technician.getLastName();
        this.code = technician.getCode();
    }


    public void validateTechnicianForm(BindingResult errors) {

    }

    public static List<Technician> findAll() {
        return JPA.findAll(Technician.class);
    }

    public static Technician findById(Long id) {
        return JPA.em().find(Technician.class, id);
    }

    public static Technician findByAltKey(String altKey) {
        return JPA.findByAltKey(Technician.class, altKey);
    }

    public static List<Technician> findbyIds(Long[] idTechnicians) {
        return JPA.queryNamed("SELECT t FROM Technician t WHERE t.id IN :ids", "ids", Arrays.asList(idTechnicians));
    }

    public static List<Technician> findByCode(String code) {
        return JPA.query("SELECT t FROM Technician t WHERE t.code LIKE ?1", code != null ? "%" + code + "%" : null);
    }
}
