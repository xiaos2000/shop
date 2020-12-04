package com.example.xm.service;

import com.example.xm.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface UserService {
    void addregister(User user) throws Exception;


    boolean active(String code);



    User log(@Param("username") String username, @Param("PASSWORD") String PASSWORD);
}
