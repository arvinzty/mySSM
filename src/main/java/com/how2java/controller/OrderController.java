package com.how2java.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.mapper.OrderMapper;
import com.how2java.pojo.Order;
import com.how2java.service.OrderItemService;
import com.how2java.service.OrderService;
import com.how2java.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @RequestMapping("/admin_order_list")
    public String list(Page page, Model model){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Order> orders=orderService.list();
        int total= (int) new PageInfo<>(orders).getTotal();
        page.setTotal(total);
        orderItemService.fill(orders);
        model.addAttribute("os",orders);
        model.addAttribute("page",page);
        return "admin/listOrder";
    }
    @RequestMapping("/admin_order_delivery")
    private String delivery(Order order){
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return "redirect:/admin_order_list";
    }
}
