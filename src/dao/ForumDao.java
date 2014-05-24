package dao;

import beans.Forum;
import beans.Thread;

import java.util.List;

public interface ForumDao {
    public Forum getForum(int forumId);
    public List<Thread> getThreads(int forumId);
    public List<Forum> getSubforums(int forumId);
    public List<Forum> getTopForums();
}
