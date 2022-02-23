package priv.crystallightghost.frscommunity.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.pojo.blog.BlogClickApplause;

public interface BlogClickApplauseDao extends JpaRepository<BlogClickApplause, Long>, JpaSpecificationExecutor<BlogClickApplause> {
    BlogClickApplause findByUserIdAndBlogId (long userId, long blogId);
}
