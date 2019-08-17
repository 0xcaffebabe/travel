package wang.ismy.travel.dao;

import wang.ismy.travel.entity.Route;

import java.util.List;

public interface RouteDAO {

    int count(Integer cid, String kw);

    List<Route> findByPage(Integer cid, Integer start, Integer pageSize, String kw);

    Route findOne(Integer rid);
}
