package priv.crystallightghost.frscommunity.pojo.system;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Table(name = "menu")
public class Menu {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "menu_id")
    private long menuId;
    @Basic
    @Column(name = "parent_id")
    private Long parentId;
    @Basic
    @Column(name = "menu_name")
    private String menuName;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "code")
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return menuId == menu.menuId && Objects.equals(parentId, menu.parentId) && Objects.equals(menuName, menu.menuName) && Objects.equals(description, menu.description) && Objects.equals(code, menu.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, parentId, menuName, description, code);
    }
}
