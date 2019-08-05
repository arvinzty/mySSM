package com.how2java.service.impl;

import com.how2java.mapper.ReviewMapper;
import com.how2java.pojo.Review;
import com.how2java.pojo.ReviewExample;
import com.how2java.pojo.User;
import com.how2java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements com.how2java.service.ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;
    @Override
    public void add(Review review) {
        reviewMapper.insertSelective(review);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updata(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public List<Review> list(int pid) {
        ReviewExample example=new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("od desc");
        List<Review> reviews=reviewMapper.selectByExample(example);
        return reviews;
    }

    @Override
    public Review getOne(int id) {
        Review review=reviewMapper.selectByPrimaryKey(id);
        return review;
    }

    @Override
    public int getCount(int pid) {
        return list(pid).size();
    }

    public void setUser(Review review){
        User user=userService.getOne(review.getUid());
        review.setUser(user);
    }

    public void setUser(List<Review> reviews){
        for (Review tem:reviews){
            setUser(tem);
        }
    }
}
