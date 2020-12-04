package com.example.xm.mapper;

import com.example.xm.bean.Route;
import com.example.xm.bean.Seller;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellerMapper {
    Seller getSeller(@Param("sid") int sid);
}
