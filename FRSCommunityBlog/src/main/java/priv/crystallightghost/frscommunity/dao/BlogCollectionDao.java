package priv.crystallightghost.frscommunity.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.pojo.blog.BlogCollection;

public interface BlogCollectionDao extends JpaRepository<BlogCollection,Long>, JpaSpecificationExecutor<BlogCollection> {
    BlogCollection findByUserIdAndBlogId(long userId, long blogId);
}
