package dao;

import beans.ForumRule;
import beans.Tag;
import beans.ThreadTag;
import com.google.common.base.Joiner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcCommonDao implements CommonDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tag> getAllTags() {
        String query = "SELECT * " +
                       "FROM Tag";

        return jdbcTemplate.query(query, new TagRowMapper());
    }

    @Override
    public List<ThreadTag> getThreadTagForThreads(List<Integer> threadsIds) {
        String query = "SELECT * " +
                       "FROM ThreadTag tt " +
                       "WHERE tt.ThreadId IN (" + Joiner.on(",").join(threadsIds) + ")";

        return jdbcTemplate.query(query, new ThreadTagRowMapper());
    }

//    @Override
//    public List<Tag> getThreadTags(int threadId) {
//        String query = "SELECT * " +
//                       "FROM Thread t " +
//                       "JOIN ThreadTags tt ON tt.ThreadId = t.Id " +
//                       "JOIN Tag tg ON tg.Id = tt.TagId " +
//                       "WHERE t.Id = ?";
//
//        return jdbcTemplate.query(query, new TagRowMapper(), threadId);
//    }

    @Override
    public void attachTags(int threadId, List<Integer> tagsIds) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ThreadTag(ThreadId, TagId) ")
             .append("VALUES ");

        for (int i = 0; i < tagsIds.size(); i++) {
            if (i > 0) query.append(",");
            query.append("(").append(threadId).append(",").append(tagsIds.get(i)).append(")");
        }

        jdbcTemplate.update(query.toString());
    }

    @Override
    public List<ForumRule> getAllRules() {
        return null;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    class TagRowMapper implements RowMapper<Tag> {
        @Override
        public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tag tag = new Tag();
            tag.setId(rs.getInt("id"));
            tag.setName(rs.getString("name"));
            tag.setDescription(rs.getString("description"));
            return tag;
        }
    }

    class ThreadTagRowMapper implements RowMapper<ThreadTag> {
        @Override
        public ThreadTag mapRow(ResultSet rs, int rowNum) throws SQLException {
            ThreadTag threadTag = new ThreadTag();
            threadTag.setThreadId(rs.getInt("threadId"));
            threadTag.setTagId(rs.getInt("tagId"));
            return threadTag;
        }
    }
}
