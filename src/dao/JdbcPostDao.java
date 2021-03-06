package dao;

import beans.Post;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcPostDao implements PostDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Post> getTopicPosts(int topicId) {
        String query = "SELECT * " +
                       "FROM Post p " +
                       "WHERE p.TopicId = ?";

        return jdbcTemplate.query(query, new PostRowMapper(), topicId);
    }

    @Override
    public void create(Post post) {
        String query = "INSERT INTO Post(Message, TopicId, UserId, RepliedTo) " +
                       "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(query, post.getMessage(), post.getTopicId(), post.getUserId(), post.getRepliedTo());
    }

    @Override
    public Post get(int postId) {
        return null;
    }

    @Override
    public void update(Post post) {

    }

    @Override
    public void delete(int postId) {

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}

class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setId(rs.getInt("id"));
        post.setMessage(rs.getString("message"));
        post.setRating(rs.getInt("rating"));
        post.setDateCreated(new DateTime(rs.getTimestamp("dateCreated")));
        post.setTopicId(rs.getInt("topicId"));
        post.setUserId(rs.getInt("userId"));
        post.setRepliedTo(rs.getInt("repliedTo"));
        return post;
    }
}
