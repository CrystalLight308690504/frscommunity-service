package priv.crystallightghost.frscommunity.dao;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.entity.blog.Blog;
import priv.crystallightghost.frscommunity.entity.blog.BlogCollection;


public interface BlogCollectionDao extends JpaRepository<BlogCollection, Long>, JpaSpecificationExecutor<BlogCollection> {
    BlogCollection findByUserIdAndBlog(long userId, Blog blog);

    Slice<BlogCollection> findByUserId(long userId, Pageable pageable);
}
