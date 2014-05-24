package dao;

import beans.User;

import java.util.List;

public interface UserDao {
    public void createAdministrator(User user);
    public void createUser(User user);
    public User get(int userId);
    public User get(String login, String password);
    public List<User> getUsersByIds(Iterable<Integer> usersIds);
    public void update(User user);
    public void delete(int userId);
}
