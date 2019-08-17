package wang.ismy.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wang.ismy.travel.dao.CategoryDAO;
import wang.ismy.travel.entity.Category;
import wang.ismy.travel.util.JdbcUtils;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<Category> findAll() {

        String sql = "SELECT * FROM tab_category ";

        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class){});

    }
}
