package com.how2java.service.impl;

import com.how2java.mapper.UserMapper;
import com.how2java.pojo.User;
import com.how2java.pojo.UserExample;
import com.how2java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceController implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public void upData(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<User> list() {
        UserExample example=new UserExample();
        example.setOrderByClause("id desc");
        return userMapper.selectByExample(example);
    }

    @Override
    public User getOne(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean isExist(String name) {
        UserExample example=new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> users=userMapper.selectByExample(example);
        if (users.isEmpty())
            return true;
        return false;
    }
}
