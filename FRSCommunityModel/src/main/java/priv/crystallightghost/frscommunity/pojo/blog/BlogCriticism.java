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
@Table(name = "blog_criticism", schema = "frscommunity", catalog = "")
@Data
public class BlogCriticism {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "criticism_id")
    private String criticismId;
    @Basic
    @Column(name = "blog_id")
    private Long blogId;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "answer_to_criticism_id")
    private String answerToCriticismId;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "next_content_id")
    private Long nextContentId;
    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;
}
