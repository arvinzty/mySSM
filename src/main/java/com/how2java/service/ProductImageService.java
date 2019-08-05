package com.how2java.service;

import com.how2java.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    String single="type_single";
    String detail="type_detail";
     void add(ProductImage productImageService);
     void delete(int id);
     void upData(ProductImage productImage);
     List<ProductImage> list(int pid,String string);
     ProductImage getOne(int id);
}
