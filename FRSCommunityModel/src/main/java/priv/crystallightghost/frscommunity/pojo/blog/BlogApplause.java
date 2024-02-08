package priv.crystallightghost.frscommunity.pojo.blog;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Table(name = "blog_click_applause", schema = "frscommunity_blog", catalog = "")
@Data
public class BlogApplause {
    @Id
    @Column(name = "click_applause_id")
    private long clickApplauseId;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "user_of_blog_id")
    private Long userOfBlogId;

    @OneToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;

}
