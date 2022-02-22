package priv.crystallightghost.frscommunity.pojo.blog;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Date 2022/2/22
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Table(name = "blog_criticism", schema = "frscommunity", catalog = "")
public class BlogCriticism {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "criticism_id")
    private long criticismId;
    @Basic
    @Column(name = "blog_id")
    private Long blogId;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "next_content_id")
    private Long nextContentId;
    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;

    public long getCriticismId() {
        return criticismId;
    }

    public void setCriticismId(long criticismId) {
        this.criticismId = criticismId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getNextContentId() {
        return nextContentId;
    }

    public void setNextContentId(Long nextContentId) {
        this.nextContentId = nextContentId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogCriticism that = (BlogCriticism) o;
        return criticismId == that.criticismId && Objects.equals(blogId, that.blogId) && Objects.equals(userId, that.userId) && Objects.equals(content, that.content) && Objects.equals(nextContentId, that.nextContentId) && Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(criticismId, blogId, userId, content, nextContentId, createdTime);
    }
}
