package com.how2java.service.impl;

import com.how2java.mapper.PropertyValueMapper;
import com.how2java.pojo.Property;
import com.how2java.pojo.PropertyValue;
import com.how2java.pojo.PropertyValueExample;
import com.how2java.service.ProductService;
import com.how2java.service.ProductValueService;
import com.how2java.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductValueServiceImpl implements ProductValueService {
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    ProductService productService;
    @Autowired
    PropertyService propertyService;
    @Override
    public void init(int pid) {
        int cid=productService.getOne(pid).getCid();
        List<Property> properties=propertyService.list(cid);
        for(Property tem:properties){
            PropertyValue propertyValue=this.getOne(pid,tem.getId());
            if(null==propertyValue){
                propertyValue=new PropertyValue();
                propertyValue.setPid(pid);
                propertyValue.setPtid(tem.getId());
                propertyValueMapper.insert(propertyValue);
            }

        }
    }

    @Override
    public List<PropertyValue> list(int pid) {
        PropertyValueExample example=new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> propertyValues=propertyValueMapper.selectByExample(example);
        for(PropertyValue tem:propertyValues){
            Property property=propertyService.getOne(tem.getPtid());
            tem.setProperty(property);
        }
        return propertyValues;
    }

    @Override
    public PropertyValue getOne(int pid, int ptid) {
        PropertyValueExample example=new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid).andPtidEqualTo(ptid);
        List<PropertyValue> propertyValues=propertyValueMapper.selectByExample(example);
        if(propertyValues.isEmpty())
            return null;
        return propertyValues.get(0);
    }

    @Override
    public void upData(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }
}
