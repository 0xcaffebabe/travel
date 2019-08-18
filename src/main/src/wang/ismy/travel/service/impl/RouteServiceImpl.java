package wang.ismy.travel.service.impl;

import wang.ismy.travel.dao.FavoriteDAO;
import wang.ismy.travel.dao.RouteDAO;
import wang.ismy.travel.dao.RouteImgDAO;
import wang.ismy.travel.dao.SellerDAO;
import wang.ismy.travel.dao.impl.FavoriteDAOImpl;
import wang.ismy.travel.dao.impl.RouteDAOImpl;
import wang.ismy.travel.dao.impl.RouteImgDAOImpl;
import wang.ismy.travel.dao.impl.SellerDAOImpl;
import wang.ismy.travel.entity.PageBean;
import wang.ismy.travel.entity.Route;
import wang.ismy.travel.entity.RouteImg;
import wang.ismy.travel.entity.Seller;
import wang.ismy.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDAO dao = new RouteDAOImpl();

    private RouteImgDAO routeImgDAO = new RouteImgDAOImpl();

    private SellerDAO sellerDAO = new SellerDAOImpl();

    private FavoriteDAO favoriteDAO = new FavoriteDAOImpl();

    @Override
    public PageBean<Route> list(Integer cid, Integer currentPage, Integer pageSize, String kw) {

        PageBean<Route> pageBean = new PageBean<>();

        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(dao.count(cid,kw));
        pageBean.setTotalPage(pageBean.getTotalCount() / pageSize);

        int totalPage = pageBean.getTotalCount() % pageBean.getPageSize() == 0 ?
                pageBean.getTotalCount() / pageBean.getPageSize() :
                (pageBean.getTotalCount() / pageBean.getPageSize()) + 1;
        pageBean.setTotalPage(totalPage);
        pageBean.setList(dao.findByPage(cid, (currentPage - 1) * pageSize, pageSize,kw));
        return pageBean;
    }

    @Override
    public Route findOne(Integer rid) {
        Route route = dao.findOne(rid);

        List<RouteImg> imgList = routeImgDAO.findByRid(route.getRid());

        route.setImgList(imgList);
        Seller seller = sellerDAO.findById(route.getSid());
        route.setSeller(seller);
        route.setCount(favoriteDAO.count(route.getRid()));
        return route;
    }
}
