package priv.crystallightghost.frscommunity.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.pojo.blog.BlogApplause;

public interface BlogClickApplauseDao extends JpaRepository<BlogApplause, Long>, JpaSpecificationExecutor<BlogApplause> {
    BlogApplause findByUserIdAndBlogId (long userId, long blogId);
}
