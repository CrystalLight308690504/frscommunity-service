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
@Table(name = "api")
public class Api {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "api_id")
    private long apiId;
    @Basic
    @Column(name = "method_name")
    private String methodName;
    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "code")
    private String code;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Api api = (Api) o;
        return apiId == api.apiId && Objects.equals(methodName, api.methodName) && Objects.equals(url, api.url) && Objects.equals(code, api.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiId, methodName, url, code);
    }
}
