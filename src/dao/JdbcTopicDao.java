package dao;

import beans.Topic;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTopicDao implements TopicDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Topic> getAll() {
        String query = "SELECT * " +
                       "FROM Topic";

        return jdbcTemplate.query(query, new TopicRowMapper());
    }

    @Override
    public List<Topic> getTopicsByTag(int tagId) {
        String query = "SELECT * " +
                       "FROM Topic t " +
                       "JOIN TopicTag tt ON tt.TopicId = t.Id " +
                       "WHERE tt.TagId = ?";

        return jdbcTemplate.query(query, new TopicRowMapper(), tagId);
    }

    @Override
    public void follow(int userId, int topicId) {
        String query = "INSERT INTO TopicFollower(UserId, TopicId) " +
                       "VALUES (?, ?)";

        jdbcTemplate.update(query, userId, topicId);
    }

    @Override
    public void cancelFollowing(int userId, int topicId) {
        String query = "DELETE TopicFollower " +
                       "WHERE UserId = ? AND TopicId = ?";

        jdbcTemplate.update(query, userId, topicId);
    }

    @Override
    public int create(Topic topic) {
        String query = "INSERT INTO Topic(Subject, UserId, ForumId) " +
                       "VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"Id"});
            preparedStatement.setString(1, topic.getSubject());
            preparedStatement.setInt(2, topic.getUserId());
            preparedStatement.setInt(3, topic.getForumId());
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Topic get(int topicId) {
        String query = "SELECT * " +
                       "FROM Topic t " +
                       "WHERE t.Id = ?";

        List<Topic> result = jdbcTemplate.query(query, new TopicRowMapper(), topicId);

        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public void update(Topic topic) {
        String query = "UPDATE Topic " +
                       "SET Subject = ?, ViewCount = ?, Active = ? " +
                       "WHERE Id = ?";

        jdbcTemplate.update(query, topic.getSubject(), topic.getViewCount(), topic.getActive(), topic.getId());
    }

    @Override
    public void delete(int topicId) {
        String query = "DELETE Topic " +
                       "WHERE Id = ?";

        jdbcTemplate.update(query, topicId);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}

class TopicRowMapper implements RowMapper<Topic> {
    @Override
    public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
        Topic topic = new Topic();
        topic.setId(rs.getInt("id"));
        topic.setSubject(rs.getString("subject"));
        topic.setDateCreated(new DateTime(rs.getTimestamp("dateCreated")));
        topic.setDateLastPost(new DateTime(rs.getTimestamp("dateLastPost")));
        topic.setActive(rs.getBoolean("active"));
        topic.setViewCount(rs.getInt("viewCount"));
        topic.setUserId(rs.getInt("userId"));
        return topic;
    }
}
