package wang.ismy.travel.service;

import wang.ismy.travel.entity.User;

/**
 * @author MY
 */
public interface UserService {


    boolean regist(User user);

    boolean active(String code);

    User findByUsername(String username);
}
