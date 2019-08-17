package wang.ismy.travel.servlet;

import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.util.StringUtils;
import wang.ismy.travel.entity.Result;
import wang.ismy.travel.entity.User;
import wang.ismy.travel.service.UserService;
import wang.ismy.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author MY
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    private UserService userService = new UserServiceImpl();

    @Override
    public void init() throws ServletException {
        super.init();
        // 这里需要自己手动完成beanUtils 日期转换器的编写
        ConvertUtils.register(new Converter() {
            @Override
            public <T> T convert(Class<T> aClass, Object o) {

                return (T) LocalDate.parse(o.toString());

            }
        }, LocalDate.class);
    }


    public void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");

        if (!StringUtils.isEmpty(code)){

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

    public void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

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

    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        // 验证码验证

        String clientCheck = req.getParameter("check");

        String serverCheck = "";
        if (req.getSession().getAttribute(KAPTCHA_SESSION_KEY) == null){

        }else{
            serverCheck = req.getSession().getAttribute(KAPTCHA_SESSION_KEY).toString();
        }

        // 马上删除session中的验证码数据
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if (!clientCheck.equalsIgnoreCase(serverCheck)){
            String json = new Gson().toJson(Result.error("验证码错误"));
            resp.getWriter().print(json);
            return;
        }

        // 获取数据
        Map<String, String[]> parameterMap = req.getParameterMap();
        // 封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 调用service

        boolean regist = userService.regist(user);

        String json = null;
        if (regist){
            json = new Gson().toJson(Result.success(null));
        }else{
            json = new Gson().toJson(Result.error("注册失败"));
        }

        // 响应结果
        resp.getWriter().print(json);
    }

    public void username(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        resp.setContentType("application/json;charset=utf-8");
        if (user == null){
            resp.getWriter().print(new Gson().toJson(Result.success("游客")));
            return;
        }

        resp.getWriter().print(new Gson().toJson(Result.success(user.getUsername())));
    }
}
