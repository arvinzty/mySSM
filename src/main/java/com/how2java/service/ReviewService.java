package com.how2java.service;

import com.how2java.pojo.Review;

import java.util.List;

public interface ReviewService {
    void add(Review review);
    void delete(int id);
    void updata(Review review);
    List<Review> list(int pid);
    Review getOne(int id);
    int getCount(int pid);
}
