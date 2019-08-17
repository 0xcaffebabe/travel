package wang.ismy.travel.servlet;

import com.google.gson.Gson;
import wang.ismy.travel.entity.Result;
import wang.ismy.travel.entity.User;
import wang.ismy.travel.service.UserService;
import wang.ismy.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ws/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService userService = new UserServiceImpl();
        User user = userService.findByUsername(username);

        String json = null;

        if (user == null){
            // 用户不存在
            json = new Gson().toJson(Result.error("用户名或密码错误"));

        }else{
            if (!user.getPassword().equals(password)){
                // 密码错误
                json = new Gson().toJson(Result.error("用户名或密码错误"));
            }else{
                if ("N".equals(user.getStatus())){
                    json = new Gson().toJson(Result.error("账户未激活"));
                }else{
                    json = new Gson().toJson(Result.success("登录成功"));
                    req.getSession().setAttribute("user",user);
                }
            }
        }

        resp.getWriter().print(json);

    }
}
