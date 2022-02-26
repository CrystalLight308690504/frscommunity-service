package priv.crystallightghost.frscommunity.pojo.blog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import priv.crystallightghost.frscommunity.pojo.system.User;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Data
@Table(name = "blog_category")
public class BlogCategory {

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @Basic
    @Column(name = "category_name")
    private String categoryName;
    @Basic
    @Column(name = "created_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createdTime;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private BlogCategory parent;
    @Basic
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
