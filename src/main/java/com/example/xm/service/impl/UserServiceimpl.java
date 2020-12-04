package com.example.xm.service.impl;

import com.example.xm.bean.User;
import com.example.xm.mapper.UserMapper;
import com.example.xm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
   private UserMapper mapper;
    @Override
    public void addregister(User user) throws Exception {
        mapper.addregister(user);
    }



    //激活用户
    @Override
    public boolean active(String code) {
      User user=  mapper.finByCode(code);

        user.setSTATUS("Y");
       // System.out.println(user.getSTATUS());
      if(user!=null){
          //2. 调用dao层激活方法
          mapper.updateStatus(user);
          return true;
      }else {
          return false;
    }

    }
///登录
    @Override
    public User log(String username, String PASSWORD) {
        return mapper.log(username,PASSWORD);
    }
}
