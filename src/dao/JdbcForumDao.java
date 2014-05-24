package dao;

import beans.Forum;
import beans.Thread;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcForumDao implements ForumDao {
    DataSource dataSource;
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Thread> getThreads(int forumId) {
        String query = "SELECT * " +
                       "FROM Thread t " +
                       "WHERE t.ForumId = ?";

        return jdbcTemplate.query(query, new ThreadRowMapper(), forumId);
    }

    @Override
    public List<Forum> getSubforums(int forumId) {
        String query = "SELECT * " +
                       "FROM Forum f " +
                       "WHERE f.ParentForum = ?";

        return jdbcTemplate.query(query, new ForumRowMapper(), forumId);
    }

    @Override
    public List<Forum> getTopForums() {
        String query = "SELECT * " +
                       "FROM Forum f " +
                       "WHERE f.ParentForum IS NULL";

        return jdbcTemplate.query(query, new ForumRowMapper());
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}

class ForumRowMapper implements RowMapper<Forum> {
    @Override
    public Forum mapRow(ResultSet rs, int rowNum) throws SQLException {
        Forum forum = new Forum();
        forum.setId(rs.getInt("id"));
        forum.setName(rs.getString("name"));
        forum.setDescription(rs.getString("description"));
        forum.setParentForum(rs.getInt("parentForum"));
        return forum;
    }
}