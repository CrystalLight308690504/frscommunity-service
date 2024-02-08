package priv.crystallightghost.frscommunity.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Date 2022/2/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Data
@Table(name = "user_follower")
public class UserFollower {

    @Id
    private long followerId;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "user_followed_id")
    private Long userFollowedId;

    @Basic
    @Column(name = "created_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createdTime;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFollower that = (UserFollower) o;
        return followerId == that.followerId && Objects.equals(userId, that.userId) && Objects.equals(userFollowedId, that.userFollowedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, userId, userFollowedId);
    }
}
