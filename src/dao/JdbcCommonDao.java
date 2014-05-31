package dao;

import beans.ForumRule;
import beans.Tag;
import beans.TopicFollower;
import beans.TopicTag;
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
    public List<Tag> getTagsByTopic(int topicId) {
        String query = "SELECT * " +
                       "FROM Tag tg " +
                       "JOIN TopicTag tt ON tt.TagId = tg.Id " +
                       "JOIN Topic t ON t.Id = tt.TopicId " +
                       "WHERE t.Id = ?";

        return jdbcTemplate.query(query, new TagRowMapper(), topicId);
    }

    @Override
    public List<TopicTag> getTopicTagForTopics(List<Integer> topicsIds) {
        String query = "SELECT * " +
                       "FROM TopicTag tt " +
                       "WHERE tt.TopicId IN (" + Joiner.on(",").join(topicsIds) + ")";

        return jdbcTemplate.query(query, new TopicTagRowMapper());
    }

    @Override
    public void attachTags(int topicId, List<Integer> tagsIds) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO TopicTag(TopicId, TagId) ")
             .append("VALUES ");

        for (int i = 0; i < tagsIds.size(); i++) {
            if (i > 0) query.append(",");
            query.append("(").append(topicId).append(",").append(tagsIds.get(i)).append(")");
        }

        jdbcTemplate.update(query.toString());
    }

    @Override
    public List<ForumRule> getAllRules() {
        String query = "SELECT * " +
                       "FROM ForumRule";

        return jdbcTemplate.query(query, new ForumRuleRowMapper());
    }

    @Override
    public void attachViolation(int postId, int ruleId, String violationComment) {
        String query = "INSERT INTO RuleViolation(PostId, RuleId, Comment) " +
                       "VALUES (?, ?, ?)";

        jdbcTemplate.update(query, postId, ruleId, violationComment);
    }

    @Override
    public List<TopicFollower> getTopicFollowersByUserId(int userId) {
        String query = "SELECT * " +
                       "FROM TopicFollower tf " +
                       "WHERE tf.UserId = ?";

        return jdbcTemplate.query(query, new TopicFollowerRowMapper(), userId);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
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

class TopicTagRowMapper implements RowMapper<TopicTag> {
    @Override
    public TopicTag mapRow(ResultSet rs, int rowNum) throws SQLException {
        TopicTag topicTag = new TopicTag();
        topicTag.setTopicId(rs.getInt("topicId"));
        topicTag.setTagId(rs.getInt("tagId"));
        return topicTag;
    }
}

class ForumRuleRowMapper implements RowMapper<ForumRule> {
    @Override
    public ForumRule mapRow(ResultSet rs, int rowNum) throws SQLException {
        ForumRule forumRule = new ForumRule();
        forumRule.setId(rs.getInt("id"));
        forumRule.setName(rs.getString("name"));
        forumRule.setDescription(rs.getString("description"));
        return forumRule;
    }
}

class TopicFollowerRowMapper implements RowMapper<TopicFollower> {
    @Override
    public TopicFollower mapRow(ResultSet rs, int rowNum) throws SQLException {
        TopicFollower topicFollower = new TopicFollower();
        topicFollower.setUserId(rs.getInt("userId"));
        topicFollower.setTopicId(rs.getInt("topicId"));
        return topicFollower;
    }
}
