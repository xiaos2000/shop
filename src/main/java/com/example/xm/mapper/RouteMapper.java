package com.example.xm.mapper;

import com.example.xm.bean.Favorite;
import com.example.xm.bean.PageBean;
import com.example.xm.bean.Route;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper
public interface RouteMapper {
   List<Route> finByPage(@Param("cid") int cid ,@Param("currentPage") int currentPage,@Param("pageSize") int pageSize,@Param("rname") String rname );
   int finTotalCount(@Param("cid") int cid,@Param("rname") String rname );

  Route particulars(@Param("rid") int rid, @Param("sid") int sid);

    Favorite isFavorite(@Param("rid") int rid,@Param("uid") int uid);

    int findCountByRid(int rid);
}
