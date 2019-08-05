package com.how2java.service.impl;

import com.how2java.mapper.OrderMapper;
import com.how2java.pojo.Order;
import com.how2java.pojo.OrderExample;
import com.how2java.pojo.User;
import com.how2java.service.OrderService;
import com.how2java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Order getOne(int id) {
        Order order=orderMapper.selectByPrimaryKey(id);
        setUser(order);
        return order;
    }

    @Override
    public List<Order> list() {
        OrderExample example=new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> orders=orderMapper.selectByExample(example);
        setUser(orders);
        return orders;
    }

    public void setUser(Order order){
        User user=userService.getOne(order.getUid());
        order.setUser(user);
    }

    public void setUser(List<Order> orders){
        for (Order tem:orders){
            setUser(tem);
        }
    }
}
