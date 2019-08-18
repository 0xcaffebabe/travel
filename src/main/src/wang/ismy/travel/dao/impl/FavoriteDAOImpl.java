package wang.ismy.travel.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wang.ismy.travel.dao.FavoriteDAO;
import wang.ismy.travel.entity.Favorite;
import wang.ismy.travel.util.JdbcUtils;

import java.util.Date;

public class FavoriteDAOImpl implements FavoriteDAO {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public Favorite find(Integer rid, Integer uid) {

        String sql = "SELECT * FROM tab_favorite WHERE rid = ? AND uid = ?";
        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(Favorite.class),rid,uid);
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public int count(Integer rid) {

        String sql = "SELECT COUNT(*) FROM tab_favorite WHERE rid = ?";
        return template.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public void add(Integer rid, Integer uid) {
        String sql = "INSERT INTO tab_favorite(rid,date,uid) VALUES(?,?,?)";


        template.update(sql,rid,new Date(),uid);
    }
}
