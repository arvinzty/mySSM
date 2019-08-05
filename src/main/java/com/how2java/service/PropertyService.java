package com.how2java.service;

import com.how2java.pojo.Property;

import java.util.List;

public interface PropertyService {
    void add(Property property);
    void delete(int id);
    void updata(Property property);
    List<Property> list(int cid);
    Property getOne(int id);
}
