package wang.ismy.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wang.ismy.travel.dao.UserDAO;
import wang.ismy.travel.entity.User;
import wang.ismy.travel.util.JdbcUtils;

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public User findByUsername(String username) {

        String sql = "SELECT * FROM tab_user WHERE username = ?";

        try{
            return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
        }catch (Throwable t){
            return null;
        }


    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO tab_user VALUES(?,?,?,?,?,?,?,?,?,?)";

        template.update(sql,user.getUid(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    @Override
    public User findByCode(String code) {
        String sql = "SELECT * FROM tab_user WHERE code = ?";

        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),code);
        }catch (Throwable t){
            return null;
        }

    }

    @Override
    public boolean updateStatus(User user) {

        String sql = "UPDATE tab_user SET status = ? WHERE uid = ?";

        return template.update(sql,user.getStatus(),user.getUid()) == 1;
    }
}
