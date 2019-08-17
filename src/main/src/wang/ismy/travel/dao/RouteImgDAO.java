package wang.ismy.travel.dao;

import wang.ismy.travel.entity.RouteImg;

import java.util.List;

public interface RouteImgDAO {

    List<RouteImg> findByRid(Integer rid);
}
