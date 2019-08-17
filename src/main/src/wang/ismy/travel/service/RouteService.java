package wang.ismy.travel.service;

import wang.ismy.travel.entity.PageBean;
import wang.ismy.travel.entity.Route;

public interface RouteService {

    PageBean<Route> list(Integer cid,Integer currentPage,Integer pageSize);
}
