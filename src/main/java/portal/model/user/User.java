package portal.model.user;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import portal.config.JPA;
import portal.model.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User extends BaseEntity {
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_role", referencedColumnName = "id")})
    private List<Role> roles = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    //<editor-fold desc="Getters and setters">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

//</editor-fold>

    //<editor-fold desc="Methods">


    @Override
    protected String attributes() {
        return super.attributes() + ", username: " + username + ", password: " + password +
                ", firstName: " + firstName + ", lastName: :" + lastName + ", email: " + email +
                ", phone: " + phone + ", address: " + address;
    }

    public boolean save() {
        try {
            JPA.em().persist(this);
            logger.info("Modified user " + toString());
            return true;
        } catch (Exception e) {
            logger.error("Exception modifying usuario", e);
        }
        return false;
    }

    public void validateUserForm(BindingResult errors) {

    }

    public void getData(User user) {
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.username = user.getUsername();
    }

    @Transient
    public String getFullName() {
        return StringUtils.defaultString(firstName) + " " + StringUtils.defaultString(lastName);
    }

    //</editor-fold>

    //<editor-fold desc="Static methods">
    public static User findByAltKey(String altKey) {
        return JPA.findByAltKey(User.class, altKey);
    }

    public static User findByUsername(String username) {
        return JPA.querySingle("SELECT u FROM User u WHERE u.username = ?1", username);
    }

    public static User findById(Long id) {
        return JPA.em().find(User.class, id);
    }

    public static List<User> findAll() {
        return JPA.findAll(User.class);
    }

    public static boolean validate(String username, String password) {
        String query = "SELECT COUNT(u) FROM User u WHERE u.username = ?1 AND u.password = ?2";
        Number count = JPA.querySingle(query, username, password);
        return count != null && count.longValue() == 1;
    }
    //</editor-fold>

    public boolean saveWithRoles(Long[] idRoles) {
        if (idRoles != null) {
            roles.clear();
            roles.addAll(Role.findbyIds(idRoles));
        }
        return save();
    }
}
