package wang.ismy.travel.service.impl;

import wang.ismy.travel.dao.RouteDAO;
import wang.ismy.travel.dao.impl.RouteDAOImpl;
import wang.ismy.travel.entity.PageBean;
import wang.ismy.travel.entity.Route;
import wang.ismy.travel.service.RouteService;

public class RouteServiceImpl implements RouteService {

    private RouteDAO dao = new RouteDAOImpl();

    @Override
    public PageBean<Route> list(Integer cid, Integer currentPage, Integer pageSize) {

        PageBean<Route> pageBean = new PageBean<>();

        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(dao.count(cid));
        pageBean.setTotalPage(pageBean.getTotalCount() / pageSize);

        int totalPage = pageBean.getTotalCount() % pageBean.getPageSize() == 0 ?
                pageBean.getTotalCount() / pageBean.getPageSize() :
                (pageBean.getTotalCount() / pageBean.getPageSize()) + 1;
        pageBean.setTotalPage(totalPage);
        pageBean.setList(dao.findByPage(cid, (currentPage - 1) * pageSize, pageSize));
        return pageBean;
    }
}
