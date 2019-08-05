package com.how2java.service;

import com.how2java.pojo.Order;
import com.how2java.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    void add(OrderItem orderItem);
    void delete(int id);
    void upDtat(OrderItem orderItem);
    OrderItem getOne(int id);
    List<OrderItem> list();

    void fill(List<Order> os );
    void fill(Order o);
    int getSaleCount(int  pid);

}
