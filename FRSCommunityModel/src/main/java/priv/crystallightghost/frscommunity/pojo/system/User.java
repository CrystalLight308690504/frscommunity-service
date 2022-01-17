package priv.crystallightghost.frscommunity.pojo.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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
    @Column(name = "user_name")
    private String userName = "";
    @Basic
    @Column(name = "email")
    private String email = "";
    @Basic
    @Column(name = "password")
    private String password="";
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber = "";
    @OneToOne
    @JoinColumn(name = "user_info_id")
    private UserInformation userInfo ;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userInfo=" + userInfo +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, email, password, phoneNumber);
    }
}
