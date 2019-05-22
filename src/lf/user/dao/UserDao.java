package lf.user.dao;

import lf.user.domain.User;

/**
 * UserDao接口
 */
public interface UserDao {
    public void addUser(User form);
    public User findByUsername(String username);
}
