package priv.crystallightghost.frscommunity.entity.skatingtype;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Data
@Table(name = "skating_type", schema = "frscommunity", catalog = "")
public class SkatingType {

    @Id
    @Column(name = "skating_type_id")
    private long skatingTypeId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkatingType that = (SkatingType) o;
        return skatingTypeId == that.skatingTypeId && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skatingTypeId, name, description);
    }
}
