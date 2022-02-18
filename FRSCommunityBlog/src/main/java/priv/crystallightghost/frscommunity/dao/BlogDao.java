package priv.crystallightghost.frscommunity.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.pojo.blog.Blog;
import priv.crystallightghost.frscommunity.pojo.blog.BlogCategory;
import priv.crystallightghost.frscommunity.pojo.skatingtype.SkatingType;
import priv.crystallightghost.frscommunity.pojo.system.User;

import java.util.List;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public interface BlogDao extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    List<Blog> findBlogsByUser(User user);
    List<Blog> findBlogsBySkatingType(SkatingType skatingType);
    List<Blog> findBlogsByUserAndBlogCategory(User user, BlogCategory category);
    Slice<Blog> findBlogsBySkatingType(SkatingType skatingType, Pageable pageable);
    Slice<Blog> findByBlogTitleContainingOrContentContaining(String likePattern, String likePattern2,Pageable pageable);
}
