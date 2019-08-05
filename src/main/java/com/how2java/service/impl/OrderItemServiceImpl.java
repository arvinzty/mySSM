package com.how2java.service.impl;

import com.how2java.mapper.OrderItemMapper;
import com.how2java.pojo.Order;
import com.how2java.pojo.OrderItem;
import com.how2java.pojo.OrderItemExample;
import com.how2java.pojo.Product;
import com.how2java.service.OrderItemService;
import com.how2java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;
    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void upDtat(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public OrderItem getOne(int id) {
        OrderItem orderItem=orderItemMapper.selectByPrimaryKey(id);
        setProduct(orderItem);
        return orderItem;
    }

    @Override
    public List<OrderItem> list() {
        OrderItemExample example=new OrderItemExample();
        example.setOrderByClause("id desc");
        List<OrderItem> orderItems=orderItemMapper.selectByExample(example);
        return orderItems;
    }

    @Override
    public void fill(List<Order> os) {
        for (Order tem:os){
            fill(tem);
        }
    }

    @Override
    public void fill(Order o) {
        OrderItemExample example=new OrderItemExample();
        example.createCriteria().andOidEqualTo(o.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> orderItems=orderItemMapper.selectByExample(example);
        setProduct(orderItems);
        int tatol=0;
        float number=0;
        for (OrderItem tem:orderItems){
            tatol+=tem.getNumber();
            number+=tem.getNumber()*tem.getProduct().getPromotePrice();
        }
        o.setTotal(number);
        o.setTotalNumber(tatol);
        o.setOrderItems(orderItems);
    }

    @Override
    public int getSaleCount(int pid) {
        OrderItemExample example=new OrderItemExample();
        example.createCriteria().andPidEqualTo(pid);
        List<OrderItem> orderItems=orderItemMapper.selectByExample(example);
        int i=0;
        for (OrderItem tem:orderItems){
            i+=tem.getNumber();
        }
        return i;
    }

    public void setProduct(OrderItem orderItem){
        orderItem.setProduct(productService.getOne(orderItem.getPid()));
    }

    public void setProduct(List<OrderItem> orderItems){
        for (OrderItem tem:orderItems){
            setProduct(tem);
        }
    }
}
