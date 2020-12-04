package com.example.xm.service.impl;

import com.example.xm.bean.Category;
import com.example.xm.mapper.CategoryMapper;
import com.example.xm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }
}
