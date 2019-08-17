package wang.ismy.travel.dao;

import wang.ismy.travel.entity.Category;

import java.util.List;

public interface CategoryDAO {

    List<Category> findAll();
}
