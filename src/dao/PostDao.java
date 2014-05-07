package dao;

import beans.Post;

import java.util.List;

public interface PostDao {
    public List<Post> getThreadPosts(int threadId);
    public void create(Post post);
    public Post get(int id);
    public void update(Post post);
    public void delete(int id);
}
