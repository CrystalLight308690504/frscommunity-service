package priv.crystallightghost.frscommunity.pojo.blog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import priv.crystallightghost.frscommunity.pojo.system.User;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Date 2022/2/22
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Data
@Table(name = "blog_criticism")
public class BlogCriticism {

    @Id
    @Column(name = "criticism_id")
    private long criticismId;
    @Basic
    @Column(name = "blog_id")
    private Long blogId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "next_content_id")
    private Long nextContentId;
    @Basic
    @Column(name = "created_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createdTime;


}
