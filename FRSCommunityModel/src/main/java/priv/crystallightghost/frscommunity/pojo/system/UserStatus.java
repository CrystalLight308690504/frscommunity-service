package priv.crystallightghost.frscommunity.pojo.system;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Table(name = "user_status")
public class UserStatus {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "user_status_id")
    private Long userStatusId;
    @Basic
    @Column(name = "status_name")
    private byte[] statusName;
    @Basic
    @Column(name = "status_dated_tims")
    private String statusDatedTims;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatus that = (UserStatus) o;
        return Objects.equals(userId, that.userId) && Objects.equals(userStatusId, that.userStatusId) && Arrays.equals(statusName, that.statusName) && Objects.equals(statusDatedTims, that.statusDatedTims);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userId, userStatusId, statusDatedTims);
        result = 31 * result + Arrays.hashCode(statusName);
        return result;
    }
}
