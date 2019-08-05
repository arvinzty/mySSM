package com.how2java.service;

import com.how2java.pojo.PropertyValue;

import java.util.List;

public interface ProductValueService {
    void init(int pid);
    List<PropertyValue> list(int pid);
    PropertyValue getOne(int pid,int ptid);
    void upData(PropertyValue propertyValue);
}
