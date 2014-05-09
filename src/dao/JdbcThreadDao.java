package dao;

import beans.Thread;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
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
    public void create(Thread thread) {
        String query = "INSERT INTO Thread(Subject, ViewCount, DateCreated, Active) " +
                       "VALUES (?, 0, (SELECT GETDATE()), TRUE)";

        jdbcTemplate.update(query, thread.getSubject());
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
                       "SET Subject = ?, ViewCount = ?, Active = ?";

        jdbcTemplate.update(query, thread.getSubject(), thread.getViewCount(), thread.getActive());
    }

    @Override
    public void delete(int threadId) {

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    class ThreadRowMapper implements RowMapper<Thread> {
        @Override
        public Thread mapRow(ResultSet rs, int rowNum) throws SQLException {
            Thread thread = new Thread();
            thread.setId(rs.getInt("id"));
            thread.setSubject(rs.getString("subject"));
            thread.setDateCreated(new DateTime(rs.getTimestamp("dateCreated")));
            thread.setActive(rs.getBoolean("active"));
            thread.setViewCount(rs.getInt("viewCount"));
            thread.setUserId(rs.getInt("userId"));
            return thread;
        }
    }
}
