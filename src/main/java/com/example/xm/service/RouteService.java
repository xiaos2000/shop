package com.example.xm.service;

import com.example.xm.bean.PageBean;
import com.example.xm.bean.Route;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RouteService {

    public PageBean<Route> pageQuery(int cid,int currentPage, int pageSize, String rname);

    Route particulars(int rid, int sid);

    boolean isFavorite(int rid, int uid);
}
