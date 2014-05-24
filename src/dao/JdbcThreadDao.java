package dao;

import beans.Thread;
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

public class JdbcThreadDao implements ThreadDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Thread> getAll() {
        String query = "SELECT * " +
                       "FROM Thread";

        return jdbcTemplate.query(query, new ThreadRowMapper());
    }

    @Override
    public void follow(int userId, int threadId) {
        String query = "INSERT INTO ThreadFollower(UserId, ThreadId) " +
                       "VALUES (?, ?)";
        // TODO Check if user already follow this thread
        jdbcTemplate.update(query, userId, threadId);
    }

    @Override
    public void cancelFollowing(int userId, int threadId) {
        String query = "DELETE ThreadFollower " +
                       "WHERE UserId = ? AND ThreadId = ?";

        jdbcTemplate.update(query, userId, threadId);
    }

    @Override
    public int create(Thread thread) {
        String query = "INSERT INTO Thread(Subject, UserId, ForumId) " +
                       "VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"Id"});
            preparedStatement.setString(1, thread.getSubject());
            preparedStatement.setInt(2, thread.getUserId());
            preparedStatement.setInt(3, thread.getForumId());
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Thread get(int threadId) {
        String query = "SELECT * " +
                       "FROM Thread t " +
                       "WHERE t.Id = ?";

        List<Thread> result = jdbcTemplate.query(query, new ThreadRowMapper(), threadId);

        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public void update(Thread thread) {
        String query = "UPDATE Thread " +
                       "SET Subject = ?, ViewCount = ?, Active = ? " +
                       "WHERE Id = ?";

        jdbcTemplate.update(query, thread.getSubject(), thread.getViewCount(), thread.getActive(), thread.getId());
    }

    @Override
    public void delete(int threadId) {
        String query = "DELETE Thread " +
                       "WHERE Id = ?";

        jdbcTemplate.update(query, threadId);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}

class ThreadRowMapper implements RowMapper<Thread> {
    @Override
    public Thread mapRow(ResultSet rs, int rowNum) throws SQLException {
        Thread thread = new Thread();
        thread.setId(rs.getInt("id"));
        thread.setSubject(rs.getString("subject"));
        thread.setDateCreated(new DateTime(rs.getTimestamp("dateCreated")));
        thread.setDateLastPost(new DateTime(rs.getTimestamp("dateLastPost")));
        thread.setActive(rs.getBoolean("active"));
        thread.setViewCount(rs.getInt("viewCount"));
        thread.setUserId(rs.getInt("userId"));
        return thread;
    }
}
