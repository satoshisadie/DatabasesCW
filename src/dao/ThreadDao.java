package dao;

import beans.Thread;

import java.util.List;

public interface ThreadDao {
    public List<Thread> getAll();
    public void follow(int userId, int threadId);
    public void cancelFollowing(int userId, int threadId);
    public int create(Thread thread);
    public Thread get(int threadId);
    public void update(Thread thread);
    public void delete(int threadId);
}
