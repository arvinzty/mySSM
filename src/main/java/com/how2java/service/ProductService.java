package com.how2java.service;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import com.how2java.pojo.ProductImage;

import java.util.List;

public interface ProductService {

    void add(Product product);
    void delete(int id);
    void updata(Product product);
    List<Product> list(int cid);
    Product getOne(int pid);
    void setFirstProductImage(Product productImage);
    void fill(Category category);
    void fill(List<Category> categories);
    void fillByRow(List<Category> categoryByRow);
    void setSaleAndReviewNumber(Product p);
    void setSaleAndReviewNumber(List<Product> ps);
}
