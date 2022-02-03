package priv.crystallightghost.frscommunity.service;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.crystallightghost.frscommunity.dao.BlogDao;
import priv.crystallightghost.frscommunity.pojo.blog.Blog;
import priv.crystallightghost.frscommunity.respond.Result;
import priv.crystallightghost.frscommunity.until.FRSCIdWorker;

import java.sql.Timestamp;

@Service
public class BlogService {
    @Autowired
    BlogDao blogDao;
    @Autowired
    FRSCIdWorker idWorker;

    public Result save(Blog blog) {
        blog.setBlogId(idWorker.nextId());
        blog.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        blogDao.save(blog);
        return Result.SUCCESS();
    }
}
