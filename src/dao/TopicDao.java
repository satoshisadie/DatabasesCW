package dao;

import beans.Topic;

import java.util.List;

public interface TopicDao {
    public List<Topic> getAll();
    public List<Topic> getTopicsByTag(int tagId);
    public void follow(int userId, int threadId);
    public void cancelFollowing(int userId, int threadId);
    public int create(Topic topic);
    public Topic get(int threadId);
    public void update(Topic topic);
    public void delete(int threadId);
}
