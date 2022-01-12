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
@Table(name = "button")
public class Button {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "btn_id")
    private long btnId;
    @Basic
    @Column(name = "btn_name")
    private String btnName;
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
        Button button = (Button) o;
        return btnId == button.btnId && Objects.equals(btnName, button.btnName) && Objects.equals(description, button.description) && Objects.equals(code, button.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(btnId, btnName, description, code);
    }
}
