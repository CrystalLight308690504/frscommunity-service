package priv.crystallightghost.frscommunity.pojo.system;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Data
@Table(name = "user_information")
public class UserInformation {

    @Id
    @Column(name = "user_info_id")
    private long userInfoId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInformation that = (UserInformation) o;
        return userInfoId == that.userInfoId && Objects.equals(profile, that.profile) && Objects.equals(introduce, that.introduce) && Objects.equals(credit, that.credit) && Objects.equals(gender, that.gender) && Objects.equals(createdTime, that.createdTime) && Objects.equals(lastLoginTime, that.lastLoginTime) && Objects.equals(loginTime, that.loginTime) && Objects.equals(profession, that.profession) && Arrays.equals(description, that.description) && Objects.equals(addressIp, that.addressIp);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userInfoId, profile, introduce, credit, gender, createdTime, lastLoginTime, loginTime, profession, addressIp);
        result = 31 * result + Arrays.hashCode(description);
        return result;
    }
}
