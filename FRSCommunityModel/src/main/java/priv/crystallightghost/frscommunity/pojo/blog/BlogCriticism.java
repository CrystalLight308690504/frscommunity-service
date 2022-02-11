package priv.crystallightghost.frscommunity.pojo.blog;

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
@Table(name = "blog_criticism", schema = "frscommunity", catalog = "")
@Data
public class BlogCriticism {

    @Id
    @Column(name = "criticism_id")
    private String criticismId;
    @Basic
    @Column(name = "blog_id")
    private Long blogId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Basic
    @Column(name = "answer_to_criticism_id")
    private String answerToCriticismId;

    @Basic
    @Column(name = "content")
    private String content;

    @OneToOne
    @JoinColumn(name = "next_content_id")
    private BlogCriticism nextContent;
    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;
}
