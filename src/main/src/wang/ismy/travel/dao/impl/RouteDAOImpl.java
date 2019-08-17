package wang.ismy.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import wang.ismy.travel.dao.RouteDAO;
import wang.ismy.travel.entity.Route;
import wang.ismy.travel.util.JdbcUtils;

import java.util.ArrayList;
import java.util.List;

public class RouteDAOImpl implements RouteDAO {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public int count(Integer cid, String kw) {

        String sql = "SELECT COUNT(*) FROM tab_route WHERE 1=1 ";

        List params = new ArrayList();

        if (cid != null){
            sql += " AND cid = ? ";
            params.add(cid);
        }

        if (!StringUtils.isEmpty(kw)){
            sql += " AND rname LIKE '%' ? '%'";
            params.add(kw);
        }
        return template.queryForObject(sql,Integer.class,params.toArray());

    }

    @Override
    public List<Route> findByPage(Integer cid, Integer start, Integer pageSize, String kw) {
        String sql = "SELECT * FROM tab_route  WHERE 1=1 ";

        List params = new ArrayList();

        if (cid != null){
            sql +="AND cid = ? ";
            params.add(cid);
        }

        if (!StringUtils.isEmpty(kw)){
            sql += " AND rname LIKE '%' ? '%'";
            params.add(kw);
        }

        sql += "LIMIT ?,?";
        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findOne(Integer rid) {
        String sql = "SELECT * FROM tab_route WHERE rid = ?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
