package wang.ismy.travel.service.impl;

import wang.ismy.travel.dao.FavoriteDAO;
import wang.ismy.travel.dao.impl.FavoriteDAOImpl;
import wang.ismy.travel.service.FavoriteService;

/**
 * @author MY
 */
public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDAO dao = new FavoriteDAOImpl();

    @Override
    public boolean isFavorite(Integer rid, Integer userId) {

        return dao.find(rid,userId) != null;

    }

    @Override
    public void add(Integer rid, Integer uid) {
        dao.add(rid,uid);
    }
}
