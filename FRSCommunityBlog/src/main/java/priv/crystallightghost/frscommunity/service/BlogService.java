package priv.crystallightghost.frscommunity.service;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import priv.crystallightghost.frscommunity.dao.BlogCategoryDao;
import priv.crystallightghost.frscommunity.dao.BlogDao;
import priv.crystallightghost.frscommunity.pojo.blog.Blog;
import priv.crystallightghost.frscommunity.pojo.blog.BlogCategory;
import priv.crystallightghost.frscommunity.pojo.system.User;
import priv.crystallightghost.frscommunity.respond.Result;
import priv.crystallightghost.frscommunity.respond.ResultCode;
import priv.crystallightghost.frscommunity.until.FRSCIdWorker;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogDao blogDao;
    @Autowired
    BlogCategoryDao blogCategoryDao;
    @Autowired
    FRSCIdWorker idWorker;
    String errorImage = "(ಥ﹏ಥ)";

    public Result deleteBlog(Blog blog) {
        Optional<Blog> byId = blogDao.findById(blog.getBlogId());
        if (byId.isEmpty()) {
            return new Result(500, errorImage + "不存在此条内容", false);
        }
        Blog blogData = byId.get();
        blogDao.delete(blogData);
        return Result.SUCCESS();
    }

    public Result findBlog(Blog blog) {

        Blog blogData = blogDao.getOne(blog.getBlogId());
        if (StringUtils.isEmpty(blogData)) {
            return new Result(500, errorImage + "不存在此条内容", false);
        } else {
            blogDao.delete(blogData);
            return Result.SUCCESS();
        }
    }

    public Result modifyBlog(Blog blog) {
        Blog blogData = blogDao.getOne(blog.getBlogId());
        if (StringUtils.isEmpty(blogData)) {
            return new Result(500, errorImage + "不存在此条内容", false);
        } else {
            blogData.setBlogTitle(blog.getBlogTitle());
            blogData.setContent(blog.getContent());
            blogData.setNextContent(blog.getNextContent());
            blogDao.save(blogData);
            return Result.SUCCESS();
        }
    }

    public Result save(Blog blog) {
        Optional<Blog> byId = blogDao.findById(blog.getBlogId());
        if (!byId.isEmpty()) {
            Blog blogData = byId.get();
            blogData.setBlogTitle(blog.getBlogTitle());
            blogData.setContent(blog.getContent());
            blogData.setSkatingType(blog.getSkatingType());
            blogData.setBlogCategory(blog.getBlogCategory());
            blogData.setNextContent(blog.getNextContent());
            blogData.setRightId(blog.getRightId());
            blogDao.save(blogData);
        } else {
            blog.setBlogId(idWorker.nextId());
            blog.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            blogDao.save(blog);
        }

        return Result.SUCCESS();
    }

    public Result findBlogsByUserId(long userId) {
        User user = new User();
        user.setUserId(userId);
        List<Blog> blogs = blogDao.findBlogsByUser(user);
        return new Result(ResultCode.SUCCESS, blogs);
    }

    public Result findBlogCategories(long userId) {
        User user = new User();
        user.setUserId(userId);
        List<BlogCategory> blogCategories = blogCategoryDao.findBlogCategoryByUser(user);
        Result result = new Result(ResultCode.SUCCESS, blogCategories);
        return result;
    }

    public Result addBlogCategory(BlogCategory blogCategory) {

        blogCategory.setCategoryId(idWorker.nextId());
        blogCategory.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        blogCategoryDao.save(blogCategory);
        return Result.SUCCESS();
    }

    public Result findBlogsByUserAndCategory(long userId, long categoryId) {
        User user = new User();
        user.setUserId(userId);

        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setCategoryId(categoryId);

        List<Blog> blogs = blogDao.findBlogsByUserAndBlogCategory(user, blogCategory);
        return new Result(ResultCode.SUCCESS, blogs);
    }

    public Result deleteBlogCategory(BlogCategory blogCategory) {
        Optional<BlogCategory> optionalBlogCategory = blogCategoryDao.findById(blogCategory.getCategoryId());
        if (optionalBlogCategory.isEmpty()) {
            return new Result(500, errorImage + "不存在此条内容", false);
        } else {
            blogCategoryDao.delete(optionalBlogCategory.get());
            return Result.SUCCESS();
        }
    }

    public Result modifyBlogCategory(BlogCategory blogCategory) {
        Optional<BlogCategory> byId = blogCategoryDao.findById(blogCategory.getCategoryId());
        if (byId.isEmpty()) {
            return new Result(500, "不存在本博文", false);
        } else {
            BlogCategory blogCategoryData = byId.get();
            blogCategoryData.setCategoryName(blogCategory.getCategoryName());
            blogCategoryData.setDescription(blogCategory.getDescription());
            blogCategoryData.setParent(blogCategoryData.getParent());
            blogCategoryDao.save(blogCategoryData);
            return Result.SUCCESS();
        }
    }
}
