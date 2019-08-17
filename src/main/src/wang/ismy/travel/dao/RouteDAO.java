package wang.ismy.travel.dao;

import wang.ismy.travel.entity.Route;

import java.util.List;

public interface RouteDAO {

    int count(Integer cid);

    List<Route> findByPage(Integer cid,Integer start,Integer pageSize);
}
