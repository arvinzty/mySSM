package com.how2java.controller;

import com.how2java.pojo.*;
import com.how2java.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ProductValueService productValueService;

    @RequestMapping("/forehome")
    public String index(Model model){
        List<Category> categories=categoryService.list();
        productService.fillByRow(categories);
        model.addAttribute("cs",categories);
        return "fore/home";
    }

    @RequestMapping("/foreregister")
    public String register(User user, Model model){
        String name=user.getName();
        name= HtmlUtils.htmlEscape(name);
        if ( userService.isExist(name)){
            userService.add(user);
            return "redirect:registerSuccessPage";
        }
        String msg="用户名已存在，不能使用";
        model.addAttribute("msg",msg);
        model.addAttribute("user",null);
        return "/fore/register";

    }

    @RequestMapping("/forelogout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/forehome";
    }

    @RequestMapping("foreproduct")
    public String product( int pid, Model model) {
        Product p = productService.getOne(pid);

        List<ProductImage> productSingleImages = productImageService.list(p.getId(), ProductImageService.single);
        List<ProductImage> productDetailImages = productImageService.list(p.getId(), ProductImageService.detail);
        p.setProductSingleImages(productSingleImages);
        p.setProductDetailImages(productDetailImages);

        List<PropertyValue> pvs = productValueService.list(p.getId());
        List<Review> reviews = reviewService.list(p.getId());
        productService.setSaleAndReviewNumber(p);
        model.addAttribute("reviews", reviews);
        model.addAttribute("p", p);
        model.addAttribute("pvs", pvs);
        return "fore/product";
    }

}
