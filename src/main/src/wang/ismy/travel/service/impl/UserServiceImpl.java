package wang.ismy.travel.service.impl;

import wang.ismy.travel.dao.UserDAO;
import wang.ismy.travel.dao.impl.UserDAOImpl;
import wang.ismy.travel.entity.User;
import wang.ismy.travel.service.UserService;
import wang.ismy.travel.util.MailUtils;
import wang.ismy.travel.util.UUIDUtils;

import javax.mail.MessagingException;

/**
 * @author MY
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean regist(User user) {

        if (userDAO.findByUsername(user.getUsername()) != null) {
            // 用户名已存在
            return false;
        }

        user.setStatus("N");
        user.setCode(UUIDUtils.get());


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    MailUtils.send(user.getEmail(), "邮箱激活",
                            "<a href='http://localhost/ws/active?code=" + user.getCode() + "'>点击激活</a>");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        userDAO.save(user);
        return true;
    }

    @Override
    public boolean active(String code) {

        // 根据激活码查询用户
        User user = userDAO.findByCode(code);

        if (user != null){
            user.setStatus("Y");
            userDAO.updateStatus(user);
            return true;
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
