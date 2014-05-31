package dao;

import beans.Forum;
import beans.Topic;

import java.util.List;

public interface ForumDao {
    public Forum getForum(int forumId);
    public List<Forum> getForumsByTag(int tagId);
    public List<Topic> getTopics(int forumId);
    public List<Forum> getSubforums(int forumId);
    public List<Forum> getTopForums();
}
