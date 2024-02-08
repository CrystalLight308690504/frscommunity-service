package priv.crystallightghost.frscommunity.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.crystallightghost.frscommunity.entity.blog.*;
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

    @RequestMapping(value = "/addBlogCategory", method = RequestMethod.POST)
    public Result addBlogCategory(@RequestBody BlogCategory blogCategory) {
        return blogService.addBlogCategory(blogCategory);
    }

    @RequestMapping(value = "/cancelCollectionBlog", method = RequestMethod.DELETE)
    public Result cancelCollectionBlog(@RequestBody BlogCollection blogCollection) {
        return blogService.cancelCollectionBlog(blogCollection);
    }
    @RequestMapping(value = "/cancelApplauseBlog", method = RequestMethod.DELETE)
    public Result cancelApplauseBlog(@RequestBody BlogApplause blogApplause) {
        return blogService.cancelApplauseBlog(blogApplause);
    }

    @RequestMapping(value = "/applauseBlog", method = RequestMethod.POST)
    public Result clickApplauseBlog(@RequestBody BlogApplause blogApplause) {
        return blogService.clickApplauseBlog(blogApplause);
    }

    @RequestMapping(value = "/countBlogsByCategoryId/{categoryId}", method = RequestMethod.GET)
    public Result countBlogsByUserId( @PathVariable("categoryId") long categoryId) {
        return blogService.countBlogsByUserIdAndCategoryId(categoryId);
    }

    @RequiresRoles(value = {"superAdm","blogAdm"},logical = Logical.OR)
    @RequestMapping(value = "/changBlogIsShowed/{blogId}/{isShowed}", method = RequestMethod.POST)
    public Result changBlogIsShowed(@PathVariable("blogId") long blogId, @PathVariable("isShowed") boolean isShowed) {
        return blogService.changBlogIsShowed(blogId, isShowed);
    }

    @RequestMapping(value = "/collectionBlog", method = RequestMethod.POST)
    public Result collectionBlog(@RequestBody BlogCollection blogCollection) {
        return blogService.collectionBlog(blogCollection);
    }

    @RequestMapping(value = "/countBlogs/{userId}", method = RequestMethod.GET)
    public Result countBlogs(@PathVariable("userId") long userId) {
        return blogService.countBlogs(userId);
    }

    @RequestMapping(value = "/countBlogApplause/{userId}", method = RequestMethod.GET)
    public Result countBlogApplause(@PathVariable("userId") long userId) {
        return blogService.countBlogApplause(userId);
    }

    @RequestMapping(value = "/criticiseBlog", method = RequestMethod.POST)
    public Result criticiseBlog(@RequestBody BlogCriticism blogCriticism) {
        return blogService.criticiseBlog(blogCriticism);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result deleteBlog(@RequestBody Blog blog) {
        return blogService.deleteBlog(blog);
    }

    @RequestMapping(value = "/deleteBlogCriticism", method = RequestMethod.DELETE)
    public Result deleteBlogCriticism(@RequestBody BlogCriticism blogCriticism) {
        return blogService.deleteBlogCriticism(blogCriticism);
    }

    @RequestMapping(value = "/deleteBlogCategory", method = RequestMethod.PUT)
    public Result deleteBlogCategory(@RequestBody BlogCategory blogCategory) {
        return blogService.deleteBlogCategory(blogCategory);
    }

     @RequestMapping(value = "/isApplauseBlog/{userId}/{blogId}", method = RequestMethod.GET)
    public Result isApplauseBlog(@PathVariable("userId") long userId, @PathVariable("blogId") long blogId) {
        return blogService.isApplauseBlog(userId, blogId);
    }


    @RequestMapping(value = "/isCollectionBlog/{userId}/{blogId}", method = RequestMethod.GET)
    public Result isCollectionBlog(@PathVariable("userId") long userId, @PathVariable("blogId") long blogId) {
        return blogService.isCollectionBlog(userId, blogId);
    }

    @RequestMapping(value = "/findBlogsBySearchKey/{searchKey}/{pagerIndex}", method = RequestMethod.GET)
    public Result findBlogsBySearchKey(@PathVariable("searchKey") String searchKey, @PathVariable("pagerIndex") int pagerIndex) {
        return blogService.findBlogBySearchKey(searchKey, pagerIndex);
    }

    @RequestMapping(value = "/findBlogs/{pagerIndex}", method = RequestMethod.GET)
    public Result findBlogs( @PathVariable("pagerIndex") int pagerIndex) {
        return blogService.findBlogs(pagerIndex);
    }

    @RequestMapping(value = "/findBlogsCollected/{userId}/{pagerIndex}", method = RequestMethod.GET)
    public Result findBlogsCollected(@PathVariable("userId") long userId, @PathVariable("pagerIndex") int pagerIndex) {
        return blogService.findBlogsCollected(userId, pagerIndex);
    }

    @RequestMapping(value = "/findBlogsBySkatingTypeId/{skatingTypeId}/{pagerIndex}", method = RequestMethod.GET)
    public Result findBlogsBySkatingTypeId(@PathVariable("skatingTypeId") long skatingTypeId, @PathVariable("pagerIndex") int pagerIndex) {
        return blogService.findBlogsBySkatingTypeId(skatingTypeId, pagerIndex);
    }

    @RequestMapping(value = "/findBlogsByUserAndCategory/{userId}/{categoryId}", method = RequestMethod.GET)
    public Result findBlogsByUserAndCategory(@PathVariable("userId") long userId, @PathVariable("categoryId") long categoryId) {
        return blogService.findBlogsByUserAndCategory(userId, categoryId);
    }

    @RequestMapping(value = "/findBlogCategories/{userId}", method = RequestMethod.GET)
    public Result findBlogCategories(@PathVariable("userId") long userId) {
        return blogService.findBlogCategories(userId);
    }

    @RequestMapping(value = "/findBlogCriticisms/{blogId}/{pageIndex}", method = RequestMethod.GET)
    public Result findBlogCriticisms(@PathVariable("blogId") long blogId, @PathVariable("pageIndex") int pageIndex) {
        return blogService.findBlogCriticisms( blogId, pageIndex);
    }

    @RequestMapping(value = "/findBlogsByUserId/{userId}", method = RequestMethod.GET)
    public Result findBlogsByUserId(@PathVariable("userId") long userId) {
        return blogService.findBlogsByUserId(userId);
    }

    @RequestMapping(value = "/modifyBlog", method = RequestMethod.PUT)
    public Result modifyBlog(@RequestBody Blog blog) {
        return blogService.modifyBlog(blog);
    }

    @RequestMapping(value = "/getSkatingType", method = RequestMethod.GET)
    public Result getSkatingType() {
        return blogService.getSkatingType();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Blog blog) {
        return blogService.save(blog);
    }

    @RequestMapping(value = "/modifyBlogCategory", method = RequestMethod.PUT)
    public Result modifyBlogCategory(@RequestBody BlogCategory blogCategory) {
        return blogService.modifyBlogCategory(blogCategory);
    }

}
