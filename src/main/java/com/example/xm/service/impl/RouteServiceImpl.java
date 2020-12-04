package com.example.xm.service.impl;

import com.example.xm.bean.Favorite;
import com.example.xm.bean.PageBean;
import com.example.xm.bean.Route;
import com.example.xm.mapper.RouteMapper;
import com.example.xm.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    RouteMapper routeMapper;
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {

        PageBean<Route> pb =new PageBean<Route>();
        //设置当前页面
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        System.out.println(rname);

        int totalCount=routeMapper.finTotalCount(cid,rname);
        pb.setTotalCount(totalCount);

        //设置当前页显示的数据集合
        int start=(currentPage-1)*pageSize;//开始的记录数
        List<Route> routes = routeMapper.finByPage(cid, start, pageSize,rname);


        pb.setList(routes);

        //设置总页数=总记录数/每页显示条数

        int totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);




        return pb;

    }

    @Override
    public Route particulars(int rid, int sid) {

        Route particulars = routeMapper.particulars(rid, sid);
        //收藏次数
        int count =routeMapper.findCountByRid(particulars.getRid());
        particulars.setCount(count);
        return particulars;


    }

    @Override
    public boolean isFavorite(int rid, int uid) {
        Favorite favorite = routeMapper.isFavorite(rid, uid);
        if(favorite==null){
            return false ;
        }else{
            return true ;
        }

    }

}
