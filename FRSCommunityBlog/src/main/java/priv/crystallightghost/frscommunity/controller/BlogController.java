package priv.crystallightghost.frscommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.crystallightghost.frscommunity.pojo.blog.Blog;
import priv.crystallightghost.frscommunity.pojo.blog.BlogCategory;
import priv.crystallightghost.frscommunity.respond.Result;
import priv.crystallightghost.frscommunity.service.BlogService;
import priv.crystallightghost.frscommunity.until.FRSCIdWorker;


/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/blog")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    FRSCIdWorker idWorker;

    @RequestMapping(value = "/findBlogsByUserId/{userId}", method = RequestMethod.GET)
    public Result findBlogs(@PathVariable("userId") long userId) {
        return blogService.findBlogsByUserId(userId);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deleteBlog(@RequestBody Blog blog) {
        return blogService.deleteBlog(blog);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Blog blog) {
        return blogService.save(blog);
    }

    @RequestMapping(value = "/modifyBlog", method = RequestMethod.PUT)
    public Result modifyBlog(@RequestBody Blog blog) {
        return blogService.modifyBlog(blog);
    }

    @RequestMapping(value = "/findBlogsByUserAndCategory/{userId}/{categoryId}", method = RequestMethod.GET)
    public Result findBlogsByUserAndCategory(@PathVariable("userId") long userId, @PathVariable("categoryId") long categoryId) {
        return blogService.findBlogsByUserAndCategory(userId, categoryId);

    }
    @RequestMapping(value = "/findBlogCategories/{userId}", method = RequestMethod.GET)
    public Result findBlogCategories(@PathVariable("userId") long userId) {
        return blogService.findBlogCategories(userId);
    }

    @RequestMapping(value = "/addBlogCategory", method = RequestMethod.POST)
    public Result addBlogCategory(@RequestBody BlogCategory blogCategory) {
        blogCategory.setCategoryId(idWorker.nextId());
        return blogService.addBlogCategory(blogCategory);
    }
}
