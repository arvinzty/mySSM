package com.how2java.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import com.how2java.service.CategoryService;
import com.how2java.service.ProductService;
import com.how2java.util.Page;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/admin_product_list")
    public String list(int cid, Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> products=productService.list(cid);
        int tatol=(int) new PageInfo<>(products).getTotal();
        page.setTotal(tatol);
        page.setParam("&cid="+cid);
        Category category=categoryService.get(cid);
        model.addAttribute("ps",products);
        model.addAttribute("p",page);
        model.addAttribute("c",category);
        return "admin/listProduct";
    }

    @RequestMapping("/admin_product_add")
    public String add(Product product){
        product.setCreateDate(new Date());
        productService.add(product);
        return "redirect:/admin_product_list?cid="+product.getCid();
    }

    @RequestMapping("/admin_product_delete")
    public String delete(int id){
        int cid=productService.getOne(id).getCid();
        productService.delete(id);
        return "redirect:/admin_product_list?cid="+cid;
    }

    @RequestMapping("/admin_product_edit")
    public String edit(int id, Model model){
        Product product=productService.getOne(id);
        model.addAttribute("p",product);
        return "admin/editProducy";
    }

    @RequestMapping("/admin_product_update")
    public String upData(Product product){
        productService.updata(product);
        return "redirect:/admin_product_list?cid="+product.getCid();
    }
}
