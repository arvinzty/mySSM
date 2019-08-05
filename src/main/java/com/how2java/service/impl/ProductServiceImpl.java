package com.how2java.service.impl;

import com.how2java.mapper.CategoryMapper;
import com.how2java.mapper.ProductImageMapper;
import com.how2java.mapper.ProductMapper;
import com.how2java.pojo.*;
//import com.how2java.service.CategoryService;
import com.how2java.service.OrderItemService;
import com.how2java.service.OrderService;
import com.how2java.service.ProductImageService;
import com.how2java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updata(Product product) {
        productMapper.updateByPrimaryKey(product);
    }

    @Override
    public List<Product> list(int cid) {
        ProductExample example=new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List<Product> products=productMapper.selectByExample(example);
        setCategorys(products);
        setFirstProductImages(products);
        return products;
    }

    @Override
    public Product getOne(int pid) {
        Product product=productMapper.selectByPrimaryKey(pid);
        setCategory(product);
        setFirstProductImage(product);
        return product;
    }

    @Override
    public void setFirstProductImage(Product product) {
        int pid=product.getId();
        List<ProductImage> productImages=productImageService.list(pid,ProductImageService.single);
        if(!productImages.isEmpty()){
            product.setfirstProductImage(productImages.get(0));
        }
    }

    @Override
    public void fill(Category category) {
//        ProductExample example=new ProductExample();
//        example.createCriteria().andCidEqualTo(category.getId());
//        example.setOrderByClause("id desc");
        List<Product> products=list(category.getId());
        category.setProducts(products);
    }

    @Override
    public void fill(List<Category> categories) {
        for (Category tem:categories){
            fill(tem);
        }
    }

    @Override
    public void fillByRow(List<Category> categoryByRow) {
        fill(categoryByRow);
        int productNumberEachRow=8;
        List<List<Product>> p=new ArrayList<>();
        for (Category tem:categoryByRow){
            for (int i=0;i<tem.getProducts().size();i+=productNumberEachRow){
                int a=i+productNumberEachRow;
                a=a>tem.getProducts().size()?tem.getProducts().size():a;
                p.add(tem.getProducts().subList(i,a));
            }
        }
    }

    @Override
    public void setSaleAndReviewNumber(Product p) {
        int saleCount = orderItemService.getSaleCount(p.getId());
        p.setSaleCount(saleCount);

        int reviewCount = reviewService.getCount(p.getId());
        p.setReviewCount(reviewCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> ps) {

    }

    public void setFirstProductImages(List<Product> productImages){
        for(Product tem:productImages){
            setFirstProductImage(tem);
        }
    }

    public void setCategory(Product product){
        Category category=categoryMapper.selectByPrimaryKey(product.getCid());
        product.setCategory(category);
    }

    public void setCategorys(List<Product> products){
        for (Product product:products) {
            setCategory(product);
        }
    }
}
