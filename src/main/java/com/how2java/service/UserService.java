package com.how2java.service;

import com.how2java.pojo.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void upData(User user);
    void delete(int id);
    List<User> list();
    User getOne(int id);
    Boolean isExist(String name);
}
