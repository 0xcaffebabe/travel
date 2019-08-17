package wang.ismy.travel.servlet;


import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
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
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author MY
 */
@WebServlet("/ws/register")
public class RegisterServlet extends HttpServlet {

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        UserService userService = new UserServiceImpl();
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
}
