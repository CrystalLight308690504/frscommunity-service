package priv.crystallightghost.frscommunity.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.pojo.blog.Blog;
import priv.crystallightghost.frscommunity.pojo.blog.BlogApplause;

public interface BlogApplauseDao extends JpaRepository<BlogApplause, Long>, JpaSpecificationExecutor<BlogApplause> {
    BlogApplause findByUserIdAndBlog (long userId, Blog blogId);
    long countByUserOfBlogId (long userId);
}
