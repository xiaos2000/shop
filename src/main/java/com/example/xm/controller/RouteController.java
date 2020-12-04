package com.example.xm.controller;

import com.alibaba.fastjson.JSON;
import com.example.xm.bean.PageBean;
import com.example.xm.bean.Route;
import com.example.xm.bean.User;
import com.example.xm.service.RouteService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/Route")
public class RouteController {
    @Autowired
    RouteService routeService;

    @GetMapping("/pageQuery")
    public String pageRoute(@RequestParam(name = "currentPage", defaultValue = "1", required = false) int currentPage,
                            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
                             int cid,  String rname){
        System.out.println("dsd"+rname);
        PageBean<Route> routePageBean = routeService.pageQuery(cid, currentPage, pageSize,rname);
        System.out.println(rname);
        return JSON.toJSONString(routePageBean);

    }
    @GetMapping("/particulars")
    public String particulars(int rid,int sid){
        System.out.println(rid+""+sid);
    Route particulars = routeService.particulars(rid, sid);
        System.out.println(particulars);
        return JSON.toJSONString(particulars);
    }

    @GetMapping("/isFavorite")
    public String isFavorite (int rid, HttpServletRequest request, HttpServletResponse response){
        HttpSession session =request.getSession();
        Object user=  session.getAttribute("session_uid");
        int uid;
        if(user==null){
            uid=0;
        }else {
            uid=(int)user;
        }
        boolean favorite = routeService.isFavorite(rid, uid);
        return JSON.toJSONString(favorite);
    }
}
