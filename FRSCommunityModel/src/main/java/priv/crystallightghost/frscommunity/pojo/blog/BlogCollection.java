package priv.crystallightghost.frscommunity.pojo.blog;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Date 2022/2/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Data
@Table(name = "blog_collection", schema = "frscommunity", catalog = "")
public class BlogCollection {

    @Id
    @Column(name = "collection_id")
    private long collectionId;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "blog_id")
    private Long blogId;
    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;
}