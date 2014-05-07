package dao;

import beans.Post;

public interface PostDao {
    public void create(Post post);
    public Post get(int postId);
    public void update(Post post);
    public void delete(int postId);
}
