package wang.ismy.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wang.ismy.travel.dao.RouteDAO;
import wang.ismy.travel.entity.Route;
import wang.ismy.travel.util.JdbcUtils;

import java.util.List;

public class RouteDAOImpl implements RouteDAO {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public int count(Integer cid) {

        String sql = "SELECT COUNT(*) FROM tab_route WHERE cid = ?";

        return template.queryForObject(sql,Integer.class,cid);

    }

    @Override
    public List<Route> findByPage(Integer cid, Integer start, Integer pageSize) {
        String sql = "SELECT * FROM tab_route WHERE cid = ? LIMIT ?,?";

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),cid,start,pageSize);
    }
}
