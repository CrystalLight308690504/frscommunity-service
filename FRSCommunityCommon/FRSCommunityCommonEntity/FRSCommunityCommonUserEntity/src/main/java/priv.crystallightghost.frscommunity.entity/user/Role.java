package priv.crystallightghost.frscommunity.entity.user;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Data
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    private long roleId;
    @Basic
    @Column(name = "orle_name")
    private String roleName;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name = "created_time")
    private Timestamp createdTime;


}
