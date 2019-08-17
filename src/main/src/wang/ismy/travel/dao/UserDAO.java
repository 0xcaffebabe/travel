package wang.ismy.travel.dao;

import wang.ismy.travel.entity.User;

/**
 * @author MY
 */
public interface UserDAO {

    User findByUsername(String username);

    void save(User user);

    User findByCode(String code);

    boolean updateStatus(User user);
}
