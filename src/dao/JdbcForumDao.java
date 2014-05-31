package dao;

import beans.Forum;
import beans.Topic;
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
    public Forum getForum(int forumId) {
        String query = "SELECT * " +
                       "FROM Forum f " +
                       "WHERE f.Id = ?";

        List<Forum> result = jdbcTemplate.query(query, new ForumRowMapper(), forumId);

        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public List<Forum> getForumsByTag(int tagId) {
        String query = "SELECT * " +
                       "FROM Forum f " +
                       "JOIN ForumTag ft ON ft.ForumId = f.Id " +
                       "WHERE ft.TagId = ?";

        return jdbcTemplate.query(query, new ForumRowMapper(), tagId);
    }

    @Override
    public List<Topic> getTopics(int forumId) {
        String query = "SELECT * " +
                       "FROM Topic t " +
                       "WHERE t.ForumId = ?";

        return jdbcTemplate.query(query, new TopicRowMapper(), forumId);
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
