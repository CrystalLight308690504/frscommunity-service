package priv.crystallightghost.frscommunity.dao;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.pojo.blog.BlogCriticism;

public interface BlogCriticismDao extends JpaRepository<BlogCriticism,Long>, JpaSpecificationExecutor<BlogCriticism> {
    Slice<BlogCriticism> findBlogCriticismsByBlogId(long blogId, Pageable pageable);

}
