package priv.crystallightghost.frscommunity.pojo.blog;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Table(name = "blog_click_applause", schema = "frscommunity", catalog = "")
@Data
public class BlogApplause {
    @Id
    @Column(name = "click_applause_id")
    private long clickApplauseId;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "blog_id")
    private Long blogId;
    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogApplause that = (BlogApplause) o;
        return clickApplauseId == that.clickApplauseId && Objects.equals(userId, that.userId) && Objects.equals(blogId, that.blogId) && Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, blogId, createdTime, clickApplauseId);
    }
}
