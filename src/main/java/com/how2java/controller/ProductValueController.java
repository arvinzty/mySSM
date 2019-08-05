package com.how2java.controller;

import com.how2java.pojo.Product;
import com.how2java.pojo.Property;
import com.how2java.pojo.PropertyValue;
import com.how2java.service.ProductService;
import com.how2java.service.ProductValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductValueController {
    @Autowired
    ProductValueService productValueService;
    @Autowired
    ProductService productService;

    @RequestMapping("/admin_propertyValue_edit")
    public String index(int pid, Model model){
        productValueService.init(pid);
        List<PropertyValue> pvs=productValueService.list(pid);
        Product p=productService.getOne(pid);
        model.addAttribute("pvs",pvs);
        model.addAttribute("p",p);
        return "/admin/editPropertyValue";
    }

    @RequestMapping("/admin_propertyValue_update")
    @ResponseBody
    public String updata(PropertyValue propertyValue){
        productValueService.upData(propertyValue);
        return "success";
    }
}
