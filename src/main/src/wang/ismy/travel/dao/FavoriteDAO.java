package wang.ismy.travel.dao;

import wang.ismy.travel.entity.Favorite;

public interface FavoriteDAO {

    Favorite find(Integer rid,Integer uid);

    int count(Integer rid);

    void add(Integer rid, Integer uid);
}
