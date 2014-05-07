package dao;

import beans.User;

public interface UserDao {
    public void create(User user);
    public User get(int userId);
    public User get(String login, String password);
    public void update(User user);
    public void delete(int userId);
}
