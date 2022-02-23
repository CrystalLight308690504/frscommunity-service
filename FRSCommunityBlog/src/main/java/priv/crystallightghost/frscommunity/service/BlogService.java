package priv.crystallightghost.frscommunity.service;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import priv.crystallightghost.frscommunity.dao.*;
import priv.crystallightghost.frscommunity.pojo.blog.*;
import priv.crystallightghost.frscommunity.pojo.skatingtype.SkatingType;
import priv.crystallightghost.frscommunity.pojo.system.User;
import priv.crystallightghost.frscommunity.respond.PagerResult;
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
    SkatingTypeDao skatingTypeDao;
    @Autowired
    BlogCriticismDao blogCriticismDao;
    @Autowired
    BlogCollectionDao blogCollectionDao;
    @Autowired
    BlogClickApplauseDao blogClickApplauseDao;
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

    public Result getSkatingType() {
        List<SkatingType> skatingTypes = skatingTypeDao.findAll();
        return new Result(ResultCode.SUCCESS, skatingTypes);

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
        int size = blogCategories.size();
        if (size == 0) {
            BlogCategory blogCategory = new BlogCategory();
            blogCategory.setCategoryName("默认收藏夹");
            blogCategory.setUser(user);
            blogCategory.setCategoryId(idWorker.nextId());
            blogCategory.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            blogCategoryDao.save(blogCategory);
            blogCategories.add(blogCategory);
        }
        Result result = new Result(ResultCode.SUCCESS, blogCategories);
        return result;
    }

    public Result findBlogBySearchKey(String searchKsy, int pagerIndex) {
        Sort sort = Sort.by("createdTime").descending();
        String searchLikeKey = searchKsy;
        Slice<Blog> blogsSlice = blogDao.findByBlogTitleContainingOrContentContaining(searchLikeKey, searchLikeKey, PageRequest.of(pagerIndex, 10, sort));
        PagerResult pagerResult = new PagerResult(blogsSlice.getContent(), blogsSlice.hasNext());
        return Result.SUCCESS(pagerResult);
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

    public Result findBlogsBySkatingTypeId(long skatingTypeId, int pagerIndex) {
        SkatingType skatingType = new SkatingType();
        skatingType.setSkatingTypeId(skatingTypeId);
        Sort sort = Sort.by("createdTime").descending();
        Slice<Blog> slice = blogDao.findBlogsBySkatingType(skatingType, PageRequest.of(pagerIndex, 10, sort));
        PagerResult pagerResult = new PagerResult(slice.getContent(), slice.hasNext());
        return Result.SUCCESS(pagerResult);
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

    public Result countBlogs(long userId) {
        User user = new User();
        user.setUserId(userId);
        long blogCount = blogDao.countBlogsByUser(user);
        return Result.SUCCESS(blogCount);
    }

    public Result criticiseBlog(BlogCriticism blogCriticism) {
        blogCriticism.setCriticismId(idWorker.nextId());
        blogCriticismDao.save(blogCriticism);
        return Result.SUCCESS();
    }

    public Result deleteBlogCriticism(BlogCriticism blogCriticism) {
        Optional<BlogCriticism> criticismDaoById = blogCriticismDao.findById(blogCriticism.getCriticismId());
        if (criticismDaoById.isEmpty()) {
            return Result.ERROR("不存在此评论");
        }else {
            blogCriticismDao.delete(criticismDaoById.get());
            return Result.SUCCESS();
        }
    }

    public Result findBlogCriticisms(long blogId, int pageIndex) {
        Sort s = Sort.by("createdTime").descending();
        Slice<BlogCriticism> criticisms = blogCriticismDao.findBlogCriticismsByBlogId(blogId, PageRequest.of(pageIndex, 10, s));
        PagerResult pagerResult = new PagerResult(criticisms.getContent(), criticisms.hasNext());
        return Result.SUCCESS(pagerResult);
    }

    public Result collectionBlog(BlogCollection blogCollection) {
        BlogCollection blogCollectionData = blogCollectionDao.findByUserIdAndBlogId(blogCollection.getUserId(), blogCollection.getBlogId());
        if (null != blogCollectionData) {
            return Result.ERROR("已经收藏");
        }
        blogCollection.setCollectionId(idWorker.nextId());
        blogCollection.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        blogCollectionDao.save(blogCollection);
        return Result.SUCCESS();
    }

    public Result cancelCollectionBlog(BlogCollection blogCollection) {
        BlogCollection blogCollectionData = blogCollectionDao.findByUserIdAndBlogId(blogCollection.getUserId(), blogCollection.getBlogId());
        if (null == blogCollectionData) {
            return Result.ERROR("未收藏此博客");
        }else {
            blogCollectionDao.delete(blogCollectionData);
            return Result.SUCCESS();
        }
    }

    public Result isCollectionBlog(long userId, long blogId) {
        BlogCollection blogCollectionData = blogCollectionDao.findByUserIdAndBlogId(userId,blogId);
        if (null == blogCollectionData) {
            return Result.SUCCESS(false);
        }else {
            return Result.SUCCESS(true);
        }
    }

    public Result clickApplauseBlog(BlogClickApplause blogClickApplause) {
        BlogClickApplause blogClickApplauseData = blogClickApplauseDao.findByUserIdAndBlogId(blogClickApplause.getUserId(), blogClickApplause.getBlogId());
        if (null == blogClickApplauseData) {
            blogClickApplause.setClickApplauseId(idWorker.nextId());
            blogClickApplause.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            blogClickApplauseDao.save(blogClickApplause);
            return Result.SUCCESS();
        }else {
            return Result.ERROR("已经点赞了");
        }
    }

    public Result cancelApplauseBlog(BlogClickApplause blogClickApplause) {
        BlogClickApplause blogClickApplauseData = blogClickApplauseDao.findByUserIdAndBlogId(blogClickApplause.getUserId(), blogClickApplause.getBlogId());
        if (null != blogClickApplauseData) {
            blogClickApplauseDao.delete(blogClickApplauseData);
            return Result.SUCCESS();
        }else {
            return Result.ERROR("未点赞");
        }
    }

    public Result isApplauseBlog(long userId, long blogId) {
        BlogClickApplause blogClickApplauseData = blogClickApplauseDao.findByUserIdAndBlogId(userId,blogId);
        if (null == blogClickApplauseData) {
            return Result.SUCCESS(false);
        }else {
            return Result.SUCCESS(true);
        }
    }
}
