package com.how2java.service.impl;

import com.how2java.mapper.ProductImageMapper;
import com.how2java.pojo.ProductImage;
import com.how2java.pojo.ProductImageExample;
import com.how2java.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageMapper productImageMapper;
    @Override
    public void add(ProductImage productImageService) {
        productImageMapper.insert(productImageService);
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void upData(ProductImage productImage) {
        productImageMapper.updateByPrimaryKey(productImage);
    }

    @Override
    public List<ProductImage> list(int pid, String string) {
        ProductImageExample example=new ProductImageExample();
        example.createCriteria()
                .andPidEqualTo(pid)
                .andTypeEqualTo(string);
        example.setOrderByClause("id desc");
        return productImageMapper.selectByExample(example);
    }

    @Override
    public ProductImage getOne(int id) {
        return productImageMapper.selectByPrimaryKey(id);
    }
}
