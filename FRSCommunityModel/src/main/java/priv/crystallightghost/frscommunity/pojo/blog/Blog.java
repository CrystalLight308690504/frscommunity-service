package priv.crystallightghost.frscommunity.pojo.blog;

import lombok.Data;
import priv.crystallightghost.frscommunity.pojo.skatingtype.SkatingType;
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
@Table(name = "blog")
@Data
public class Blog {
    @Id
    @Column(name = "blog_id")
    private long blogId;
    @OneToOne
    @JoinColumn(name = "skating_type_id")
    private SkatingType skatingType;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private SelfBlogCategory blogCategory;

    @Basic
    @Column(name = "blog_title")
    private String blogTitle;
    @Basic
    @Column(name = "content")
    private String content;
    @OneToOne
    @JoinColumn(name = "next_content_id")
    private Blog blog;
    @Basic
    @Column(name = "right_id")
    private Long rightId;
    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
