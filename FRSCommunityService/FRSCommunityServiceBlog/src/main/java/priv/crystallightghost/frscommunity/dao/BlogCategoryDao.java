package priv.crystallightghost.frscommunity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.entity.blog.BlogCategory;
import priv.crystallightghost.frscommunity.entity.user.User;


import java.util.List;

/**
 * @Date 2022/2/4
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public interface BlogCategoryDao extends JpaRepository<BlogCategory,Long>, JpaSpecificationExecutor<BlogCategory> {

        List<BlogCategory> findBlogCategoryByUser(User user);
}
