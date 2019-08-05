package com.how2java.service.impl;

import com.how2java.mapper.PropertyMapper;
import com.how2java.pojo.Property;
import com.how2java.pojo.PropertyExample;
import com.how2java.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper propertyMapper;

    @Override
    public void add(Property property) {
        propertyMapper.insert(property);
    }

    @Override
    public void delete(int id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updata(Property property) {
        propertyMapper.updateByPrimaryKey(property);
    }

    @Override
    public List<Property> list(int cid) {
        PropertyExample example=new PropertyExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        return propertyMapper.selectByExample(example);
    }

    @Override
    public Property getOne(int id) {
        return propertyMapper.selectByPrimaryKey(id);
    }
}
