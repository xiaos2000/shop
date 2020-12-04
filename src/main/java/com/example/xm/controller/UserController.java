package com.example.xm.controller;

import com.example.xm.bean.User;
import com.example.xm.service.UserService;
import com.example.xm.util.MailUtils;
import com.example.xm.util.Md5Util;
import com.example.xm.util.UuidUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
@RestController
public class UserController {

@Autowired
private UserService userService;

@PostMapping("/login_register")
public  String login_register(@RequestBody User user,HttpServletResponse response,HttpServletRequest request) throws Exception {
 //校验验证码
    String check=user.getCheck();
    System.out.println(check);
    //从session中获取验证码
    HttpSession session =request.getSession();

    String checkcode_server=(String)session.getAttribute("CHECKCODE_SERVER");
    System.out.println(checkcode_server);
    session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码唯一 获取了session里的验证码立马移除掉
    if(!checkcode_server.equalsIgnoreCase(check)){
         return JSON.toJSONString("验证码错误");
    }

    try {
            //1.设置激活码
        //1.1设置激活码  唯一字符串
       user.setCODE(UuidUtil.getUuid());
       //2.设置激活状态
        user.setSTATUS("N");
        String se=user.getCODE();
        //3.激活文件发送，邮箱正文
        String content="<a href='http://localhost:8080/activeUserServlet/"+se+"'>点击激活【黑马旅游网】</a>";


        MailUtils.sendMail(user.getEmail(),content,"激活邮箱");
        userService.addregister(user);
        //如果成功就返回success
        return JSON.toJSONString("success");
    }catch (Exception e){
        e.printStackTrace();
        //如果失败就返回fail
        return JSON.toJSONString("fail");
    }

}
@GetMapping("/activeUserServlet/{CODE}")
    public void   setUserService(@PathVariable String CODE,HttpServletRequest request,HttpServletResponse response) throws IOException {


    if(CODE!=null){
         boolean flag=userService.active(CODE);

         String msg=null;
         //3.判断标记
            if(flag){
                msg="激活成功，请<a href='http://localhost:8080/login.html'>登录</a>";
                //激活成功
            }else {
                msg="激活失败，请联系管理员！";
                //激活失败
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }

@PostMapping("/e_login/{name}/{pwd}/{check}")
    public  String get_login(@PathVariable String name,@PathVariable String pwd,@PathVariable String check,HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest){


    HttpSession session =httpServletRequest.getSession();

    String servlet_check=(String)session.getAttribute("CHECKCODE_SERVER");
    if(!servlet_check.equalsIgnoreCase(check)){
        return JSON.toJSONString("验证码错误");
    }else{

        User user=  userService.log(name,pwd);
       if(user!=null){
           if(user.getSTATUS().equals("N")){

               return JSON.toJSONString("未激活");
           }else{
               session.setAttribute("session_name",user.getNAME());
               session.setAttribute("session_uid",user.getUid());
               return JSON.toJSONString("success");
           }
       }else {
           return JSON.toJSONString("fail");
       }


    }

    }

    @GetMapping("/findUserServlet")
    public  String findUserServlet(HttpServletRequest request,HttpServletResponse response){

        HttpSession session =request.getSession();
        Object name=session.getAttribute("session_name");

           return  JSON.toJSONString(name);



    }
    //退出
    @RequestMapping("/exitServlet")
    public  void exitServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");

    }
}
