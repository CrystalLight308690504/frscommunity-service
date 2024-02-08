package priv.crystallightghost.frscommunity.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Data
@Table(name = "permission")
public class Permission {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "permission_id")
    private long permissionId;
    @Basic
    @Column(name = "permission_name")
    private String permissionName;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "type")
    private Byte type;
    @Basic
    @Column(name = "type_id")
    private Long typeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return permissionId == that.permissionId && Objects.equals(permissionName, that.permissionName) && Objects.equals(description, that.description) && Objects.equals(code, that.code) && Objects.equals(type, that.type) && Objects.equals(typeId, that.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, permissionName, description, code, type, typeId);
    }
}
