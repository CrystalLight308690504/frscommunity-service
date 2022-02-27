package priv.crystallightghost.frscommunity.pojo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Date 2022/2/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Data
@Table(name = "user_follower")
public class UserFollowerEntity {

    @Id
    private long followerId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "user_followed_id")
    private User userFollowed;

    @Basic
    @Column(name = "created_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createdTime;
}
