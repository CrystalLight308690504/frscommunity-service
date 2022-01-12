package priv.crystallightghost.frscommunity.pojo.system;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private long roleId;
    @Basic
    @Column(name = "orle_name")
    private String orleName;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;

    @ManyToMany
    @JoinTable (name = "role_permission", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "permission_id")})
    Set<Permission> permissions;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId && Objects.equals(orleName, role.orleName) && Objects.equals(description, role.description) && Objects.equals(createdTime, role.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, orleName, description, createdTime);
    }
}
