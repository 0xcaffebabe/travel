package wang.ismy.travel.servlet;

import org.springframework.util.StringUtils;
import wang.ismy.travel.service.UserService;
import wang.ismy.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ws/active")
public class ActiveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");

        if (!StringUtils.isEmpty(code)){
            UserService userService = new UserServiceImpl();
            boolean ret = userService.active(code);

            String msg = null;
            if (ret){
                msg = "激活成功";
            }else{
                msg = "激活失败";
            }

            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().print(msg);
        }
    }
}
