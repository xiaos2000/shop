package com.example.xm.mapper;

import com.example.xm.bean.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    public List<Category> findAll();
}
