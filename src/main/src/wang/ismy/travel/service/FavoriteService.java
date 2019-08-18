package wang.ismy.travel.service;

public interface FavoriteService {

    boolean isFavorite(Integer rid,Integer userId);

    void add(Integer rid,Integer uid);
}
