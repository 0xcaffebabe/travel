package wang.ismy.travel.servlet;

import wang.ismy.travel.entity.Result;
import wang.ismy.travel.entity.Route;
import wang.ismy.travel.entity.User;
import wang.ismy.travel.service.FavoriteService;
import wang.ismy.travel.service.RouteService;
import wang.ismy.travel.service.impl.FavoriteServiceImpl;
import wang.ismy.travel.service.impl.RouteServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MY
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService service = new RouteServiceImpl();

    private FavoriteService favoriteService = new FavoriteServiceImpl();

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String cidStr = req.getParameter("cid");
        String kw = req.getParameter("kw");

        Integer cid = null;

        try {
            cid = Integer.valueOf(cidStr);
        } catch (NumberFormatException e) {

        }

        Integer currentPage = Integer.valueOf(currentPageStr);
        Integer pageSize = Integer.valueOf(pageSizeStr);

        writeJson(Result.success(service.list(cid,currentPage,pageSize,kw)),resp);

    }

    public void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String rid = req.getParameter("rid");

        Route route = service.findOne(Integer.valueOf(rid));

        writeJson(Result.success(route),resp);
    }

    public void favorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("rid");

        User user = (User) req.getSession().getAttribute("user");
        // 未登录 收藏为假
        if (user == null){
            writeJson(Result.success(false),resp);
            return;
        }else{
            writeJson(Result.success(favoriteService.isFavorite(Integer.valueOf(rid),user.getUid())),resp);
        }
    }

    public void addFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("rid");

        User user = (User) req.getSession().getAttribute("user");

        if (user == null){
            writeJson(Result.error("未登录"),resp);

        }else{

            if (favoriteService.isFavorite(Integer.valueOf(rid),user.getUid())){
                writeJson(Result.success("已经收藏过了"),resp);
                return;
            }

            favoriteService.add(Integer.valueOf(rid),user.getUid());
            writeJson(Result.success("收藏成功"),resp);
        }

    }
}
