package priv.crystallightghost.frscommunity.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.entity.blog.Blog;
import priv.crystallightghost.frscommunity.entity.blog.BlogCategory;
import priv.crystallightghost.frscommunity.entity.skatingtype.SkatingType;
import priv.crystallightghost.frscommunity.entity.user.User;

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
    List<Blog> findBlogsByUserAndBlogCategory(User user, BlogCategory category, Sort sort);
    void deleteByBlogCategory(BlogCategory category);
    Slice<Blog> findBlogsBySkatingTypeSkatingTypeIdAndIsShowed(long skatingTypeId,int isShowed, Pageable pageable);
    Slice<Blog> findByBlogTitleContainingOrContentContaining(String likePattern, String likePattern2,Pageable pageable);
    long countBlogsByUser(User user);
    long countBlogsByBlogCategory(BlogCategory blogCategory);
}
