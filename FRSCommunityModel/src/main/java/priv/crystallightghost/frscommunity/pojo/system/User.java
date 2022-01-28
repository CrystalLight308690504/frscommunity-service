package priv.crystallightghost.frscommunity.pojo.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User  implements Serializable {

    @Id
    @Column(name = "user_id")
    private long userId;

    @Getter
    @Setter
    @Transient
    private String sessionId;

    @Getter
    @Setter
    @Transient
    private String oldPassword;

    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber ;
    @Basic
    @Column(name = "profile")
    private String profile;
    @Basic
    @Column(name = "introduce")
    private String introduce;
    @Basic
    @Column(name = "credit")
    private Long credit;
    @Basic
    @Column(name = "gender")
    private Boolean gender;
    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;
    @Basic
    @Column(name = "last_login_time")
    private Timestamp lastLoginTime;
    @Basic
    @Column(name = "login_time")
    private Timestamp loginTime;
    @Basic
    @Column(name = "profession")
    private String profession;
    @Basic
    @Column(name = "description")
    private byte[] description;
    @Basic
    @Column(name = "address_ip")
    private String addressIp;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    /**
     * JsonIgnore
     * : 忽略json转化
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Set<Role> roles = new HashSet<Role>();//用户与角色   多对多

}
