package wang.ismy.travel.servlet;

import com.google.gson.Gson;
import wang.ismy.travel.entity.Result;
import wang.ismy.travel.service.CategoryService;
import wang.ismy.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MY
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImpl();

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");

        writeJson(Result.success(service.findAll()),resp);

    }
}
