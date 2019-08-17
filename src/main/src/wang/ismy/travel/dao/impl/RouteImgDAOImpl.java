package wang.ismy.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wang.ismy.travel.dao.RouteDAO;
import wang.ismy.travel.dao.RouteImgDAO;
import wang.ismy.travel.entity.RouteImg;
import wang.ismy.travel.util.JdbcUtils;

import java.util.List;

public class RouteImgDAOImpl implements RouteImgDAO {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<RouteImg> findByRid(Integer rid) {

        String sql = "SELECT * FROM tab_route_img WHERE rid = ?";

        return template.query(sql,new BeanPropertyRowMapper<>(RouteImg.class),rid);
    }
}
