package wang.ismy.travel.servlet;

import wang.ismy.travel.entity.Result;
import wang.ismy.travel.service.RouteService;
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

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String cidStr = req.getParameter("cid");

        Integer cid = Integer.valueOf(cidStr);
        Integer currentPage = Integer.valueOf(currentPageStr);
        Integer pageSize = Integer.valueOf(pageSizeStr);

        writeJson(Result.success(service.list(cid,currentPage,pageSize)),resp);

    }
}
