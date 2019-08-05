package com.how2java.service.impl;

import java.util.List;

import com.how2java.mapper.CategoryMapper;


import com.how2java.pojo.Category;
import com.how2java.pojo.CategoryExample;
import com.how2java.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.service.CategoryService;

@Service
public class CategoryServiceImpl  implements CategoryService{
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> list() {
        CategoryExample example=new CategoryExample();
        example.setOrderByClause("id desc");
        return categoryMapper.selectByExample(example);
    }

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category get(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }


    @Override
    public void updata(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }
}
