package priv.crystallightghost.frscommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.crystallightghost.frscommunity.pojo.blog.Blog;
import priv.crystallightghost.frscommunity.respond.Result;
import priv.crystallightghost.frscommunity.service.BlogService;


/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@CrossOrigin
@RestController
@RequestMapping(value ="/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody Blog blog) {
       return blogService.save(blog);
    }
}
