package com.example.xm.mapper;

import com.example.xm.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//@Repository
@Mapper//可以被spring boot扫描到
public interface UserMapper {
    void addregister(User user) throws Exception;
    void  updateStatus(User user);

    User log(@Param("username") String username, @Param("PASSWORD") String PASSWORD);
    User finByCode(String code);
}
