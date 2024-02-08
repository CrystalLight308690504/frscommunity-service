package priv.crystallightghost.frscommunity.entity.blog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import priv.crystallightghost.frscommunity.entity.skatingtype.SkatingType;
import priv.crystallightghost.frscommunity.entity.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Table(name = "blog", schema = "frscommunity_blog", catalog = "")
@Data
public class Blog {
    @Id
    @Column(name = "blog_id")
    private long blogId;

    @OneToOne
    @JoinColumn(name = "skating_type_id")
    private SkatingType skatingType;


    @Basic
    @Column(name = "blog_title")
    private String blogTitle;
    @Basic
    @Column(name = "is_showed")
    private int isShowed;
    @Basic
    @Column(name = "content")
    private String content;
    @OneToOne
    @JoinColumn(name = "next_content_id")
    private Blog nextContent;
    @Basic
    @Column(name = "right_id")
    private Long rightId;
    @Basic
    @Column(name = "created_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createdTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private BlogCategory blogCategory;
}
