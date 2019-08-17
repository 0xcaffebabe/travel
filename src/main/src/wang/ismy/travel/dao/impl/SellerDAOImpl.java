package wang.ismy.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wang.ismy.travel.dao.SellerDAO;
import wang.ismy.travel.entity.Seller;
import wang.ismy.travel.util.JdbcUtils;

public class SellerDAOImpl implements SellerDAO {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public Seller findById(Integer id) {

        String sql = "SELECT * FROM tab_seller WHERE sid = ?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Seller.class),id);
    }
}
