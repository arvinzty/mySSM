package com.how2java.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.pojo.Property;
import com.how2java.service.CategoryService;
import com.how2java.service.PropertyService;
import com.how2java.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/admin_property_list")
    public String list(int cid, Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> properties=propertyService.list(cid);
        int tatol= (int) new PageInfo<>(properties).getTotal();
        page.setTotal(tatol);
        page.setParam("&cid="+cid);
        model.addAttribute("ps",properties);
        model.addAttribute("c",categoryService.get(cid));
        model.addAttribute("page",page);
        return "admin/listProperty";
    }

    @RequestMapping("/admin_property_delete")
    public String delete(int id){
        int cid=propertyService.getOne(id).getCid();
        propertyService.delete(id);
        return "redirect:/admin_property_list?cid="+cid;
    }

    @RequestMapping("/admin_property_add")
    public String add(Property property){
        propertyService.add(property);
        return "redirect:/admin_property_list?cid="+property.getCid();
    }

    @RequestMapping("/admin_property_edit")
    public String edit(int id,Model model){
        model.addAttribute("p", propertyService.getOne(id));
        return "admin/editProperty";
    }

    @RequestMapping("/admin_property_update")
    public String updata(Property property){
        propertyService.updata(property);
        return "redirect:/admin_property_list?cid="+property.getCid();
    }


}
